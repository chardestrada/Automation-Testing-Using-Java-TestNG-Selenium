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

import page.locator.UserManagementLocators;

public class UserManagementPage extends DashboardPage implements UserManagementLocators {

    @FindBy(xpath = TITLE)
    private WebElement tabTitle;

    @FindBy(xpath = TOAST_MESSAGE)
    private WebElement toastMessage;

    @FindBy(id = ACTIONS_BUTTON_ADD_USER)
    private WebElement buttonAddUser;

    @FindBy(id = ACTIONS_BUTTON_EXPORT_USERS)
    private WebElement buttonExportUsers;

    @FindBy(id = FIELD_SEARCH_BOX)
    private WebElement fieldSearchBox;

    @FindBy(css = DIALOG_CONTAINER)
    private WebElement dialog;

    @FindBy(id = ADD_EDIT_DIALOG_CHECKBOX_STATUS)
    private WebElement dialogStatus;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_STATUS_LABEL)
    private WebElement dialogStatusLabel;

    @FindBy(id = ADD_EDIT_DIALOG_FIELD_FIRSTNAME)
    private WebElement dialogFirstName;

    @FindBy(id = ADD_EDIT_DIALOG_FIELD_LASTNAME)
    private WebElement dialogLastName;

    @FindBy(id = ADD_EDIT_DIALOG_FIELD_SUFFIX)
    private WebElement dialogSuffix;

    @FindBy(id = ADD_EDIT_DIALOG_FIELD_EMAIL)
    private WebElement dialogEmail;

    @FindBy(id = ADD_EDIT_DIALOG_FIELD_CONFIRM_EMAIL)
    private WebElement dialogConfirmEmail;

    @FindBy(id = ADD_EDIT_DIALOG_FIELD_PASSWORD)
    private WebElement dialogPassword;

    @FindBy(id = ADD_EDIT_DIALOG_FIELD_CONFIRM_PASSWORD)
    private WebElement dialogConfirmPassword;

    @FindBy(id = ADD_EDIT_DIALOG_CHECKBOX_VERIFY_EMAIL)
    private WebElement dialogVerifyEmail;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_VERIFY_EMAIL_LABEL)
    private WebElement dialogVerifyEmailLabel;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_VISIBILITY_SET1)
    private WebElement dialogVisibilitySet1;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_VISIBILITY_SET1_LABEL)
    private WebElement dialogVisibilitySet1Label;

    @FindBy(id = ADD_EDIT_DIALOG_CHECKBOX_AUTHORIZED_MAIN_OFFICE)
    private WebElement dialogAuthorizedMainOffice;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_AUTHORIZED_MAIN_OFFICE_LABEL)
    private WebElement dialogAuthorizedMainOfficeLabel;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_ACCOUNTADMIN_LABEL)
    private WebElement dialogUserRoleAccountAdminLabel;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_FLEETMANAGER_LABEL)
    private WebElement dialogUserRoleFleetManagerLabel;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_USERADMIN_LABEL)
    private WebElement dialogUserRoleUserAdminLabel;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_ASSETADMIN_LABEL)
    private WebElement dialogUserRoleAssetAdminLabel;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_WORKORDERASSIGNEE_LABEL)
    private WebElement dialogUserRoleWorkOrederAssigneeLabel;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_USER_VIEW_ONLY_LABEL)
    private WebElement dialogUserRoleUserViewOnlyLabel;

    @FindBy(css = ADD_EDIT_DIALOG_SLIMSCROLL_AREA)
    private WebElement scrollArea;

    @FindBy(css = ADD_EDIT_DIALOG_SLIMSCROLL_BAR)
    private WebElement scrollBar;

    @FindBy(xpath = ADD_EDIT_DIALOG_BUTTON_SAVE)
    private WebElement dialogSave;

    @FindBy(xpath = EXPORT_DIALOG_OPTION_LABEL)
    private WebElement dialogCSVOption;

    @FindBy(id = EXPORT_DIALOG_BUTTON_GENERATE)
    private WebElement dialogGenerate;

    @FindBy(xpath = DELETE_DIALOG_BUTTON_DELETE)
    private WebElement dialogDelete;

    @FindBy(css = DIALOG_BUTTON_CANCEL)
    private WebElement dialogCancel;

    public UserManagementPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getCurrentTab() {
        return TITLE;
    }

    public String getTabTitle(){
        return tabTitle.getText();
    }

    public List<WebElement> getAllUserRowsFromTable(){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(TOAST_MESSAGE)));

        return getWebDriver().findElements(By.xpath(TABLE_ROWS));
    }

    public WebElement getUserRowByName(String userName){
        List<WebElement> userRows = getAllUserRowsFromTable();

        for (WebElement userRow : userRows) {
            WebElement userRowName = userRow.findElement(By.xpath("td[1]"));
            if (userRowName.getText().equalsIgnoreCase(userName)) {
                return userRow;
            }
        }
        return null;
    }

    public ArrayList<String> getAllUserNames(){
        List<WebElement> userRows = getAllUserRowsFromTable();
        ArrayList<String> userNames = new ArrayList<>();

        for (WebElement userRow : userRows) {
            WebElement userRowName = userRow.findElement(By.xpath("td[1]"));
            userNames.add(userRowName.getText());
        }
        return userNames;
    }

    private List<WebElement> getAllUserRoleCheckboxesFromDialog(){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);

        return getWebDriver().findElements(By.xpath(ADD_EDIT_DIALOG_CHECKBOX_USER_ROLES));
    }

    public HashMap<String, Boolean> getAllUserRoleNamesAndValues(){
        HashMap<String, Boolean> userRolesMap = new HashMap<>();
        for (int scrolls = 0; scrolls < 5 ; scrolls++) {

            List<WebElement> userRoleCheckboxes = getAllUserRoleCheckboxesFromDialog();
            for (WebElement userRoleCheckbox : userRoleCheckboxes) {
                WebElement userRoleLabel = userRoleCheckbox.findElement(By.xpath("following-sibling::*[1]"));
                if (!userRoleLabel.getText().equals("") && userRoleLabel.getText() != null) {
                    userRolesMap.put(userRoleLabel.getText(), userRoleCheckbox.isSelected());
                }
            }

            if (userRolesMap.size() < 6) {
                scrollDialog(scrolls == 0, true, 100);
            }
        }
        return userRolesMap;
    }

    public void clickStatus() {
        clickCheckBox(dialogStatusLabel);
    }

    public void typeFirstName(String text) {
        clearAndTypeText(dialogFirstName, text);
    }

    public void typeLastName(String text) {
        clearAndTypeText(dialogLastName, text);
    }

    public void typeSuffix(String text) {
        clearAndTypeText(dialogSuffix, text);
    }

    public void typeEmail(String text) {
        clearAndTypeText(dialogEmail, text);
    }

    public void typeConfirmEmail(String text) {
        clearAndTypeText(dialogConfirmEmail, text);
    }

    public void typePassword(String text) {
        clearAndTypeText(dialogPassword, text);
    }

    public void typeConfirmPassword(String text) {
        clearAndTypeText(dialogConfirmPassword, text);
    }

    public void clickVerifyEmail() {
        clickCheckBox(dialogVerifyEmailLabel);
    }

    public void clickVisibilitySet1() {
        clickCheckBox(dialogVisibilitySet1Label);
    }

    public void clickAuthorizedMainOffice() {
        clickCheckBox(dialogAuthorizedMainOfficeLabel);
    }

    public void clickUserRoleAccountAdmin() {
        clickCheckBox(dialogUserRoleAccountAdminLabel);
    }

    public void clickUserRoleFleetManager() {
        clickCheckBox(dialogUserRoleFleetManagerLabel);
    }

    public void clickUserRoleUserAdmin() {
        clickCheckBox(dialogUserRoleUserAdminLabel);
    }

    public void clickUserRoleAssetAdmin() {
        clickCheckBox(dialogUserRoleAssetAdminLabel);
    }

    public void clickUserRoleWorkOrderAssignee() {
        clickCheckBox(dialogUserRoleWorkOrederAssigneeLabel);
    }

    public void clickUserRoleUserViewOnly() {
        clickCheckBox(dialogUserRoleUserViewOnlyLabel);
    }

    public void clickCSVOption() {
        clickOption(dialogCSVOption);
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
            dialogFirstName.click();
        }
        System.out.println(getWebDriver().findElement(By.cssSelector(ADD_EDIT_DIALOG_SLIMSCROLL_BAR)).isDisplayed());

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

    public void clickOnDialogGenerateButton() {
        dialogGenerate.click();
    }

    public void clickOnDialogDeleteButton() {
        dialogDelete.click();
    }

    public void clickOnDialogCancelButton() {
        dialogCancel.click();

        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ACTIONS_BUTTON_ADD_USER)));
    }

    public void clickOnAddUser() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ACTIONS_BUTTON_ADD_USER)));

        waitUntilTableHasLoaded();

        buttonAddUser.click();
        waitUntilDialogIsDisplayed();
    }

    public void clickOnExportUsers() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ACTIONS_BUTTON_EXPORT_USERS)));

        waitUntilTableHasLoaded();

        buttonExportUsers.click();
        waitUntilDialogIsDisplayed();
    }

    public void typeSearchCriteria(String text) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ACTIONS_BUTTON_ADD_USER)));

        waitUntilTableHasLoaded();

        clearAndTypeText(fieldSearchBox, text);

        waitUntilTableHasLoaded();
    }

    public void clickOnEditUser(WebElement userRow) {
        WebElement userEdit = userRow.findElement(By.xpath(TABLE_ROW_BUTTON_EDIT));

        userEdit.click();
        waitUntilDialogIsDisplayed();
    }

    public void clickOnDeleteUser(WebElement userRow) {
        waitUntilDialogIsGone();

        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        WebElement userDelete = wait.until(ExpectedConditions.elementToBeClickable(userRow.findElement(By.xpath(TABLE_ROW_BUTTON_DELETE))));

        userDelete.click();
    }
}
