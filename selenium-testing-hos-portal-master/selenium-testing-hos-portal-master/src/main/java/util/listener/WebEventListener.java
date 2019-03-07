package util.listener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import util.logging.Log;

public class WebEventListener extends AbstractWebDriverEventListener {

    public void beforeNavigateTo(String url, WebDriver driver) {
        Log.info("Before navigating to: '" + url + "'");
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        Log.info("Navigated to:' " + url + "'");
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        Log.info("Value of the: " + element.toString()
                + " before any changes made");
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        Log.info("Element value changed to: " + element.toString());
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        Log.info("Trying to click on: " + element.toString());
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
        Log.info("Clicked on: " + element.toString());
    }

    public void beforeNavigateBack(WebDriver driver) {
        Log.info("Navigating back to previous page");
    }

    public void afterNavigateBack(WebDriver driver) {
        Log.info("Navigated back to previous page");
    }

    public void beforeNavigateForward(WebDriver driver) {
        Log.info("Navigating forward to next page");
    }

    public void afterNavigateForward(WebDriver driver) {
        Log.info("Navigated forward to next page");
    }

    public void onException(Throwable error, WebDriver driver) {
        Log.info("Exception occured: " + error);
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        Log.info("Trying to find Element By: " + by.toString());
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        Log.info("Found Element By: " + by.toString());
    }

    /*
     * non overridden methods of WebListener class
     */
    public void beforeScript(String script, WebDriver driver) {
    }

    public void afterScript(String script, WebDriver driver) {
    }
}
