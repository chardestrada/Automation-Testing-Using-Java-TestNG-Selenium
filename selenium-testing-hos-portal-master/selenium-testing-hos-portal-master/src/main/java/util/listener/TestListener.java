package util.listener;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.suite.AbstractTestSuite;
import util.extentreport.ExtentReportManager;
import util.extentreport.ExtentTestManager;
import util.logging.Log;

public class TestListener extends AbstractTestSuite implements ITestListener {

    private StringWriter sw = new StringWriter();
    private PrintWriter pw = new PrintWriter(sw);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Before starting all tests, below method runs.
    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("VisTracks Portal Automated Regression Test is running...");
        iTestContext.setAttribute("WebDriver", getWebDriver());
    }

    // After ending all tests, below method runs.
    @Override
    public void onFinish(ITestContext iTestContext) {
        //Do tear down operations for extent reports reporting
        ExtentTestManager.endTest();
        ExtentReportManager.getExtent().flush();
        Log.info("VisTracks Portal Automated Regression Test is over.");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        try {
            Log.startTestCase(iTestResult.getMethod().getMethodName());
            startRecording(iTestResult.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Start operation for extent reports
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),
                iTestResult.getMethod().getDescription(),
                iTestResult.getTestClass().getRealClass().getSimpleName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        // Log information for passed tests
        Log.endTestCase(iTestResult.getMethod().getMethodName(), Status.PASS);
        ExtentTestManager.getTest().pass(MarkupHelper.createLabel("TEST PASSED", ExtentColor.GREEN));

        try {
            String screencast = stopRecording();

            ExtentTestManager.getTest().info("<a class='video-links' href='" + screencast + "'> DOWNLOAD TEST EXECUTION VIDEO </a>");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.endTestCase(String.format("FAILED %s.%s", iTestResult.getTestClass()
                .getName(), iTestResult.getMethod().getMethodName()), Status.FAIL);

        Throwable cause = iTestResult.getThrowable();
        if (null != cause) {
            cause.printStackTrace(pw);
            Log.error(sw.getBuffer().toString());
        }

        // Get driver from AbstractTestSuite and assign to local webDriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((AbstractTestSuite) testClass).getWebDriver();

        try {
            String screenshot = getScreenshot(webDriver, iTestResult.getName());
            String screencast = stopRecording();

            // Log information for failed tests
            ExtentTestManager.getTest().info("TEST FAILED SCREENSHOT: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
            ExtentTestManager.getTest().fail(cause);
            ExtentTestManager.getTest().info("<a class='video-links' href='" + screencast + "'> DOWNLOAD TEST EXECUTION VIDEO </a>");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.endTestCase(iTestResult.getMethod().getMethodName(), Status.SKIP);

        // Get driver from AbstractTestSuite and assign to local webDriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((AbstractTestSuite) testClass).getWebDriver();

        try {
            String screenshot = getScreenshot(webDriver, iTestResult.getName());
            String screencast = stopRecording();

            // Log information for skipped tests.
            ExtentTestManager.getTest().skip("TEST SKIPPED", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
            ExtentTestManager.getTest().info("<a class='video-links' href='" + screencast + "'> DOWNLOAD TEST EXECUTION VIDEO </a>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.warn("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}
