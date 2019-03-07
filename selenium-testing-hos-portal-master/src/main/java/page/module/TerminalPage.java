package page.module;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.locator.TerminalLocators;

public class TerminalPage extends DashboardPage implements TerminalLocators {

    @FindBy(xpath = TITLE)
    private WebElement tabTitle;

    @FindBy(xpath = TOAST_MESSAGE)
    private WebElement toastMessage;

    @FindBy(xpath = ACTIONS_BUTTON_ADD_TERMINAL)
    private WebElement buttonAddTerminal;

    @FindBy(xpath = ACTIONS_BUTTON_UPLOAD_TERMINAL)
    private WebElement buttonUploadTerminal;

    @FindBy(css = DIALOG_CONTAINER)
    private WebElement dialog;

    @FindBy(xpath = ADD_EDIT_DIALOG_FIELD_NAME)
    private WebElement dialogName;

    @FindBy(xpath = ADD_EDIT_DIALOG_FIELD_CODE)
    private WebElement dialogCode;

    @FindBy(xpath = ADD_EDIT_DIALOG_FIELD_STREET)
    private WebElement dialogStreet;

    @FindBy(xpath = ADD_EDIT_DIALOG_FIELD_CITY)
    private WebElement dialogCity;

    @FindBy(xpath = ADD_EDIT_DIALOG_FIELD_STATE_OR_PROVINCE)
    private WebElement dialogStreetOrProvince;

    @FindBy(xpath = ADD_EDIT_DIALOG_FIELD_POSTAL_CODE)
    private WebElement dialogPostalCode;

    @FindBy(xpath = ADD_EDIT_DIALOG_FIELD_COUNTRY)
    private WebElement dialogCountry;

    @FindBy(xpath = ADD_EDIT_DIALOG_FIELD_PHONE_NUMBER)
    private WebElement dialogPhoneNumber;

    @FindBy(xpath = ADD_EDIT_DIALOG_SELECT_SEARCH_FIELD_TIMEZONE)
    private WebElement dialogTimezoneSearch;

    @FindBy(xpath = ADD_EDIT_DIALOG_SELECT_SEARCH_FIELD_START_TIME_OF_DAY)
    private WebElement dialogStartTimeOfDaySearch;

    @FindBy(xpath = ADD_EDIT_DIALOG_CHECKBOX_MAIN_OFFICE)
    private WebElement dialogMainOffice;

    @FindBy(css = ADD_EDIT_DIALOG_SLIMSCROLL_AREA)
    private WebElement scrollArea;

    @FindBy(css = ADD_EDIT_DIALOG_SLIMSCROLL_BAR)
    private WebElement scrollBar;

    @FindBy(xpath = ADD_EDIT_DIALOG_BUTTON_SAVE)
    private WebElement dialogSave;

    @FindBy(xpath = DELETE_DIALOG_BUTTON_DELETE)
    private WebElement dialogDelete;

    @FindBy(css = UPLOAD_CSV_DIALOG_BUTTON_CLOSE)
    private WebElement dialogClose;

    @FindBy(id = UPLOAD_CSV_DIALOG_LINK)
    private WebElement dialogLink;

    @FindBy(id = UPLOAD_CSV_DIALOG_IFRAME_BUTTON_FILE)
    private WebElement dialogFile;

    @FindBy(name = UPLOAD_CSV_DIALOG_IFRAME_RADIO_BUTTONS)
    private List<WebElement> dialogRadios;

    @FindBy(css = UPLOAD_CSV_DIALOG_IFRAME_POPUP_YES)
    private WebElement dialogPopupYes;

    @FindBy(css = UPLOAD_CSV_DIALOG_IFRAME_ALERT_MESSAGE)
    private WebElement dialogMessage;

    @FindBy(id = UPLOAD_CSV_DIALOG_IFRAME_BUTTON_UPLOAD)
    private WebElement dialogUpload;

    public TerminalPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getCurrentTab() {
        return TITLE;
    }

    public String getTabTitle(){
        return tabTitle.getText();
    }


