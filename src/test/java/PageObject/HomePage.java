package PageObject;

import Utils.ElementControls;
import Utils.WebDriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class HomePage {
    @FindBy(className ="login")
    public WebElement signIn;

    @FindBy(xpath = "//img[contains(@class,'logo')]")
    public WebElement logo;

    @FindBy(id = "search_query_top")
    public WebElement searchQuery;

    @FindBy(className = "ajax_cart_no_product")
    public WebElement cartIsEmpty;

    @FindBy(xpath = "//span[@class='shop-phone']/strong")
    public WebElement phoneNumber;

    @FindBy(xpath = "//ul[contains(@class,'menu-content')]/li/a")
    public List<WebElement> menus;

    @FindBy(xpath = "(//a[@title='Summer Dresses'])[2]")
    public WebElement summerDressesUnderDresses;

    @FindBy(xpath = "(//a[@title='Dresses'])[2]")
    public WebElement dresses;

    @FindBy(xpath = "//a[@title='View my customer account']")
    public WebElement userTab;

    @FindBy(className = "logout")
    public WebElement logout;
    @FindBy(className = "logout")
    public List<WebElement> logoutPresent;
    WebDriver driver;

    private ElementControls elementControls;
    @Inject
    public HomePage(){
        elementControls=new ElementControls();
        driver= WebDriverManager.getDriver();
        PageFactory.initElements(driver,this);
    }

    public ProductListingPage navigateToSummerDresses(){
        elementControls.click(summerDressesUnderDresses);
        return new ProductListingPage();
    }

    public void verifyLogoIsPresent(){
        Assert.assertTrue(elementControls.isVisible(logo),"Logo is not present");
    }

    public void clickOnSignIn(){
        elementControls.click(signIn);
    }

    public void verifyUserNameAs(String username){
        elementControls.waitTillVisible(userTab,60);
        Assert.assertEquals(elementControls.getText(userTab),username);
    }

    public void moveToTheMenu(String menu){

        switch (menu.toLowerCase()) {
            case "dresses":
                elementControls.moveToElement(dresses);
                elementControls.waitTillVisible(summerDressesUnderDresses,60);
                break;
        }
    }

    public void moveToSubMenu(String subMenu){
        switch (subMenu.toLowerCase()) {
            case "summer dresses":
                navigateToSummerDresses();
                break;
        }
    }


}
