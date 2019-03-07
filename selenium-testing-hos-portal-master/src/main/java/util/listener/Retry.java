package util.listener;

import java.io.IOException;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import test.suite.AbstractTestSuite;
import util.extentreport.ExtentTestManager;

public class Retry extends AbstractTestSuite implements IRetryAnalyzer {

    private int count = 0;
    private static int maxTries = 0; // Run the failed test 2 times

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                      // Check if test not succeed
            if (count < maxTries) {                            // Check if maxtry count is reached
                count++;                                     // Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE);  // Mark test as failed
                extendReportsFailOperations(iTestResult);    // ExtentReports fail operations
                return true;                                 // Tells TestNG to re-run the test
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      // If test passes, TestNG marks it as passed
        }
        return false;
    }

    private void extendReportsFailOperations (ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((AbstractTestSuite) testClass).getWebDriver();

        try {
            String screenshot = getScreenshot(getWebDriver(), iTestResult.getName());

            // Log and screenshot operations for failed tests.
            ExtentTestManager.getTest().log(Status.FAIL,"Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
