package PageObject;

import Utils.ElementControls;
import Utils.WebDriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductListingPage {
    WebDriver driver;

    @FindBy(id = "selectProductSort")
    public WebElement sortBy;

    @FindBy(className = "icon-th-list")
    public WebElement listView;

    @FindBy(xpath = "(//button[contains(@class,'compare')])[1]")
    public WebElement compareButton;

    @FindBy(xpath = "(//button[contains(@class,'compare')])[1]//strong")
    public WebElement noOfCompareItems;

    @FindBy(xpath = "//div[contains(@class,'right')]//span[@itemprop='price']")
    List<WebElement> allTheItemsPrice;

    @FindBy(xpath = "//span[contains(@class,'ajax_cart_quantity ')]")
    public WebElement cartQuantity;

    @FindBy(id = "button_order_cart")
    public WebElement orderCart;

    @FindBy(xpath = "//a[@title='Add to cart']")
    public List<WebElement> allElementsAddToCartButton;

    @FindBy(xpath = "//h5[@itemprop='name']/a")
    public List<WebElement> itemName;

    @FindBy(xpath = "//div[contains(@class,'right-block-content')]//span[@itemprop='price']")
    public List<WebElement> itemPrice;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    public WebElement continueShopping;
    private ElementControls elementControls;

    @Inject
    public ProductListingPage(){
        driver= WebDriverManager.getDriver();
        PageFactory.initElements(driver,this);
        elementControls=new ElementControls();
    }

    public void sortProductBy(String sortByCondition){

        elementControls.selectDropDownByVisibleText(sortBy,sortByCondition);
    }

    public void selectListView(){
        elementControls.click(listView);
        try {
            Thread.sleep(4000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static double highestPrice;
    public ProductDetailsPage openProductWithHighestPrice(){
        List<Double> prices=new ArrayList<>();
        for(WebElement element:allTheItemsPrice)
            prices.add(Double.parseDouble(elementControls.getText(element).trim().replaceAll("\\$","")));
        Collections.sort(prices);
        Collections.reverse(prices);
        System.out.println(prices);
        System.out.println("//div[div[contains(@class,'right')]//span[contains(text(),'"+prices.get(0)+"')]]/div[contains(@class,'center-block')]//a[@class='product-name']");
        WebElement element=elementControls.find(By.xpath("//div[div[contains(@class,'right')]//span[contains(text(),'"+prices.get(0)+"')][@itemprop='price']]/div[contains(@class,'center-block')]//a[@class='product-name']"));
        elementControls.click(element);
        highestPrice=prices.get(0);
        return new ProductDetailsPage();
    }

    public void addItemToTheCart(int index){
        elementControls.waitTillVisible(allElementsAddToCartButton.get(index-1),60);
        elementControls.click(allElementsAddToCartButton.get(index-1));

    }

    public void clickOnContinueShopping(){
        elementControls.click(continueShopping);
    }

    public void verifyCartQuantity(String items){
        Assert.assertEquals(elementControls.getText(cartQuantity), items);
    }

    public void moveCartQuantity(){
        elementControls.moveToElement(cartQuantity);
    }

    public void clickOnOrderCart(){
        elementControls.waitTillVisible(orderCart,60);
        elementControls.click(orderCart);
    }
}
