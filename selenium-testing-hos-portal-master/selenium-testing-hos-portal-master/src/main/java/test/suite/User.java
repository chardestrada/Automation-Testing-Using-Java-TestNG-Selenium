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
import page.module.SignInPage;
import page.module.UserManagementPage;
import test.data.DashboardData;
import test.data.SignInData;
import test.data.UserData;
import util.extentreport.ExtentTestManager;
import util.extentreport.CustomMarkupHelper;

public class User extends AbstractTestSuite implements SignInData, DashboardData, UserData{

    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private UserManagementPage userManagementPage;

    @BeforeMethod
    public void setup(Method method) throws Exception {
        signInPage = new SignInPage(getWebDriver());
    }

    @Test(priority = 12, description = "Verify the existence of the currently logged in user")
    public void User_VerifyExistenceOfCurrentUser() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(HEADER_USERNAME, dashboardPage.getDashboardUsername().toUpperCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userManagementPage = dashboardPage.navigateToUsersTab();
        Assert.assertEquals(USER_HEADER_TEXT, userManagementPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Management Page.", ExtentColor.WHITE));

        WebElement currentUserRow = userManagementPage.getUserRowByName(HEADER_USERNAME);
        Assert.assertNotNull(currentUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified the existence of the currently logged in user.", ExtentColor.WHITE));
    }

    @Test(priority = 13, description = "Verify the functionality of the search box")
    public void User_VerifySearchBox() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(HEADER_USERNAME, dashboardPage.getDashboardUsername().toUpperCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userManagementPage = dashboardPage.navigateToUsersTab();
        Assert.assertEquals(USER_HEADER_TEXT, userManagementPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Management Page.", ExtentColor.WHITE));

        userManagementPage.typeSearchCriteria(USER_FIRSTNAME);

        WebElement noAvailableUserRow = userManagementPage.getUserRowByName(NO_DATA_AVAILABLE);
        Assert.assertNotNull(noAvailableUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that there is no data available.", ExtentColor.WHITE));

        userManagementPage.typeSearchCriteria(VALID_USERNAME);

        WebElement currentUserRow = userManagementPage.getUserRowByName(HEADER_USERNAME);
        Assert.assertNotNull(currentUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified the existence of the currently logged in user.", ExtentColor.WHITE));
    }

    @Test(priority = 14, description = "Verify that the user can create and edit a user with French characters.")
    public void User_CreateAndEditUser() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userManagementPage = dashboardPage.navigateToUsersTab();
        Assert.assertEquals(USER_HEADER_TEXT, userManagementPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Management Page.", ExtentColor.WHITE));

        userManagementPage.clickOnAddUser();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Add User button.", ExtentColor.WHITE));

        userManagementPage.typeFirstName(USER_FIRSTNAME_FRENCH);
        userManagementPage.typeLastName(USER_LASTNAME_FRENCH);
        userManagementPage.typeSuffix(USER_SUFFIX_FRENCH);
        userManagementPage.typeEmail(USER_EMAIL);
        userManagementPage.scrollDialog(true, true, 250);
        userManagementPage.typeConfirmEmail(USER_EMAIL);
        userManagementPage.typePassword(USER_PASSWORD);
        userManagementPage.typeConfirmPassword(USER_PASSWORD);
        userManagementPage.clickUserRoleAccountAdmin();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered user details with French characters.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("A new user has been successfully created", userManagementPage.getTabToastMessage());

        WebElement createdUserRow = userManagementPage.getUserRowByName(USER_FIRSTNAME_FRENCH + " " + USER_LASTNAME_FRENCH + " " + USER_SUFFIX_FRENCH);
        Assert.assertNotNull(createdUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully saved the user. Verified that the French characters were saved.", ExtentColor.WHITE));

        userManagementPage.clickOnEditUser(createdUserRow);

        userManagementPage.typeFirstName(USER_FIRSTNAME);
        userManagementPage.typeLastName(USER_LASTNAME);
        userManagementPage.typeSuffix(USER_SUFFIX);
        userManagementPage.typeEmail(USER_EMAIL_2);
        userManagementPage.scrollDialog(true, true, 250);
        userManagementPage.typeConfirmEmail(USER_EMAIL_2);
        userManagementPage.typePassword(USER_PASSWORD_2);
        userManagementPage.typeConfirmPassword(USER_PASSWORD_2);
        userManagementPage.clickUserRoleFleetManager();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Edited the user and set the fields using regular characters.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("User account has been successfully updated", userManagementPage.getTabToastMessage());

        WebElement updatedUserRow = userManagementPage.getUserRowByName(USER_FIRSTNAME + " " + USER_LASTNAME + " " + USER_SUFFIX);
        Assert.assertNotNull(updatedUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully updated the user. Verified that the regular characters were saved.", ExtentColor.WHITE));

        userManagementPage.clickOnDeleteUser(updatedUserRow);
        userManagementPage.clickOnDialogDeleteButton();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deleted the user.", ExtentColor.WHITE));
    }

    @Test(priority = 15, description = "Verify that the user can create and delete a user with Spanish characters.")
    public void User_CreateAndDeleteUser() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userManagementPage = dashboardPage.navigateToUsersTab();
        Assert.assertEquals(USER_HEADER_TEXT, userManagementPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Management Page.", ExtentColor.WHITE));

        userManagementPage.clickOnAddUser();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Add User button.", ExtentColor.WHITE));

        userManagementPage.typeFirstName(USER_FIRSTNAME_SPANISH);
        userManagementPage.typeLastName(USER_LASTNAME_SPANISH);
        userManagementPage.typeSuffix(USER_SUFFIX_SPANISH);
        userManagementPage.typeEmail(USER_EMAIL);
        userManagementPage.scrollDialog(true, true, 250);
        userManagementPage.typeConfirmEmail(USER_EMAIL);
        userManagementPage.typePassword(USER_PASSWORD);
        userManagementPage.typeConfirmPassword(USER_PASSWORD);
        userManagementPage.clickUserRoleAccountAdmin();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered user details with Spanish characters.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("A new user has been successfully created", userManagementPage.getTabToastMessage());

        WebElement createdUserRow = userManagementPage.getUserRowByName(USER_FIRSTNAME_SPANISH + " " + USER_LASTNAME_SPANISH + " " + USER_SUFFIX_SPANISH);
        Assert.assertNotNull(createdUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully saved the user. Verified that the Spanish characters were saved.", ExtentColor.WHITE));

        userManagementPage.clickOnDeleteUser(createdUserRow);
        userManagementPage.clickOnDialogDeleteButton();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deleted the user.", ExtentColor.WHITE));
    }

    @Test(priority = 16, description = "Verify that the user can create, deactivate and activate a user.")
    public void User_CreateDeactivateAndActivateUser() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userManagementPage = dashboardPage.navigateToUsersTab();
        Assert.assertEquals(USER_HEADER_TEXT, userManagementPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Management Page.", ExtentColor.WHITE));

        userManagementPage.clickOnAddUser();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Add User button.", ExtentColor.WHITE));

        userManagementPage.typeFirstName(USER_FIRSTNAME);
        userManagementPage.typeLastName(USER_LASTNAME);
        userManagementPage.typeSuffix(USER_SUFFIX);
        userManagementPage.typeEmail(USER_EMAIL);
        userManagementPage.clickAuthorizedMainOffice();
        userManagementPage.scrollDialog(true, true, 250);
        userManagementPage.typeConfirmEmail(USER_EMAIL);
        userManagementPage.typePassword(USER_PASSWORD);
        userManagementPage.typeConfirmPassword(USER_PASSWORD);
        userManagementPage.clickUserRoleAccountAdmin();
        userManagementPage.clickUserRoleFleetManager();
        userManagementPage.clickUserRoleUserAdmin();
        userManagementPage.clickUserRoleAssetAdmin();
        userManagementPage.clickUserRoleWorkOrderAssignee();
        userManagementPage.clickUserRoleUserViewOnly();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered user details.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("A new user has been successfully created", userManagementPage.getTabToastMessage());

        WebElement createdUserRow = userManagementPage.getUserRowByName(USER_FIRSTNAME + " " + USER_LASTNAME + " " + USER_SUFFIX);
        Assert.assertNotNull(createdUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully saved the user.", ExtentColor.WHITE));

        userManagementPage.clickOnEditUser(createdUserRow);

        userManagementPage.clickStatus();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Deactivated the user.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("User account has been successfully updated", userManagementPage.getTabToastMessage());

        WebElement deactivatedUserRow = userManagementPage.getUserRowByName(USER_FIRSTNAME + " " + USER_LASTNAME + " " + USER_SUFFIX);
        Assert.assertNotNull(deactivatedUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deactivated the user.", ExtentColor.WHITE));

        userManagementPage.clickOnEditUser(deactivatedUserRow);

        userManagementPage.clickStatus();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Activated the user.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("User account has been successfully updated", userManagementPage.getTabToastMessage());

        WebElement activatedUserRow = userManagementPage.getUserRowByName(USER_FIRSTNAME + " " + USER_LASTNAME + " " + USER_SUFFIX);
        Assert.assertNotNull(activatedUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully activated the user.", ExtentColor.WHITE));

        userManagementPage.clickOnDeleteUser(activatedUserRow);
        userManagementPage.clickOnDialogDeleteButton();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deleted the user.", ExtentColor.WHITE));
    }

    @Test(priority = 17, description = "Verify that the user can create, login and export users.")
    public void User_CreateLoginAndExportUsers() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userManagementPage = dashboardPage.navigateToUsersTab();
        Assert.assertEquals(USER_HEADER_TEXT, userManagementPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Management Page.", ExtentColor.WHITE));

        userManagementPage.clickOnAddUser();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Add User button.", ExtentColor.WHITE));

        userManagementPage.typeFirstName(USER_FIRSTNAME);
        userManagementPage.typeLastName(USER_LASTNAME);
        userManagementPage.typeSuffix(USER_SUFFIX);
        userManagementPage.typeEmail(USER_EMAIL_3);
        userManagementPage.scrollDialog(true, true, 250);
        userManagementPage.typeConfirmEmail(USER_EMAIL_3);
        userManagementPage.typePassword(USER_PASSWORD);
        userManagementPage.typeConfirmPassword(USER_PASSWORD);
        userManagementPage.clickUserRoleAccountAdmin();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered user details with regular characters.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("A new user has been successfully created", userManagementPage.getTabToastMessage());

        WebElement createdUserRow = userManagementPage.getUserRowByName(USER_FIRSTNAME + " " + USER_LASTNAME + " " + USER_SUFFIX);
        Assert.assertNotNull(createdUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully saved the user. Verified that the regular characters were saved.", ExtentColor.WHITE));

        signInPage = userManagementPage.clickSignOut();
        Assert.assertEquals(HEADER_TEXT, signInPage.getLoginTitle());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed out from the portal", ExtentColor.WHITE));


        dashboardPage = signInPage.enterCredentialsAndSignIn(USER_EMAIL_3, USER_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal using the new user.", ExtentColor.WHITE));

        userManagementPage = dashboardPage.navigateToUsersTab();
        Assert.assertEquals(USER_HEADER_TEXT, userManagementPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Management Page.", ExtentColor.WHITE));

        userManagementPage.clickOnAddUser();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Add User button.", ExtentColor.WHITE));

        userManagementPage.typeFirstName(USER_FIRSTNAME_2);
        userManagementPage.typeLastName(USER_LASTNAME_2);
        userManagementPage.typeSuffix(USER_SUFFIX_2);
        userManagementPage.typeEmail(USER_EMAIL_2);
        userManagementPage.scrollDialog(true, true, 250);
        userManagementPage.typeConfirmEmail(USER_EMAIL_2);
        userManagementPage.typePassword(USER_PASSWORD_2);
        userManagementPage.typeConfirmPassword(USER_PASSWORD_2);
        userManagementPage.clickUserRoleAccountAdmin();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered user details with regular characters.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("A new user has been successfully created", userManagementPage.getTabToastMessage());

        WebElement createdUserRow2 = userManagementPage.getUserRowByName(USER_FIRSTNAME + " " + USER_LASTNAME + " " + USER_SUFFIX);
        Assert.assertNotNull(createdUserRow2);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully saved another user.", ExtentColor.WHITE));

        signInPage = userManagementPage.clickSignOut();
        Assert.assertEquals(HEADER_TEXT, signInPage.getLoginTitle());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed out from the portal", ExtentColor.WHITE));


        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userManagementPage = dashboardPage.navigateToUsersTab();
        Assert.assertEquals(USER_HEADER_TEXT, userManagementPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Management Page.", ExtentColor.WHITE));

        userManagementPage = dashboardPage.navigateToUsersTab();
        Assert.assertEquals(USER_HEADER_TEXT, userManagementPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Management Page.", ExtentColor.WHITE));


        userManagementPage.clickOnExportUsers();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Export Users button.", ExtentColor.WHITE));
        userManagementPage.clickOnDialogGenerateButton();
        threeSecondSleep();
        userManagementPage.closeExportTab();
        threeSecondSleep();
        userManagementPage.clickCSVOption();
        userManagementPage.clickOnDialogGenerateButton();
        threeSecondSleep();
        userManagementPage.clickOnDialogCancelButton();


        createdUserRow = userManagementPage.getUserRowByName(USER_FIRSTNAME + " " + USER_LASTNAME + " " + USER_SUFFIX);
        userManagementPage.clickOnDeleteUser(createdUserRow);
        userManagementPage.clickOnDialogDeleteButton();

        createdUserRow2 = userManagementPage.getUserRowByName(USER_FIRSTNAME_2 + " " + USER_LASTNAME_2 + " " + USER_SUFFIX_2);
        userManagementPage.clickOnDeleteUser(createdUserRow2);
        userManagementPage.clickOnDialogDeleteButton();

        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deleted the users.", ExtentColor.WHITE));
    }

    @Test(priority = 18, description = "Verify that the user cannot create/edit users with incorrect values.")
    public void User_NegativeTests() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userManagementPage = dashboardPage.navigateToUsersTab();
        Assert.assertEquals(USER_HEADER_TEXT, userManagementPage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Management Page.", ExtentColor.WHITE));

        userManagementPage.clickOnAddUser();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Add User button.", ExtentColor.WHITE));

        userManagementPage.typeFirstName(USER_FIRSTNAME);
        userManagementPage.typeLastName(USER_LASTNAME);
        userManagementPage.typeSuffix(USER_SUFFIX);
        userManagementPage.typeEmail(USER_EMAIL);
        userManagementPage.clickVisibilitySet1();
        userManagementPage.clickAuthorizedMainOffice();
        userManagementPage.scrollDialog(true, true, 250);
        userManagementPage.typeConfirmEmail(USER_EMAIL);
        userManagementPage.typePassword(USER_PASSWORD);
        userManagementPage.typeConfirmPassword(USER_PASSWORD);
        userManagementPage.clickUserRoleAccountAdmin();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered user details, did not select any visibility set.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("A user must have at least one visibility set.", userManagementPage.getTabToastMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that cannot create a user without any visibility set.", ExtentColor.WHITE));
        userManagementPage.clickTabToastMessage();
        userManagementPage.clickUserRoleAccountAdmin();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Did not select any user role.", ExtentColor.WHITE));

        userManagementPage.scrollDialog(false, false, 250);
        userManagementPage.clickVisibilitySet1();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Selected one visibility set.", ExtentColor.WHITE));
        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("Select at least 1 available user roles", userManagementPage.getTabToastMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that cannot create a user without any user role.", ExtentColor.WHITE));
        userManagementPage.clickTabToastMessage();

        userManagementPage.scrollDialog(false, true, 250);
        userManagementPage.clickUserRoleAccountAdmin();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Selected one user role", ExtentColor.WHITE));
        userManagementPage.typeConfirmEmail(USER_EMAIL_2);
        userManagementPage.typeConfirmPassword(USER_PASSWORD_2);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered mismatched email address and password.", ExtentColor.WHITE));
        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("Email and Confirm Email should match", userManagementPage.getTabToastMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that cannot create a user with a mismatched email address.", ExtentColor.WHITE));
        userManagementPage.clickTabToastMessage();
        userManagementPage.typeConfirmEmail(USER_EMAIL);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered matching email address.", ExtentColor.WHITE));
        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("Password and Confirm Password should match", userManagementPage.getTabToastMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that cannot create a user with a mismatched password.", ExtentColor.WHITE));
        userManagementPage.clickTabToastMessage();
        userManagementPage.typeConfirmPassword(USER_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered matching password.", ExtentColor.WHITE));
        userManagementPage.typeEmail(VALID_USERNAME);
        userManagementPage.typeConfirmEmail(VALID_USERNAME);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered existing email address.", ExtentColor.WHITE));
        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("A user with the given email address already exists.", userManagementPage.getTabToastMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that cannot create a user with existing email address.", ExtentColor.WHITE));
        userManagementPage.clickTabToastMessage();
        userManagementPage.typeEmail(USER_EMAIL);
        userManagementPage.typeConfirmEmail(USER_EMAIL);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered a new email address.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("A new user has been successfully created", userManagementPage.getTabToastMessage());

        WebElement createdUserRow = userManagementPage.getUserRowByName(USER_FIRSTNAME + " " + USER_LASTNAME + " " + USER_SUFFIX);
        Assert.assertNotNull(createdUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully saved the user.", ExtentColor.WHITE));




        userManagementPage.clickOnEditUser(createdUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the edit user button.", ExtentColor.WHITE));
        userManagementPage.scrollDialog(false, true, 250);
        userManagementPage.clickUserRoleAccountAdmin();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Removed all user roles.", ExtentColor.WHITE));

        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("Select at least 1 available user roles", userManagementPage.getTabToastMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that cannot edit a user without any user role.", ExtentColor.WHITE));
        userManagementPage.clickTabToastMessage();
        userManagementPage.clickUserRoleAccountAdmin();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Selected one user role.", ExtentColor.WHITE));

        userManagementPage.typeConfirmEmail(USER_EMAIL_2);
        userManagementPage.typeConfirmPassword(USER_PASSWORD_2);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered mismatched email address and password.", ExtentColor.WHITE));
        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("Email and Confirm Email should match", userManagementPage.getTabToastMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that cannot edit a user with a mismatched email address.", ExtentColor.WHITE));
        userManagementPage.clickTabToastMessage();
        userManagementPage.typeConfirmEmail(USER_EMAIL);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered matching email address.", ExtentColor.WHITE));
        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("Password and Confirm Password should match", userManagementPage.getTabToastMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that cannot edit a user with a mismatched password.", ExtentColor.WHITE));
        userManagementPage.clickTabToastMessage();
        userManagementPage.typePassword(USER_PASSWORD);
        userManagementPage.typeConfirmPassword(USER_PASSWORD);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered matching password.", ExtentColor.WHITE));
        userManagementPage.typeEmail(VALID_USERNAME);
        userManagementPage.typeConfirmEmail(VALID_USERNAME);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered existing email address.", ExtentColor.WHITE));
        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("A user with the given email address already exists.", userManagementPage.getTabToastMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that cannot edit a user with existing email address.", ExtentColor.WHITE));
        userManagementPage.clickTabToastMessage();
        userManagementPage.typeEmail(USER_EMAIL);
        userManagementPage.typeConfirmEmail(USER_EMAIL);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered the original email address.", ExtentColor.WHITE));


        userManagementPage.clickOnDialogSaveButton();
        Assert.assertEquals("User account has been successfully updated", userManagementPage.getTabToastMessage());

        WebElement updatedUserRow = userManagementPage.getUserRowByName(USER_FIRSTNAME + " " + USER_LASTNAME + " " + USER_SUFFIX);
        Assert.assertNotNull(updatedUserRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully edited the user.", ExtentColor.WHITE));

        userManagementPage.clickOnDeleteUser(updatedUserRow);
        userManagementPage.clickOnDialogDeleteButton();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deleted the user.", ExtentColor.WHITE));
    }

    @AfterMethod
    public void signOut(ITestResult result) throws Exception {
        signInPage = userManagementPage.clickSignOut();
        Assert.assertEquals(HEADER_TEXT, signInPage.getLoginTitle());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed out from the portal", ExtentColor.WHITE));
    }

}
