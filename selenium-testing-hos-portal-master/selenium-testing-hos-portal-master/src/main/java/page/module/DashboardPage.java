package page.module;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.locator.DashBoardLocators;

public class DashboardPage extends AbstractPage implements DashBoardLocators {

    @FindBy(xpath = HEADER_TEXT_USERNAME)
    private WebElement username;

    @FindBy(xpath = HEADER_BUTTON_REFRESH)
    private WebElement refresh;

    @FindBy(xpath = HEADER_BUTTON_SIGN_OUT)
    private WebElement signout;

    @FindBy(xpath = TOAST_MESSAGE)
    private WebElement toastMessage;

    @FindBy(xpath = DEFAULT_TAB_TITLE)
    private WebElement tabTitle;

    @FindBy(xpath = MENU_SETTINGS)
    private WebElement menuSettings;

    @FindBy(xpath = MENU_SETTINGS_USERS_TAB)
    private WebElement menuSettingsUsersTab;

    @FindBy(xpath = MENU_SETTINGS_USER_ROLES_TAB)
    private WebElement menuSettingsUserRolesTab;

    @FindBy(xpath = MENU_SETTINGS_TERMINALS_TAB)
    private WebElement menuSettingsTerminalsTab;

    @FindBy(xpath = TABLE_LOADING_ROWS)
    private WebElement tableLoadingRows;

    public DashboardPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getDashboardUsername(){
        return username.getText();
    }

    public String getCurrentTab() {
        return DEFAULT_TAB_TITLE;
    }

    public void closeExportTab() {
        closeCurrentTab();
    }

    public SignInPage clickSignOut() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.and(
            ExpectedConditions.visibilityOfElementLocated(By.xpath(getCurrentTab())),
            ExpectedConditions.invisibilityOfElementLocated(By.xpath(TOAST_MESSAGE)),
            ExpectedConditions.elementToBeClickable((By.xpath(HEADER_BUTTON_SIGN_OUT)))
        ));

        signout.click();
        return new SignInPage(getWebDriver());
    }

    public String getTabToastMessage(){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), SHORT_WAIT);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return toastMessage.getCssValue("opacity").equals("1");
            }
        });
        return toastMessage.getText();
    }

    public void clickTabToastMessage(){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), SHORT_WAIT);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return toastMessage.getCssValue("opacity").equals("1");
            }
        });
        toastMessage.click();
    }

    public UserManagementPage navigateToUsersTab() {
        menuSettings.click();

        WebDriverWait wait = new WebDriverWait(getWebDriver(), SHORT_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MENU_SETTINGS_USERS_TAB)));

        menuSettingsUsersTab.click();
        return new UserManagementPage(getWebDriver());
    }

    public UserRolePage navigateToUserRolesTab() {
        menuSettings.click();

        WebDriverWait wait = new WebDriverWait(getWebDriver(), SHORT_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MENU_SETTINGS_USER_ROLES_TAB)));

        menuSettingsUserRolesTab.click();
        return new UserRolePage(getWebDriver());
    }

    public TerminalPage navigateToTerminalsTab() {
        menuSettings.click();

        WebDriverWait wait = new WebDriverWait(getWebDriver(), SHORT_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MENU_SETTINGS_TERMINALS_TAB)));

        menuSettingsTerminalsTab.click();
        return new TerminalPage(getWebDriver());
    }

    public void clickRefresh() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(getCurrentTab())),
                ExpectedConditions.invisibilityOfElementLocated(By.xpath(TOAST_MESSAGE)),
                ExpectedConditions.elementToBeClickable((By.xpath(HEADER_BUTTON_REFRESH)))
        ));

        refresh.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TABLE_LOADING_ROWS)));
    }

    public void waitUntilTableHasLoaded() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TABLE_LOADING_ROWS)));
    }

}
