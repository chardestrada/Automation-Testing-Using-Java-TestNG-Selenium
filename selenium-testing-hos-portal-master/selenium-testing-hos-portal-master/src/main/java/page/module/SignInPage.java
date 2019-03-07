package page.module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.locator.SignInLocators;

public class SignInPage extends AbstractPage implements SignInLocators {

    @FindBy(xpath = TEXT_TITLE)
    private WebElement titleText;

    @FindBy(css = TEXT_NOTIFICATION)
    private WebElement notification;

    @FindBy(name = FIELD_USERNAME)
    private WebElement username;

    @FindBy(name = FIELD_PASSWORD)
    private WebElement password;

    @FindBy(id = BUTTON_SIGN_IN)
    private WebElement signin;

    public SignInPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getLoginTitle(){
        return titleText.getText();
    }

    public String getLoginNotification(){
        return notification.getText();
    }

    private void typeUserName(String text) {
        clearAndTypeText(username, text);
    }

    private void typePassword(String text) {
        clearAndTypeText(password, text);
    }

    private DashboardPage clickSignIn() {
        signin.click();
        return new DashboardPage(getWebDriver());
    }

    public DashboardPage enterCredentialsAndSignIn(String username, String password) {
        typeUserName(username);
        typePassword(password);
        return clickSignIn();
    }


}
