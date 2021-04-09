package stepDefinitions;

import PageObject.*;
import Utils.PageControls;
import Utils.PropertiesFileReader;
import Utils.WebDriverManager;
import com.google.inject.Inject;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Assessment {

    @Inject
    private HomePage homePage;
    @Inject
    private AuthenticationPage authenticationPage;

    @Inject
    private ProductListingPage productListingPage;


    @Inject
    private ShoppingCartPage shoppingCartPage;

    @Inject
    private ProductDetailsPage productDetailsPage;


    private static List<String> itemValues = new ArrayList<>();
    private static List<String> itemNames = new ArrayList<>();
    PageControls pageControls=new PageControls();

    @Given("^I open the application$")
    public void iOpenTheApplication() {
        PageControls pageControls = new PageControls();
        PropertiesFileReader propertiesFileReader = new PropertiesFileReader("./ApplicationSettings.properties");
        propertiesFileReader.readFile();
        System.setProperty("webdriver.chrome.driver", PropertiesFileReader.properties.getProperty("Driver.Chrome"));
        pageControls.launchBrowser(PropertiesFileReader.properties.getProperty("Browser"));
        pageControls.implicitWait(20);
        pageControls.maximizeWindow();
        pageControls.loadURL(PropertiesFileReader.properties.getProperty("Application.URL"));

    }

    @Then("^I verify your logo new experience is displayed$")
    public void iVerifyYouLogoNewExperienceIsDisplayed() {
        homePage.verifyLogoIsPresent();
    }

    @Then("^I click on signin link$")
    public void iClickOnSigninLink() {
        homePage.clickOnSignIn();
    }

    @And("^I enter valid username \"(.*)\" and password \"(.*)\"$")
    @And("^I enter invalid username \"(.*)\" and password \"(.*)\"$")
    public void iEnterInvalidUsernameAndPassword(String username, String password) {
        authenticationPage.login(username, password);
    }

    @Then("^I verify the error message as \"(.*)\"$")
    public void iVerifyTheErrorMessageAs(String errorMsg) {
        authenticationPage.verifyTheErrorMessage(errorMsg);
    }

    @Then("^I verify user tab of name \"(.*)\" is displayed$")
    public void iVerifyUserTabOfNameIsDisplayed(String tab) {
        homePage.verifyUserNameAs(tab);
    }

    @When("^I move mouse to \"(.*)\"$")
    public void iMoveMouseToMenu(String menu) {
        homePage.moveToTheMenu(menu);
    }

    @Then("^I click on \"(.*)\"$")
    public void iClickOnSubMenu(String subMenu) {
        homePage.moveToSubMenu(subMenu);
    }

    @And("^I select the list view$")
    public void iSelectTheListView() {
        productListingPage.selectListView();
    }

    @Then("^I add \"(.*)\" item to the cart$")
    public void iAddItemToTheCart(String item) throws InterruptedException {
        int index = Integer.parseInt(item.substring(0, 1));
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), 60);
        itemNames.add(productListingPage.itemName.get(index - 1).getText());
        itemValues.add(productListingPage.itemPrice.get(index - 1).getText());
        System.out.println(itemValues);
        System.out.println(itemNames);
        productListingPage.addItemToTheCart(index);
        productListingPage.clickOnContinueShopping();
        Thread.sleep(3000);
    }

    @Then("^I verify number of items in cart is \"(.*)\"$")
    public void iVerifyNumberOfItemsInCartIs(String items) {
        productListingPage.verifyCartQuantity(items);
    }

    @When("^I checkout the products$")
    public void iCheckoutTheProducts() {
        productListingPage.moveCartQuantity();
        productListingPage.clickOnOrderCart();
    }

    @Then("^I verify the product name and price in order summary$")
    public void iVerifyTheProductNameAndPriceInOrderSummary() {
        shoppingCartPage.verifyProductNameAndPrice(itemNames,itemValues);
    }

    @Then("^I click on proceed to checkout$")
    public void iClickOnProceedToCheckout() throws InterruptedException {
        productDetailsPage.clickOnProceedToCheckout();
    }

    @Then("^I verify application is in Addresses tab$")
    public void iVerifyApplicationIsInAddressesTab() {
        shoppingCartPage.verifyApplicationIsInAddressTab();
    }

    @Then("^I verify application is in Shipping tab$")
    public void iVerifyApplicationIsInShippingTab() {
        shoppingCartPage.verifyApplicationIsInShippingTab();
    }

    @And("^I verify terms and condition is not selected$")
    public void iVerifyTermsAndConditionIsNotSelected() {
        shoppingCartPage.verifyTermsAndConditionIsNotSelected();
    }

    @Then("^I click on terms and condition checkbox$")
    public void iClickOnTermsAndConditionCheckBox() {
        shoppingCartPage.clickOnTC();
    }

    @Then("^I verify the total amount$")
    public void iVerifyTheTotalAmount() {
        shoppingCartPage.verifyTheTotalAmount();
    }

    @Then("^I verify pay by bank and pay by wire option is available$")
    public void iVerifyPayByBankAndPayByWireOptionIsAvailable() {
        shoppingCartPage.verifyPayByCheckIsDisplayed();
        shoppingCartPage.verifyPayByBankIsDisplayed();
    }

    @Then("^I click on proceed to checkout in address$")
    public void iClickOnProceedToCheckoutInAddress() throws InterruptedException {
       shoppingCartPage.clickOnCheckoutInAddr();
    }

    @Then("^I click on proceed to checkout in shipping")
    public void iClickOnProceedToCheckoutInShipping() throws InterruptedException {
        shoppingCartPage.clickOnCheckoutInShipping();
    }

    @And("^I close the browser$")
    public void iCloseTheBrowser() {
        WebDriverManager.getDriver().quit();
    }

    @AfterStep
    public void checkFailureAndCaptureScreenShot(Scenario scenario){
        if(scenario.isFailed()){
            byte[] screeshot = pageControls.takeScreenShot();
            scenario.attach(screeshot,"img/png","eachstep");
        }
    }
}
