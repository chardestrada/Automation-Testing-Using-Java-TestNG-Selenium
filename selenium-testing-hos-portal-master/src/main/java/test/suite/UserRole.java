package test.suite;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

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
import page.module.UserRolePage;
import test.data.DashboardData;
import test.data.SignInData;
import test.data.UserRoleData;
import util.extentreport.ExtentTestManager;
import util.extentreport.CustomMarkupHelper;

public class UserRole extends AbstractTestSuite implements SignInData, DashboardData, UserRoleData{

    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private UserRolePage userRolePage;

    @BeforeMethod
    public void setup(Method method) throws Exception {
        signInPage = new SignInPage(getWebDriver());
    }

    @Test(priority = 8, description = "Verify the existence of all default user roles")
    public void UserRole_VerifyExistenceOfDefaultUserRoles() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(HEADER_USERNAME, dashboardPage.getDashboardUsername());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userRolePage = dashboardPage.navigateToUserRolesTab();
        Assert.assertEquals(USER_ROLE_HEADER_TEXT, userRolePage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Role Management Page.", ExtentColor.WHITE));

        ArrayList<String> defaultUserRoleNames = userRolePage.getAllUserRoleNames();
        Assert.assertTrue(defaultUserRoleNames.contains(USER_ROLE_DEFAULT_ACCOUNTADMIN));
        Assert.assertTrue(defaultUserRoleNames.contains(USER_ROLE_DEFAULT_FLEETMANAGER));
        Assert.assertTrue(defaultUserRoleNames.contains(USER_ROLE_DEFAULT_USERADMIN));
        Assert.assertTrue(defaultUserRoleNames.contains(USER_ROLE_DEFAULT_ASSETADMIN));
        Assert.assertTrue(defaultUserRoleNames.contains(USER_ROLE_DEFAULT_WORKORDERASSIGNEE));
        Assert.assertTrue(defaultUserRoleNames.contains(USER_ROLE_DEFAULT_VIEW_ONLY));
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified the existence of all default user roles.", ExtentColor.WHITE));
    }

