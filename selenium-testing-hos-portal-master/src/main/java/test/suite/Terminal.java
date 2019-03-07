package test.suite;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

import page.module.DashboardPage;
import page.module.TerminalPage;
import page.module.SignInPage;
import test.data.DashboardData;
import test.data.SignInData;
import test.data.TerminalData;
import util.extentreport.ExtentTestManager;
import util.extentreport.CustomMarkupHelper;

public class Terminal extends AbstractTestSuite implements SignInData, DashboardData, TerminalData{

    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private TerminalPage terminalPage;

    @BeforeMethod
    public void setup(Method method) throws Exception {
        signInPage = new SignInPage(getWebDriver());
    }

    @Test(priority = 3, description = "Verify the existence of main office terminal")
    public void Terminal_VerifyMainOfficeTerminal() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(HEADER_USERNAME, dashboardPage.getDashboardUsername().toUpperCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        terminalPage = dashboardPage.navigateToTerminalsTab();
        Assert.assertEquals(TERMINAL_HEADER_TEXT, terminalPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the Terminal Page.", ExtentColor.WHITE));

        WebElement mainOfficeTerminalRow = terminalPage.getTerminalRowByMainOffice();
        Assert.assertNotNull(mainOfficeTerminalRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified the existence of main office terminal.", ExtentColor.WHITE));
    }

    @Test(priority = 4, description = "Verify that user can create and edit a terminal to make it the main office terminal")
    public void Terminal_CreateMainOfficeTerminal() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        terminalPage = dashboardPage.navigateToTerminalsTab();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the Terminal Page.", ExtentColor.WHITE));

        terminalPage.clickOnAddTerminal();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Add Terminal button.", ExtentColor.WHITE));

        terminalPage.typeName(TERMINAL_NAME);
        terminalPage.typeCode(TERMINAL_CODE);
        terminalPage.typeStreet(TERMINAL_STREET);
        terminalPage.typeCity(TERMINAL_CITY);
        terminalPage.typeCountry(TERMINAL_COUNTRY);
        terminalPage.typeStateOrProvince(TERMINAL_STATE);
        terminalPage.typePostalCode(TERMINAL_POSTAL_CODE);
        terminalPage.typePhoneNumber(TERMINAL_PHONE_NUMBER);
        terminalPage.selectTimeZone(TERMINAL_TIME_ZONE);
        terminalPage.selectStartTimeOfDay(TERMINAL_START_TIME_OF_DAY);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered terminal details.", ExtentColor.WHITE));

        terminalPage.scrollDialog(true, 100);
        terminalPage.clickMainOffice();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Enabled Main Office checkbox.", ExtentColor.WHITE));

        terminalPage.clickOnDialogSaveButton();
        Assert.assertEquals(TERMINAL_NAME + " terminal has been successfully added.", terminalPage.getTabToastMessage());

        WebElement createdTerminalRow = terminalPage.getTerminalRowByName(TERMINAL_NAME);
        WebElement mainOfficeTerminalRow = terminalPage.getTerminalRowByMainOffice();
        Assert.assertEquals(createdTerminalRow, mainOfficeTerminalRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully saved the terminal. Verified that the terminal created was set as the main office.", ExtentColor.WHITE));

        WebElement defaultMainOfficeTerminalRow = terminalPage.getTerminalRowByName(TERMINAL_MAIN_OFFICE_DEFAULT);
        terminalPage.clickOnEditTerminal(defaultMainOfficeTerminalRow);
        terminalPage.scrollDialog(true, 100);
        terminalPage.clickMainOffice();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Edited the default main office terminal and set it back as the main office.", ExtentColor.WHITE));

        terminalPage.clickOnDialogSaveButton();
        Assert.assertEquals(TERMINAL_MAIN_OFFICE_DEFAULT + " terminal has been successfully updated.", terminalPage.getTabToastMessage());

        defaultMainOfficeTerminalRow = terminalPage.getTerminalRowByName(TERMINAL_MAIN_OFFICE_DEFAULT);
        mainOfficeTerminalRow = terminalPage.getTerminalRowByMainOffice();
        Assert.assertEquals(defaultMainOfficeTerminalRow, mainOfficeTerminalRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully updated the terminal. Verified that the default main office terminal was set back as the main office.", ExtentColor.WHITE));

        createdTerminalRow = terminalPage.getTerminalRowByName(TERMINAL_NAME);

        terminalPage.clickOnDeleteTerminal(createdTerminalRow);
        terminalPage.clickOnDialogDeleteButton();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deleted the terminal.", ExtentColor.WHITE));
    }

    @Test(priority = 5, description = "Verify that the user can create and edit a terminal with French characters.")
    public void Terminal_CreateAndEditTerminal() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        terminalPage = dashboardPage.navigateToTerminalsTab();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the Terminal Page.", ExtentColor.WHITE));

        terminalPage.clickOnAddTerminal();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Add Terminal button.", ExtentColor.WHITE));

        terminalPage.typeName(TERMINAL_NAME_FRENCH);
        terminalPage.typeCode(TERMINAL_CODE_FRENCH);
        terminalPage.typeStreet(TERMINAL_STREET_FRENCH);
        terminalPage.typeCity(TERMINAL_CITY_FRENCH);
        terminalPage.typeStateOrProvince(TERMINAL_STATE_FRENCH);
        terminalPage.typePostalCode(TERMINAL_POSTAL_CODE_FRENCH);
        terminalPage.typeCountry(TERMINAL_COUNTRY_FRENCH);
        terminalPage.typePhoneNumber(TERMINAL_PHONE_NUMBER);
        terminalPage.selectTimeZone(TERMINAL_TIME_ZONE_FRENCH);
        terminalPage.selectStartTimeOfDay(TERMINAL_START_TIME_OF_DAY);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered terminal details with French characters.", ExtentColor.WHITE));

        terminalPage.clickOnDialogSaveButton();
        Assert.assertEquals(TERMINAL_NAME_FRENCH + " terminal has been successfully added.", terminalPage.getTabToastMessage());

        WebElement createdTerminalRow = terminalPage.getTerminalRowByName(TERMINAL_NAME_FRENCH);
        Assert.assertNotNull(createdTerminalRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully saved the terminal. Verified that the French characters were saved.", ExtentColor.WHITE));

        terminalPage.clickOnEditTerminal(createdTerminalRow);

        terminalPage.typeName(TERMINAL_EDIT_NAME);
        terminalPage.typeCode(TERMINAL_EDIT_CODE);
        terminalPage.typeStreet(TERMINAL_EDIT_STREET);
        terminalPage.typeCity(TERMINAL_EDIT_CITY);
        terminalPage.typeStateOrProvince(TERMINAL_EDIT_STATE);
        terminalPage.typePostalCode(TERMINAL_EDIT_POSTAL_CODE);
        terminalPage.typeCountry(TERMINAL_COUNTRY);
        terminalPage.selectTimeZone(TERMINAL_TIME_ZONE);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Edited the terminal and set the fields using regular characters.", ExtentColor.WHITE));

        terminalPage.clickOnDialogSaveButton();
        Assert.assertEquals(TERMINAL_EDIT_NAME + " terminal has been successfully updated.", terminalPage.getTabToastMessage());

        WebElement updatedTerminalRow = terminalPage.getTerminalRowByName(TERMINAL_EDIT_NAME);
        Assert.assertNotNull(updatedTerminalRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully updated the terminal. Verified that the regular characters were saved.", ExtentColor.WHITE));

        terminalPage.clickOnDeleteTerminal(updatedTerminalRow);
        terminalPage.clickOnDialogDeleteButton();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deleted the terminal.", ExtentColor.WHITE));
    }

    @Test(priority = 6, description = "Verify that the user can create and delete a terminal with Spanish characters")
    public void Terminal_CreateAndDeleteTerminal() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        terminalPage = dashboardPage.navigateToTerminalsTab();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the Terminal Page.", ExtentColor.WHITE));

        terminalPage.clickOnAddTerminal();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Add Terminal button.", ExtentColor.WHITE));

        terminalPage.typeName(TERMINAL_NAME_SPANISH);
        terminalPage.typeCode(TERMINAL_CODE_SPANISH);
        terminalPage.typeStreet(TERMINAL_STREET_SPANISH);
        terminalPage.typeCity(TERMINAL_CITY_SPANISH);
        terminalPage.typeStateOrProvince(TERMINAL_STATE_SPANISH);
        terminalPage.typePostalCode(TERMINAL_POSTAL_CODE_SPANISH);
        terminalPage.typeCountry(TERMINAL_COUNTRY_SPANISH);
        terminalPage.typePhoneNumber(TERMINAL_PHONE_NUMBER);
        terminalPage.selectTimeZone(TERMINAL_TIME_ZONE_SPANISH);
        terminalPage.selectStartTimeOfDay(TERMINAL_START_TIME_OF_DAY);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered terminal details with Spanish characters.", ExtentColor.WHITE));

        terminalPage.clickOnDialogSaveButton();
        Assert.assertEquals(TERMINAL_NAME_SPANISH + " terminal has been successfully added.", terminalPage.getTabToastMessage());

        WebElement createdTerminalRow = terminalPage.getTerminalRowByName(TERMINAL_NAME_SPANISH);
        Assert.assertNotNull(createdTerminalRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully saved the terminal. Verified that the Spanish characters were saved.", ExtentColor.WHITE));

        terminalPage.clickOnDeleteTerminal(createdTerminalRow);
        terminalPage.clickOnDialogDeleteButton();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deleted the terminal.", ExtentColor.WHITE));
    }

    @Test(priority = 7, description = "Verify that the user can upload a CSV to create and update the terminals in the account")
    public void Terminal_UploadTerminalsCSV() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        terminalPage = dashboardPage.navigateToTerminalsTab();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the Terminal Page.", ExtentColor.WHITE));

        terminalPage.clickOnUploadTerminalCSV();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Upload CSV button.", ExtentColor.WHITE));

        terminalPage.clickOnUploadTerminalCSVLink();
        terminalPage.clickTabToastMessage();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully viewed the instructions and the example image.", ExtentColor.WHITE));

        terminalPage.selectAndUploadTerminalsCSVFile(TERMINAL_CSV_CREATE_PATH, false);
        Assert.assertEquals("Upload Success!", terminalPage.getUploadAlertMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Selected a CSV file. Checked the 'Create' option and clicked the Upload button.", ExtentColor.WHITE));

        terminalPage.clickOnUploadTerminalCSVCloseButton();
        terminalPage.clickRefresh();

        WebElement terminalRow1 = terminalPage.getTerminalRowByName(TERMINAL_NAME);
        Assert.assertNotNull(terminalRow1);

        WebElement terminalRow2 = terminalPage.getTerminalRowByName(TERMINAL_EDIT_NAME);
        Assert.assertNotNull(terminalRow2);

        WebElement terminalRow3 = terminalPage.getTerminalRowByName(TERMINAL_CSV_NAME);
        Assert.assertNotNull(terminalRow3);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully uploaded a CSV using the 'Create' option. Verified that there were three terminals created.", ExtentColor.WHITE));

        terminalPage.clickOnUploadTerminalCSV();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Upload CSV button.", ExtentColor.WHITE));

        terminalPage.selectAndUploadTerminalsCSVFile(TERMINAL_CSV_UPDATE_PATH, true);
        Assert.assertEquals("Upload Success!", terminalPage.getUploadAlertMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Selected a CSV file. Checked the 'Update' option and clicked the Upload button.", ExtentColor.WHITE));

        terminalPage.clickOnUploadTerminalCSVCloseButton();
        terminalPage.clickRefresh();

        terminalRow1 = terminalPage.getTerminalRowByName(TERMINAL_NAME);
        Assert.assertNotNull(terminalRow1);
        terminalPage.clickOnDeleteTerminal(terminalRow1);
        terminalPage.clickOnDialogDeleteButton();

        terminalRow2 = terminalPage.getTerminalRowByName(TERMINAL_EDIT_NAME);
        Assert.assertNotNull(terminalRow2);
        terminalPage.clickOnDeleteTerminal(terminalRow2);
        terminalPage.clickOnDialogDeleteButton();

        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully uploaded a CSV using the 'Update' option. Verified that there were two terminals updated and one terminal deleted.", ExtentColor.WHITE));

        terminalRow3 = terminalPage.getTerminalRowByName(TERMINAL_CSV_NAME);
        Assert.assertNull(terminalRow3);

        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deleted the terminals.", ExtentColor.WHITE));
    }

    @AfterMethod
    public void signOut(ITestResult result) throws Exception {
        signInPage = terminalPage.clickSignOut();
        Assert.assertEquals(HEADER_TEXT, signInPage.getLoginTitle());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed out from the portal", ExtentColor.WHITE));
    }

}
