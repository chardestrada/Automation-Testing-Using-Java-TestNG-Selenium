package page.module;

import java.awt.*;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

abstract class AbstractPage {

    private static WebDriver webDriver;

    static int SHORT_WAIT = 5;
    static int MEDIUM_WAIT = 10;
    static int LONG_WAIT = 15;
    static int MAX_WAIT = 60;

    AbstractPage(WebDriver webDriver) {
        AbstractPage.webDriver = webDriver;

        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, MAX_WAIT);
        PageFactory.initElements(factory, this);
    }

    AbstractPage(WebDriver webDriver, ExtentTest logger) {
        AbstractPage.webDriver = webDriver;

        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, MAX_WAIT);
        PageFactory.initElements(factory, this);
    }

    WebDriver getWebDriver() {
        return webDriver;
    }

    void setWebDriver(WebDriver webDriver) {
        AbstractPage.webDriver = webDriver;
    }

    void switchToFrameNameOrId(String text) {
        webDriver.switchTo().frame(text);
    }

    void switchToMainWindow() {
        webDriver.switchTo().defaultContent();
    }

    void closeCurrentTab() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_W);
    }

    void clearAndTypeText(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.clear();
        element.sendKeys(text);
    }

    void selectFromSelect2(WebElement element, String text) {
        element.click();
        element.findElement(By.className("select2-search__field")).sendKeys(text + Keys.ENTER);
    }

    void clickCheckBox(WebElement element) {
        element.click();
    }

    void clickOption(WebElement element) {
        element.click();
    }

    void selectFile(WebElement element, String text) {
        String filepath = System.getProperty("user.dir") + "\\" + text;
        element.clear();
        element.sendKeys(filepath);
    }

}
