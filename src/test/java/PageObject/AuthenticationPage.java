package PageObject;

import Utils.ElementControls;
import Utils.PageControls;
import Utils.WebDriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AuthenticationPage {
    WebDriver driver;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "passwd")
    public WebElement password;

    @FindBy(id = "SubmitLogin")
    public WebElement sumitLogin;

    @FindBy(xpath = "//div[contains(@class,'alert')]//li")
    public WebElement errorMsg;

    private ElementControls elementControls;
    private PageControls pageControls;
    @Inject
    public AuthenticationPage(){
        elementControls=new ElementControls();
        pageControls=new PageControls();
        driver= WebDriverManager.getDriver();
        PageFactory.initElements(driver,this);
    }

    public void login(String emailID,String pwd){
        elementControls.waitTillVisible(email,60);
        elementControls.clearText(email);
        elementControls.type(email,emailID);
        elementControls.clearText(password);
        elementControls.type(password,pwd);
        elementControls.click(sumitLogin);
    }

    public void verifyTheErrorMessage(String msg){
        elementControls.waitTillVisible(errorMsg,60);
        Assert.assertEquals(elementControls.getText(errorMsg),msg);
    }
}
