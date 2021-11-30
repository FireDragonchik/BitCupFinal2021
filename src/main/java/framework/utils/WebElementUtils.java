package framework.utils;

import framework.browser.BrowserFactory;
import framework.elements.BaseElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementUtils {

    public static Logger logger = Logger.getInstance();
    public static long DEFAULT_TIMEOUT = 80;

    public static WebElementUtils getInstance() {
        return new WebElementUtils();
    }

    public <T extends BaseElement> WebElement waitForVisibilityOf(T element, long timeoutInSeconds) {
        WebElement elementForWait = element.findElement(timeoutInSeconds);
        try {
            logger.info("Wait for visibility of element " + element.getName());
            new WebDriverWait(BrowserFactory.getInstanceDriver(), timeoutInSeconds).until(ExpectedConditions.visibilityOf(elementForWait));
        } catch (TimeoutException e) {
            logger.info("Wait for visibility of element '" + element.getName() + "' reached timeout: " + timeoutInSeconds);
        }
        return elementForWait;
    }

    public <T extends BaseElement> WebElement waitElementToBeClickable(T element, long timeoutInSeconds) {
        WebElement elementForWait = element.findElement(timeoutInSeconds);
        try {
            logger.info("Wait for visibility of element " + element.getName());
            new WebDriverWait(BrowserFactory.getInstanceDriver(), timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(elementForWait));
        } catch (TimeoutException e) {
            logger.info("Wait for visibility of element '" + element.getName() + "' reached timeout: " + timeoutInSeconds);
        }
        return elementForWait;
    }
}