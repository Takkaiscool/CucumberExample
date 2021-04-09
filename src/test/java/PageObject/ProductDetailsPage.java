package PageObject;

import Utils.ElementControls;
import Utils.WebDriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {

    WebDriver driver;
    @FindBy(id = "group_1")
    public WebElement size;

    @FindBy(xpath = "//span[text()='Add to cart']//ancestor::button")
    public WebElement addToCartButton;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    public WebElement proceedToCheckout;

    @FindBy(xpath = "(//a[@title='Proceed to checkout'])[last()]")
    public WebElement proceedToCheckoutCart;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    public WebElement continueShopping;

    @FindBy(className = "ajax_block_products_total")
    public WebElement totalProductPrice;

    @FindBy(id = "layer_cart_product_price")
    public WebElement productPrice;
    private ElementControls elementControls;
    @Inject
    public ProductDetailsPage(){
        driver= WebDriverManager.getDriver();
        PageFactory.initElements(driver,this);
        elementControls=new ElementControls();
    }

    public void selectSizeAs(String siz){
        elementControls.selectDropDownByVisibleText(size,siz);
    }

    public void addToCart(){
        elementControls.click(addToCartButton);
        elementControls.waitTillVisible(proceedToCheckout,60);
    }

    public void clickOnProceedToCheckout(){
        elementControls.waitTillVisible(proceedToCheckoutCart,60);
        elementControls.click(proceedToCheckoutCart);
        elementControls.wait(4);
    }
}
