package Utils;


import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/***
 * @author Arunkumar C
 */
public class PageControls {
    private static Logger logger = Logger.getLogger(PageControls.class);

    public void launchBrowser(String browser) {
        WebDriverManager.setDriver(browser);
        logger.info(browser + " is launched");

    }

    public void launchBrowser() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverManager.setDriver(browser);
        logger.info(browser + " is launched");
    }

    public String getUrl() {
        String returnUrl = WebDriverManager.getDriver().getCurrentUrl();
        logger.info("Get URL invoked: get url is " + returnUrl);
        return returnUrl;
    }

    public void loadURL(String url) {
        WebDriverManager.getDriver().get(url);
        logger.info("URL " + url + " is loaded");
    }

    private void waitForAlertPopup(int timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), timeout);
        wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Waiting for alert to get present");
    }

    public void acceptAlert(int timeout) {
        waitForAlertPopup(timeout);
        WebDriverManager.getDriver().switchTo().alert().accept();
        logger.info("Alert is been accepted");
    }

    public void declineAlert(int timeout) {
        waitForAlertPopup(timeout);
        WebDriverManager.getDriver().switchTo().alert().dismiss();
        logger.info("Alert is been declined");
    }

    public String getAlertText(int timeout) {
        waitForAlertPopup(timeout);
        String text = WebDriverManager.getDriver().switchTo().alert().getText();
        logger.info("Alert text is: " + text);
        return text;
    }

    public String getTitle() {
        String title = WebDriverManager.getDriver().getTitle();
        logger.info("Title of the current page is: " + title);
        return title;
    }

    public void closeBrowser() {
        WebDriverManager.getDriver().close();
        logger.info("Current browser tab is closed");
    }

    public void quitBrowser() {
        WebDriverManager.getDriver().quit();
        logger.info("Browser is closed");
    }

    public byte[] takeScreenShot() {
        byte[] screeshot = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        logger.info("Screenshot is captured");
        return screeshot;
    }

    public void maximizeWindow() {
        WebDriverManager.getDriver().manage().window().maximize();
        logger.info("Browser window is maximized");
    }

    public void setWindowSize(int x, int y) {
        WebDriverManager.getDriver().manage().window().setSize(new Dimension(x, y));
        logger.info("Browser window is maximized");
    }

    public void refreshPage() {
        WebDriverManager.getDriver().navigate().refresh();
        logger.info("Current page is refreshed");
    }

    public void implicitWait(int timeInSec) {
        WebDriverManager.getDriver().manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
        logger.info("Implicit wait is been set to " + timeInSec + " seconds");
    }

    public void removeImplicitWait() {
        WebDriverManager.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        logger.info("Implicit wait is been removed");

    }

    public void pageLoadTimeout(int timeInSec) {
        WebDriverManager.getDriver().manage().timeouts().pageLoadTimeout(timeInSec, TimeUnit.SECONDS);
        logger.info("Page time out is been set to " + timeInSec + " seconds");
    }

    public void fullScreen() {
        WebDriverManager.getDriver().manage().window().fullscreen();
        logger.info("Browser window screen is made to full screen");
    }

    public void deleteAllCookies() {
        WebDriverManager.getDriver().manage().deleteAllCookies();
        logger.info("Deleted all the existing cookies");
    }

    public void deleteCookie(Cookie cookie) {
        WebDriverManager.getDriver().manage().deleteCookie(cookie);
        logger.info("Deleted the " + cookie.toJson().toString() + " cookie");
    }

    public void setCookie(Cookie cookie) {
        WebDriverManager.getDriver().manage().addCookie(cookie);
        logger.info("Deleted the " + cookie.toJson().toString() + " cookie");
    }

    public String getEntirePageSource() {
        String source = WebDriverManager.getDriver().getPageSource();
        logger.info("Page source code: " + source);
        return source;
    }

    public void navigateToMainWindow() {
        Set<String> windowHandles = WebDriverManager.getDriver().getWindowHandles();
        String mainWindow = windowHandles.iterator().next();
        WebDriverManager.getDriver().switchTo().window(mainWindow);
        logger.info("Driver is switched to main window:" + mainWindow);
    }

    public void navigateToLastChildWindow() {
        Set<String> windowHandles = WebDriverManager.getDriver().getWindowHandles();
        Iterator<String> handles = windowHandles.iterator();
        String lastWindow = new String();
        while (handles.hasNext()) {
            lastWindow = handles.next();
        }
        WebDriverManager.getDriver().switchTo().window(lastWindow);
        logger.info("Driver is switched to last window:" + lastWindow);
    }

    public void navigateToWindow(int windowNumber) {
        Set<String> windowHandles = WebDriverManager.getDriver().getWindowHandles();
        Iterator<String> handles = windowHandles.iterator();
        String lastWindow = new String();
        while (handles.hasNext() && windowNumber > 0) {
            lastWindow = handles.next();
            windowNumber--;
        }
        WebDriverManager.getDriver().switchTo().window(lastWindow);
        logger.info("Driver is switched to " + windowNumber + " window:" + lastWindow);
    }


    public void scrollToBottomPage() {
        JavascriptExecutor jsExcutor = (JavascriptExecutor) WebDriverManager.getDriver();
        jsExcutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        logger.info("Scrolling to bottom of the page");
    }

    public void setLocalStorage(String key,String value){
        LocalStorage localStorage=((WebStorage) WebDriverManager.getDriver()).getLocalStorage();
        localStorage.setItem(key,value);
        logger.info("Setting the local storage as key:"+key+" value:"+value);
    }

}
