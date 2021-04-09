package PageObject;

import Utils.ElementControls;
import Utils.WebDriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage {

    @FindBy(xpath = "//tr[contains(@class,'cart_item')]//p[@class='product-name']")
    public List<WebElement> productNames;

    @FindBy(xpath = "//tr[contains(@class,'cart_item')]//span[contains(@id,'total_product_price')]")
    public List<WebElement> productTotal;

    @FindBy(xpath = "//span[contains(text(),'Address')]//ancestor::li")
    public WebElement addressHeader;

    @FindBy(xpath = "//span[contains(text(),'Shipping')]//ancestor::li")
    public WebElement shippingHeader;

    @FindBy(xpath = "//input[@type='checkbox']//ancestor::span")
    public WebElement checkBoxTC;

    @FindBy(xpath = "//a[@title='Pay by check.']")
    public WebElement payByCheck;

    @FindBy(xpath = "//a[@title='Pay by bank wire']")
    public WebElement payByWire;

    @FindBy(name = "processAddress")
    public WebElement addressCheckoout;
    @FindBy(name = "processCarrier")
    public WebElement shippingCheckout;

    @FindBy(xpath = "//td[@class='cart_total']//span")
    public List<WebElement> cartTotals;

    @FindBy(xpath = "//tr[@class='cart_total_delivery']//td[@class='price']")
    public WebElement shipingCharges;

    @FindBy(xpath = "//td[@id='total_price_container']//span")
    public WebElement totalPrice;
    private ElementControls elementControls;
    private WebDriver driver;
    @Inject
    public ShoppingCartPage(){
        elementControls=new ElementControls();
        driver=WebDriverManager.getDriver();
        PageFactory.initElements(WebDriverManager.getDriver(),this);
    }

    public void verifyProductNameAndPrice(List<String> itemNames,List<String> itemValues){

        elementControls.waitTillVisible(productNames.get(0),60);
        for (int i = 0; i < productNames.size(); i++) {
            Assert.assertEquals(elementControls.getText(productNames.get(i)), itemNames.get(i));
            Assert.assertEquals(elementControls.getText(productTotal.get(i)), itemValues.get(i));
        }
    }

    public void verifyApplicationIsInAddressTab(){
        Assert.assertTrue(elementControls.getAttribute(addressHeader,"class").contains("step_current"));
    }

    public void verifyApplicationIsInShippingTab(){
        Assert.assertTrue(elementControls.getAttribute(shippingHeader,"class").contains("step_current"));
    }

    public void verifyTermsAndConditionIsNotSelected(){
        Assert.assertTrue(elementControls.getAttribute(checkBoxTC,"class").equals(""));
    }

    public void clickOnTC(){
        elementControls.click(checkBoxTC);
    }

    public void verifyTheTotalAmount(){
        List<Double> prices = new ArrayList<>();
        for (int i = 0; i < cartTotals.size(); i++) {
            double price = Double.parseDouble(elementControls.getText(cartTotals.get(i)).trim().replaceAll("\\$", ""));
            prices.add(price);
        }
        System.out.println(prices);
        double price = Double.parseDouble(elementControls.getText(shipingCharges).trim().replaceAll("\\$", ""));
        prices.add(price);
        double totalAmt = 0;
        for (double pric : prices)
            totalAmt += pric;
        System.out.println(prices);
        double totalAmtFromApp = Double.parseDouble(elementControls.getText(totalPrice).trim().replaceAll("\\$", ""));
        DecimalFormat df = new DecimalFormat("##.00");
        Assert.assertEquals(totalAmtFromApp, Double.parseDouble(df.format(totalAmt)));
    }

    public void verifyPayByCheckIsDisplayed(){
        Assert.assertTrue(elementControls.isVisible(payByCheck));
    }
    public void verifyPayByBankIsDisplayed(){
        Assert.assertTrue(elementControls.isVisible(payByWire));
    }

    public void clickOnCheckoutInAddr(){
        elementControls.waitTillVisible(addressCheckoout,60);
        elementControls.click(addressCheckoout);
        elementControls.wait(4);

    }

    public void clickOnCheckoutInShipping(){
        elementControls.waitTillVisible(shippingCheckout,60);
        elementControls.click(shippingCheckout);
        elementControls.wait(4);
    }
}
