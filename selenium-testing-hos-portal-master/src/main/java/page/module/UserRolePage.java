package page.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.locator.UserRoleLocators;

public class UserRolePage extends DashboardPage implements UserRoleLocators {

    @FindBy(xpath = TITLE)
    private WebElement tabTitle;

    @FindBy(xpath = TOAST_MESSAGE)
    private WebElement toastMessage;

    @FindBy(xpath = ACTIONS_BUTTON_ADD_USERROLE)
    private WebElement buttonAddUserRole;

    @FindBy(css = DIALOG_CONTAINER)
    private WebElement dialog;

    @FindBy(xpath = ADD_EDIT_DIALOG_FIELD_NAME)
    private WebElement dialogName;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_CATEGORY_USERS)
    private WebElement dialogUsers;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_PERM_ACCOUNT_ADMIN)
    private WebElement dialogAccountAdmin;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_PERM_VIEW_ALL_USERS)
    private WebElement dialogViewAllUsers;

    @FindBy(css = ADD_EDIT_DIALOG_SLIMSCROLL_AREA)
    private WebElement scrollArea;

    @FindBy(css = ADD_EDIT_DIALOG_SLIMSCROLL_BAR)
    private WebElement scrollBar;

    @FindBy(xpath = ADD_EDIT_DIALOG_BUTTON_SAVE)
    private WebElement dialogSave;

    @FindBy(xpath = DELETE_DIALOG_BUTTON_DELETE)
    private WebElement dialogDelete;

    @FindBy(css = DIALOG_BUTTON_CANCEL)
    private WebElement dialogCancel;

    public UserRolePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getCurrentTab() {
        return TITLE;
    }

    public String getTabTitle(){
        return tabTitle.getText();
    }

    public List<WebElement> getAllUserRoleRowsFromTable(){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(TOAST_MESSAGE)));

        return getWebDriver().findElements(By.xpath(TABLE_ROWS));
    }

    public WebElement getUserRoleRowByName(String userRoleName){
        List<WebElement> userRoleRows = getAllUserRoleRowsFromTable();

        for (WebElement userRoleRow : userRoleRows) {
            WebElement userRoleRowName = userRoleRow.findElement(By.xpath("td[1]"));
            if (userRoleRowName.getText().equalsIgnoreCase(userRoleName)) {
                return userRoleRow;
            }
        }
        return null;
    }

    public ArrayList<String> getAllUserRoleNames(){
        List<WebElement> userRoleRows = getAllUserRoleRowsFromTable();
        ArrayList<String> userRoleNames = new ArrayList<>();

        for (WebElement userRoleRow : userRoleRows) {
            WebElement userRoleRowName = userRoleRow.findElement(By.xpath("td[1]"));
            userRoleNames.add(userRoleRowName.getText());
        }
        return userRoleNames;
    }

    private List<WebElement> getAllPremissionCheckboxesFromDialog(){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);

        return getWebDriver().findElements(By.xpath(ADD_EDIT_DIALOG_CHECKBOXES));
    }

    public HashMap<String, Boolean> getAllPermissionNamesAndValues(){
        HashMap<String, Boolean> permissionsMap = new HashMap<>();
        for (int scrolls = 0; scrolls < 5 ; scrolls++) {

            List<WebElement> permissionCheckboxes = getAllPremissionCheckboxesFromDialog();
            for (WebElement permissionCheckbox : permissionCheckboxes) {
                WebElement permissionLabel = permissionCheckbox.findElement(By.xpath("following-sibling::*[1]"));
                if (!permissionLabel.getText().equals("") && permissionLabel.getText() != null) {
                    permissionsMap.put(permissionLabel.getText(), permissionCheckbox.isSelected());
                }
            }

            if (permissionsMap.size() < 50) {
                scrollDialog(scrolls == 0, true, 150);
            }
        }
        return permissionsMap;
    }

    public void typeName(String text) {
        clearAndTypeText(dialogName, text);
    }

    public void clickCategoryUsers() {
        clickCheckBox(dialogUsers);
    }

    public void clickPermissionAccountAdmin() {
        clickCheckBox(dialogAccountAdmin);
    }

    public void clickPermissionViewAllUsers() {
        clickCheckBox(dialogViewAllUsers);
    }

    private void waitUntilDialogIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), SHORT_WAIT);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector(DIALOG_CONTAINER)).getCssValue("opacity").equals("1");
            }
        });
    }

    private void waitUntilDialogIsGone() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), SHORT_WAIT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(DIALOG_CONTAINER)));
    }

    public void scrollDialog(boolean firstTime, boolean isDownward, int scrollPoints) {
        if (firstTime) {
            dialogName.click();
        }

        if (getWebDriver().findElement(By.cssSelector(ADD_EDIT_DIALOG_SLIMSCROLL_BAR)).isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), SHORT_WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ADD_EDIT_DIALOG_SLIMSCROLL_BAR)));

            Actions dragger = new Actions((getWebDriver()));
            int pixelIncrement = isDownward ? 10 : -10;

            for (int i = 10; i < scrollPoints; i = i + 10) {
                dragger.moveToElement(scrollBar).clickAndHold().moveByOffset(0, pixelIncrement).release(scrollBar).build().perform();
            }
        }
    }

    public boolean isDialogSaveButtonEnabled() {
        return dialogSave.isEnabled();
    }

    public void clickOnDialogSaveButton() {
        dialogSave.click();
    }

    public void clickOnDialogDeleteButton() {
        dialogDelete.click();
    }

    public void clickOnDialogCancelButton() {
        dialogCancel.click();

        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ACTIONS_BUTTON_ADD_USERROLE)));
    }

    public void clickOnAddUserRole() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACTIONS_BUTTON_ADD_USERROLE)));

        waitUntilTableHasLoaded();

        buttonAddUserRole.click();
        waitUntilDialogIsDisplayed();
    }

    public void clickOnEditUserRole(WebElement userRoleRow) {
        WebElement userRoleEdit = userRoleRow.findElement(By.xpath(TABLE_ROW_BUTTON_EDIT));

        userRoleEdit.click();
        waitUntilDialogIsDisplayed();
    }

    public void clickOnDeleteUserRole(WebElement userRoleRow) {
        waitUntilDialogIsGone();

        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        WebElement userRoleDelete = wait.until(ExpectedConditions.elementToBeClickable(userRoleRow.findElement(By.xpath(TABLE_ROW_BUTTON_DELETE))));

        userRoleDelete.click();
    }
}