    private List<WebElement> getAllTerminalRowsFromTable(){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(TOAST_MESSAGE)));

        return getWebDriver().findElements(By.xpath(TABLE_ROWS));
    }

    public WebElement getTerminalRowByName(String terminalName){
        List<WebElement> terminalRows = getAllTerminalRowsFromTable();

        for (WebElement terminalRow : terminalRows) {
            WebElement terminalRowName = terminalRow.findElement(By.xpath("td[1]"));
            if (terminalRowName.getText().equalsIgnoreCase(terminalName)) {
                return terminalRow;
            }
        }
        return null;
    }

    public String getUploadAlertMessage(){
        switchToFrameNameOrId(UPLOAD_CSV_DIALOG_IFRAME);

        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(UPLOAD_CSV_DIALOG_IFRAME_ALERT_MESSAGE)));

        String message = dialogMessage.getText();

        switchToMainWindow();

        return message;
    }

    public WebElement getTerminalRowByMainOffice(){
        List<WebElement> terminalRows = getAllTerminalRowsFromTable();

        for (WebElement terminalRow : terminalRows) {
            List<WebElement> terminalRowMainOffices = terminalRow.findElements(By.xpath("td[2]/i"));
            if (!terminalRowMainOffices.isEmpty()) {
                if (terminalRowMainOffices.get(0).getText().equalsIgnoreCase("done")) {
                    return terminalRow;
                }
            }
        }
        return null;
    }

    public void typeName(String text) {
        clearAndTypeText(dialogName, text);
    }

    public void typeCode(String text) {
        clearAndTypeText(dialogCode, text);
    }

    public void typeStreet(String text) {
        clearAndTypeText(dialogStreet, text);
    }

    public void typeCity(String text) {
        clearAndTypeText(dialogCity, text);
    }

    public void typeStateOrProvince(String text) {
        clearAndTypeText(dialogStreetOrProvince, text);
    }

    public void typePostalCode(String text) {
        clearAndTypeText(dialogPostalCode, text);
    }

    public void typeCountry(String text) {
        clearAndTypeText(dialogCountry, text);
    }

    public void typePhoneNumber(String text) {
        clearAndTypeText(dialogPhoneNumber, text);
    }

    public void selectTimeZone(String text) {
        selectFromSelect2(dialogTimezoneSearch, text);
    }

    public void selectStartTimeOfDay(String text) {
        selectFromSelect2(dialogStartTimeOfDaySearch, text);
    }

    public void clickMainOffice() {
        clickCheckBox(dialogMainOffice);
    }

    public void selectAndUploadTerminalsCSVFile(String text, boolean isUpdate) {
        switchToFrameNameOrId(UPLOAD_CSV_DIALOG_IFRAME);

        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(UPLOAD_CSV_DIALOG_IFRAME_BUTTON_FILE)));

        selectFile(dialogFile, text);

        if (isUpdate) {
            for (WebElement radio : dialogRadios) {
                if (radio.getAttribute("value").equalsIgnoreCase("update")) {
                    radio.click();
                }
            }

        }

        wait.until(ExpectedConditions.elementToBeClickable(By.id(UPLOAD_CSV_DIALOG_IFRAME_BUTTON_UPLOAD)));
        dialogUpload.click();

        switchToMainWindow();

        if (isUpdate) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(UPLOAD_CSV_DIALOG_IFRAME_POPUP_YES)));
            dialogPopupYes.click();
        }
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

    public void scrollDialog(boolean isDownward, int scrollPoints) {
        dialogName.click();

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

    public void clickOnDialogSaveButton() {
        dialogSave.click();
    }

    public void clickOnDialogDeleteButton() {
        dialogDelete.click();
    }

    public void clickOnAddTerminal() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACTIONS_BUTTON_ADD_TERMINAL)));

        buttonAddTerminal.click();
        waitUntilDialogIsDisplayed();
    }

    public void clickOnUploadTerminalCSV() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACTIONS_BUTTON_UPLOAD_TERMINAL)));

        buttonUploadTerminal.click();
        waitUntilDialogIsDisplayed();
    }

    public void clickOnUploadTerminalCSVLink() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(UPLOAD_CSV_DIALOG_LINK)));

        dialogLink.click();
    }

    public void clickOnUploadTerminalCSVCloseButton() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(UPLOAD_CSV_DIALOG_BUTTON_CLOSE)));

        dialogClose.click();
    }

    public void clickOnEditTerminal(WebElement terminalRow) {
        WebElement terminalEdit = terminalRow.findElement(By.xpath(TABLE_ROW_BUTTON_EDIT));

        terminalEdit.click();
        waitUntilDialogIsDisplayed();
    }

    public void clickOnDeleteTerminal(WebElement terminalRow) {
        WebElement terminalDelete = terminalRow.findElement(By.xpath(TABLE_ROW_BUTTON_DELETE));

        terminalDelete.click();
    }
}