    @Test(priority = 9, description = "Verify that all default user roles have correct permissions")
    public void UserRole_VerifyPermissionsOfDefaultUserRoles() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(HEADER_USERNAME, dashboardPage.getDashboardUsername());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userRolePage = dashboardPage.navigateToUserRolesTab();
        Assert.assertEquals(USER_ROLE_HEADER_TEXT, userRolePage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Role Management Page.", ExtentColor.WHITE));

        WebElement userRoleAccountAdminRow = userRolePage.getUserRoleRowByName(USER_ROLE_DEFAULT_ACCOUNTADMIN);
        userRolePage.clickOnEditUserRole(userRoleAccountAdminRow);
        HashMap<String, Boolean> accountAdminPermissionsMap = userRolePage.getAllPermissionNamesAndValues();
        Assert.assertFalse(accountAdminPermissionsMap.get(PERM_USERS_WORK_ORDER_ASSIGNEE));
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that " + USER_ROLE_DEFAULT_ACCOUNTADMIN + " has the correct permissions.", ExtentColor.WHITE));

        userRolePage.clickOnDialogCancelButton();

        WebElement userRoleFleetManagerRow = userRolePage.getUserRoleRowByName(USER_ROLE_DEFAULT_FLEETMANAGER);
        userRolePage.clickOnEditUserRole(userRoleFleetManagerRow);
        HashMap<String, Boolean> fleetManagerPermissionsMap = userRolePage.getAllPermissionNamesAndValues();
        Assert.assertFalse(fleetManagerPermissionsMap.get(PERM_USERS_ACCOUNT_ADMIN));
        Assert.assertFalse(fleetManagerPermissionsMap.get(PERM_USERS_WORK_ORDER_ASSIGNEE));
        Assert.assertFalse(fleetManagerPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_PORTAL_LOG_EDITS));
        Assert.assertFalse(fleetManagerPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_PORTAL_MALFUNCTIONS_AND_DATA_DIAGNOSTICS));
        Assert.assertFalse(fleetManagerPermissionsMap.get(PERM_MOBILE_WORKER_EDIT_WORK_ORDERS));
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that " + USER_ROLE_DEFAULT_FLEETMANAGER + " has the correct permissions.", ExtentColor.WHITE));

        userRolePage.clickOnDialogCancelButton();

        WebElement userRoleUserAdminRow = userRolePage.getUserRoleRowByName(USER_ROLE_DEFAULT_USERADMIN);
        userRolePage.clickOnEditUserRole(userRoleUserAdminRow);
        HashMap<String, Boolean> userAdminPermissionsMap = userRolePage.getAllPermissionNamesAndValues();
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_USERS_EDIT_DVIR_FORMS));
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_USERS_EDIT_EQUIPMENT));
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_USERS_EDIT_TERMINALS));
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_USERS_ACCOUNT_ADMIN));
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_USERS_FLEET_MANAGER));
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_USERS_WORK_ORDER_ASSIGNEE));
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_PORTAL_LOG_EDITS));
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_PORTAL_MALFUNCTIONS_AND_DATA_DIAGNOSTICS));
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_DVIR_HISTORY_EDIT_DVIRS));
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_MOBILE_WORKER_EDIT_JOB_SITES));
        Assert.assertFalse(userAdminPermissionsMap.get(PERM_MOBILE_WORKER_EDIT_WORK_ORDERS));
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that " + USER_ROLE_DEFAULT_USERADMIN + " has the correct permissions.", ExtentColor.WHITE));

        userRolePage.clickOnDialogCancelButton();

        WebElement userRoleAssetAdminRow = userRolePage.getUserRoleRowByName(USER_ROLE_DEFAULT_ASSETADMIN);
        userRolePage.clickOnEditUserRole(userRoleAssetAdminRow);
        HashMap<String, Boolean> assetAdminPermissionsMap = userRolePage.getAllPermissionNamesAndValues();
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_USERS_EDIT_DRIVERS));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_USERS_EDIT_DVIR_FORMS));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_USERS_EDIT_TERMINALS));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_USERS_ACCOUNT_ADMIN));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_USERS_FLEET_MANAGER));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_USERS_USER_ADMIN));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_USERS_WORK_ORDER_ASSIGNEE));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_USERS_VIEW_ALL_USERS));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_CERTIFIED_LOGS));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_PORTAL_LOG_EDITS));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_PORTAL_MALFUNCTIONS_AND_DATA_DIAGNOSTICS));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_DVIR_HISTORY_EDIT_DVIRS));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_DVIR_HISTORY_VIEW_DVIR_REPORTS));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_MOBILE_WORKER_EDIT_WORK_ORDERS));
        Assert.assertFalse(assetAdminPermissionsMap.get(PERM_ALERTS_EDIT_ALERTS));
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that " + USER_ROLE_DEFAULT_ASSETADMIN + " has the correct permissions.", ExtentColor.WHITE));

        userRolePage.clickOnDialogCancelButton();

        WebElement userRoleWorkOrderAssigneeRow = userRolePage.getUserRoleRowByName(USER_ROLE_DEFAULT_WORKORDERASSIGNEE);
        userRolePage.clickOnEditUserRole(userRoleWorkOrderAssigneeRow);
        HashMap<String, Boolean> workOrderAssigneePermissionsMap = userRolePage.getAllPermissionNamesAndValues();
        Assert.assertTrue(workOrderAssigneePermissionsMap.get(PERM_USERS_WORK_ORDER_ASSIGNEE));
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that " + USER_ROLE_DEFAULT_WORKORDERASSIGNEE + " has the correct permissions.", ExtentColor.WHITE));

        userRolePage.clickOnDialogCancelButton();

        WebElement userRoleViewOnlyRow = userRolePage.getUserRoleRowByName(USER_ROLE_DEFAULT_VIEW_ONLY);
        userRolePage.clickOnEditUserRole(userRoleViewOnlyRow);
        HashMap<String, Boolean> viewOnlyPermissionsMap = userRolePage.getAllPermissionNamesAndValues();
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_USERS_VIEW_ALL_USERS));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_TERMINALS_VIEW_ALL_TERMINALS));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_DRIVERS_VIEW_PORTAL_DRIVERS_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_CERTIFIED_LOGS));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_PORTAL_DRIVER_LOGS_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_PORTAL_LOG_EDITS));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_DRIVER_LOGS_VIEW_PORTAL_MALFUNCTIONS_AND_DATA_DIAGNOSTICS));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_VIOLATIONS_VIEW_PORTAL_VIOLATIONS_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_UDE_VIEW_PORTAL_UNIDENTIFIED_DRIVING_EVENTS_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_REPORTS_VIEW_PORTAL_HOS_REPORTS_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_DVIR_HISTORY_VIEW_DVIR_REPORTS));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_DVIR_HISTORY_VIEW_PORTAL_DVIR_HISTORY_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_FUEL_RECEIPTS_VIEW_PORTAL_FUEL_RECEIPTS_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_FUEL_RECEIPTS_VIEW_PORTAL_IFTA_REPORTS_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_MOBILE_WORKER_VIEW_PORTAL_JOB_SITES_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_MOBILE_WORKER_VIEW_PORTAL_WORK_ORDERS_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_MOBILE_WORKER_VIEW_PORTAL_WORK_ORDER_REPORTS_TAB));
        Assert.assertTrue(viewOnlyPermissionsMap.get(PERM_LOCATIONS_VIEW_PORTAL_LOCATIONS_TAB));
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that " + USER_ROLE_DEFAULT_VIEW_ONLY + " has the correct permissions.", ExtentColor.WHITE));

        userRolePage.clickOnDialogCancelButton();
    }

    @Test(priority = 10, description = "Verify that all default user roles cannot be edited nor deleted")
    public void UserRole_VerifyDefaultUserRolesAreRestricted() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(HEADER_USERNAME, dashboardPage.getDashboardUsername().toUpperCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userRolePage = dashboardPage.navigateToUserRolesTab();
        Assert.assertEquals(USER_ROLE_HEADER_TEXT, userRolePage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Role Management Page.", ExtentColor.WHITE));

        WebElement userRoleAccountAdminRow = userRolePage.getUserRoleRowByName(USER_ROLE_DEFAULT_ACCOUNTADMIN);
        userRolePage.clickOnEditUserRole(userRoleAccountAdminRow);
        Assert.assertFalse(userRolePage.isDialogSaveButtonEnabled());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that " + USER_ROLE_DEFAULT_ACCOUNTADMIN + " cannot be edited.", ExtentColor.WHITE));

        userRolePage.clickOnDialogCancelButton();

        WebElement userRoleFleetManagerRow = userRolePage.getUserRoleRowByName(USER_ROLE_DEFAULT_FLEETMANAGER);
        userRolePage.clickOnDeleteUserRole(userRoleFleetManagerRow);
        userRolePage.clickOnDialogDeleteButton();
        Assert.assertEquals("Can't delete default user role " + USER_ROLE_DEFAULT_FLEETMANAGER, userRolePage.getTabToastMessage());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that " + USER_ROLE_DEFAULT_FLEETMANAGER + " cannot be deleted.", ExtentColor.WHITE));
    }

    @Test(priority = 11, description = "Verify that the user can create, edit and delete a user role with French and Spanish characters.")
    public void UserRole_CreateEditAndDeleteUserRole() {
        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(HEADER_USERNAME, dashboardPage.getDashboardUsername().toUpperCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed in to the portal.", ExtentColor.WHITE));

        userRolePage = dashboardPage.navigateToUserRolesTab();
        Assert.assertEquals(USER_ROLE_HEADER_TEXT, userRolePage.getTabTitle().toLowerCase());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Switched to the User Role Management Page.", ExtentColor.WHITE));

        userRolePage.clickOnAddUserRole();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Clicked the Add User Role button.", ExtentColor.WHITE));

        userRolePage.typeName(USER_ROLE_NAME_FRENCH);
        userRolePage.clickCategoryUsers();
        userRolePage.clickPermissionAccountAdmin();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered user role name with French characters.", ExtentColor.WHITE));

        userRolePage.clickOnDialogSaveButton();
        Assert.assertEquals("A new user role has been successfully created", userRolePage.getTabToastMessage());

        WebElement createdUserRoleRow = userRolePage.getUserRoleRowByName(USER_ROLE_NAME_FRENCH);
        Assert.assertNotNull(createdUserRoleRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully saved the user role. Verified that the French characters were saved.", ExtentColor.WHITE));

        userRolePage.clickOnEditUserRole(createdUserRoleRow);
        HashMap<String, Boolean> permissionsMap = userRolePage.getAllPermissionNamesAndValues();
        Assert.assertTrue(permissionsMap.get(PERM_USERS_ACCOUNT_ADMIN));
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that created user role has the correct permissions.", ExtentColor.WHITE));

        userRolePage.scrollDialog(false, false, 600);

        userRolePage.typeName(USER_ROLE_NAME_SPANISH);
        userRolePage.clickPermissionAccountAdmin();
        userRolePage.clickPermissionViewAllUsers();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Edited the user role and set the name with Spanish characters.", ExtentColor.WHITE));

        userRolePage.clickOnDialogSaveButton();
        Assert.assertEquals("User role has been successfully updated", userRolePage.getTabToastMessage());

        WebElement updatedUserRoleRow = userRolePage.getUserRoleRowByName(USER_ROLE_NAME_SPANISH);
        Assert.assertNotNull(updatedUserRoleRow);
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully updated the user role. Verified that the Spanish characters were saved.", ExtentColor.WHITE));

        userRolePage.clickOnEditUserRole(updatedUserRoleRow);
        HashMap<String, Boolean> updatedPermissionsMap = userRolePage.getAllPermissionNamesAndValues();
        Assert.assertFalse(updatedPermissionsMap.get(PERM_USERS_ACCOUNT_ADMIN));
        Assert.assertTrue(updatedPermissionsMap.get(PERM_USERS_VIEW_ALL_USERS));
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Verified that updated user role has the correct updated permissions checked.", ExtentColor.WHITE));

        userRolePage.clickOnDialogCancelButton();

        userRolePage.clickOnDeleteUserRole(updatedUserRoleRow);
        userRolePage.clickOnDialogDeleteButton();
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Successfully deleted the user role.", ExtentColor.WHITE));
    }

    @AfterMethod
    public void signOut(ITestResult result) throws Exception {
        signInPage = userRolePage.clickSignOut();
        Assert.assertEquals(HEADER_TEXT, signInPage.getLoginTitle());
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Signed out from the portal", ExtentColor.WHITE));
    }

}
