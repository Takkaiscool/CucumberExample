package Utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/***
 * @author Arunkumar C
 */

public class ElementControls {
    private Logger logger = Logger.getLogger(ElementControls.class);

    public void type(WebElement element, String data) {
        element.sendKeys(data);
        logger.info("Entering the value: " + data + " to the field: " + element.toString());
    }

    public void typeAndEnter(WebElement element, String data) {
        element.sendKeys(data + Keys.ENTER);
        logger.info("Entering the value: " + data + " and pressed the enter to the field: " + element.toString());
    }

    public void typeAndTab(WebElement element, String data) {
        element.sendKeys(data + Keys.TAB);
        logger.info("Entering the value: " + data + " and pressed the tab to the field: " + element.toString());
    }

    public void click(WebElement element) {
        element.click();
        logger.info("Clicked on the element:" + element.toString());
    }

    public String getText(WebElement element) {
        String textValue = element.getText();
        logger.info("Tag text value of element: " + element.toString() + " is: " + textValue);
        return textValue;

    }

    public String getTextFieldData(WebElement element) {
        String textFieldValue = element.getAttribute("value");
        logger.info("Text field value of element: " + element.toString() + " is: " + textFieldValue);
        return textFieldValue;
    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            logger.error("Interrupted exception");
        }
    }


    public String getAttribute(WebElement element, String attribute) {
        String attributeValue = element.getAttribute(attribute);
        logger.info(attribute + " value of element: " + element.toString() + " is: " + attributeValue);
        return attributeValue;
    }

    public void waitTillVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        logger.info("Waiting for the element: " + element.toString() + " to be visible within " + timeout + " seconds");
    }

    public void waitTillInteract(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        logger.info("Waiting for the element: " + element.toString() + " to be visible within " + timeout + " seconds");
    }

    public void waitTillInVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), timeout);
        wait.until(ExpectedConditions.invisibilityOf(element));
        logger.info("Waiting for the element: " + element.toString() + " to be invisible within " + timeout + " seconds");
    }



    public void waitTillUnPresence(By element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), timeout);
        wait.until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver webDriver) {
                try {
                    return webDriver.findElements(element).size() == 0;
                } catch (StaleElementReferenceException exception) {
                    return webDriver.findElements(element).size() == 0;
                }

            }
        });
        logger.info("Waiting for the element: " + element.toString() + " to be unpresent in the dom within " + timeout + " seconds");
    }

    public void waitTillPresence(By element, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        logger.info("Waiting for the element: " + element.toString() + " to be present in the dom within " + timeout + " seconds");
    }

    public void selectDropDownByIndex(WebElement element, int index) {
        Select sel = new Select(element);
        sel.selectByIndex(index);
        logger.info("Selecting the value from the dropdown: " + element.toString() + " by using index value:" + index);
    }

    public void selectDropDownByValue(WebElement element, String value) {
        Select sel = new Select(element);
        sel.selectByValue(value);
        logger.info("Selecting the value from the dropdown: " + element.toString() + " by using value attribute:" + value);
    }

    public void selectDropDownByVisibleText(WebElement element, String value) {
        Select sel = new Select(element);
        sel.selectByVisibleText(value);
        logger.info("Selecting the value from the dropdown: " + element.toString() + " by using visible text:" + value);
    }

    public void deselectDropDownByIndex(WebElement element, int index) {
        Select sel = new Select(element);
        sel.deselectByIndex(index);
        logger.info("Deselecting the value from the dropdown: " + element.toString() + " by using index value:" + index);
    }

    public void deselectDropDownByValue(WebElement element, String value) {
        Select sel = new Select(element);
        sel.deselectByValue(value);
        logger.info("Selecting the value from the dropdown: " + element.toString() + " by using value attribute:" + value);
    }

    public void deselectDropDownByVisibleText(WebElement element, String value) {
        Select sel = new Select(element);
        sel.deselectByVisibleText(value);
        logger.info("Selecting the value from the dropdown: " + element.toString() + " by using visible text:" + value);
    }

    public void rightClick(WebElement element) {
        Actions act = new Actions(WebDriverManager.getDriver());
        act.contextClick(element).build().perform();
        logger.info("Performing right click on element:" + element.toString());
    }

    public void rightClick() {
        Actions act = new Actions(WebDriverManager.getDriver());
        act.contextClick().build().perform();
        logger.info("Performing right click");
    }

    public void moveToElement(WebElement element) {
        Actions act = new Actions(WebDriverManager.getDriver());
        act.moveToElement(element).build().perform();
        logger.info("Moving mouse pointer to the element:" + element.toString());
    }

    public void moveToElement(WebElement element, int x, int y) {
        Actions act = new Actions(WebDriverManager.getDriver());
        act.moveToElement(element, x, y).build().perform();
        logger.info("Moving mouse pointer to the element:" + element.toString() + " by offset x:" + x + " and y:" + y);
    }

    public void dragAndDrop(WebElement drag, WebElement drop) {
        Actions act = new Actions(WebDriverManager.getDriver());
        act.dragAndDrop(drag, drop).build().perform();
        logger.info("Dragging the mouse from element: " + drag.toString() + " to element:" + drop.toString());
    }

    public void dragAndDrop(WebElement drag, int x, int y) {
        Actions act = new Actions(WebDriverManager.getDriver());
        act.dragAndDropBy(drag, x, y).build().perform();
        logger.info("Dragging the mouse from element: " + drag.toString() + " to offset x:" + x + " and y:" + y);
    }

    public void uploadFile(WebElement element, String filePath) {
        element.sendKeys(filePath);
        logger.info("Uploading the file from the path: " + filePath + " to the element:" + element.toString());
    }

    public boolean isVisible(WebElement element) {
        boolean isDisplayed = element.isDisplayed();
        logger.info("Element: " + element.toString() + " is displayed: " + isDisplayed);
        return isDisplayed;

    }

    public boolean isPresent(By element) {
        boolean isDisplayed = WebDriverManager.getDriver().findElements(element).size() > 0;
        logger.info("Element: " + element.toString() + " is present: " + isDisplayed);
        return isDisplayed;

    }

    public boolean isEnabled(WebElement element) {
        boolean isEnabled = element.isEnabled();
        logger.info("Element: " + element.toString() + " is enabled: " + isEnabled);
        return isEnabled;
    }

    public boolean isSelected(WebElement element) {
        boolean isSelected = element.isSelected();
        logger.info("Element: " + element.toString() + " is selected: " + isSelected);
        return element.isSelected();
    }

    public WebElement find(By locator) {
        WebElement element = WebDriverManager.getDriver().findElement(locator);
        logger.info("Found the element by locator:" + locator);
        return element;
    }

    public List<WebElement> finds(By locator) {
        List<WebElement> elements = WebDriverManager.getDriver().findElements(locator);
        logger.info("Found the elements by locator:" + locator);
        return elements;
    }

    public void scrollToElement(WebElement element) {
        moveToElement(element);
        logger.info("Scrolling to element:" + element.toString());
    }

    public void scrollToElementUsingJSExecutor(WebElement element) {
        ((JavascriptExecutor) WebDriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
        logger.info("Scrolling to element:" + element.toString() + " Using JavaScript Executor");
    }

    public void waitTillTextPresentInTextField(By locator, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), timeout);
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
        logger.info("Waiting till the text: " + text + " is present in field:" + locator.toString() + " within " + timeout + " seconds");
    }

    public String getCssValue(WebElement element, String attribute) {
        String cssValue = element.getCssValue(attribute);
        logger.info("Getting css value for element " + element.toString() + " of attribute " + attribute + " is " + cssValue);
        return cssValue;

    }

    public void clearText(WebElement element) {
        element.clear();
        logger.info("Clear the data in the field " + element.toString());
    }

    public void clearTextForRect(WebElement element) {
        element.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        logger.info("Clear the data in the field " + element.toString());
    }

}
