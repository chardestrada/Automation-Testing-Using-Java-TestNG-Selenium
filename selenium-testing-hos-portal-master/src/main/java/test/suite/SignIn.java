package test.suite;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

import page.module.DashboardPage;
import page.module.SignInPage;
import test.data.SignInData;
import test.data.DashboardData;
import util.extentreport.CustomMarkupHelper;
import util.extentreport.ExtentTestManager;

public class SignIn extends AbstractTestSuite implements SignInData, DashboardData{

    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private boolean needToSignOut = true;

    @BeforeMethod
    public void setup(Method method) {
        signInPage = new SignInPage(getWebDriver());
    }

    @Test(priority = 1, description = "Verify that the user can sign in to the VisTracks portal with valid credentials.")
    public void SignIn_EnterValidCredentials() {
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered valid credentials.", ExtentColor.WHITE));

        dashboardPage = signInPage.enterCredentialsAndSignIn(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(HEADER_USERNAME, dashboardPage.getDashboardUsername().toUpperCase());
        ExtentTestManager.getTest().info(CustomMarkupHelper.createLabel("Signed in to portal.", ExtentColor.WHITE));
    }

    @Test(priority = 2, description = "Verify that the user can not sign in to the VisTracks portal with invalid credentials.")
    public void SignIn_EnterInvalidCredentials() {
        needToSignOut = false;
        ExtentTestManager.getTest().log(Status.INFO, CustomMarkupHelper.createLabel("Entered invalid credentials.", ExtentColor.WHITE));

        dashboardPage = signInPage.enterCredentialsAndSignIn(INVALID_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(INCORRECT_USERNAME_OR_PASSWORD_NOTIFICATION, signInPage.getLoginNotification());

//        uncomment line below and comment the line above to intentionally trigger a 'failed' test
//        Assert.assertEquals(DRIVERS_DO_NOT_HAVE_PORTAL_ACCESS_NOTIFICATION, signInPage.getLoginNotification());
        ExtentTestManager.getTest().info(CustomMarkupHelper.createLabel("Failed to sign in to portal.", ExtentColor.WHITE));
    }

    @AfterMethod
    public void signOut(ITestResult result) {
        if (needToSignOut) {
            signInPage = dashboardPage.clickSignOut();
            needToSignOut = true;
        }
        Assert.assertEquals(HEADER_TEXT, signInPage.getLoginTitle());
    }

}
