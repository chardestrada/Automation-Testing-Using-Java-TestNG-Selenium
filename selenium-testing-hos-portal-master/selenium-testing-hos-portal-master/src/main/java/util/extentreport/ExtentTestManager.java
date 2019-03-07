package util.extentreport;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentReportManager.getExtent();

    public static synchronized ExtentTest getTest() {
        return (ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.flush();
    }

    public static synchronized ExtentTest startTest(String testName, String desc, String category) {
        ExtentTest test = extent.createTest(testName, desc).assignCategory(category);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

}
