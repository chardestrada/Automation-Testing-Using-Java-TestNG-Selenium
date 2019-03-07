package util.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest logger;

    ExtentHtmlReporter getHtmlReporter() {
        return htmlReporter;
    }

    void setHtmlReporter(ExtentHtmlReporter htmlReporter) {
        this.htmlReporter = htmlReporter;
    }

    public synchronized static ExtentReports getExtent(){
        if(extent == null){
            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/VisTracksReport.html");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Host Name", "staging.vistracks.com");
            extent.setSystemInfo("Environment", "STAGING");
            extent.setSystemInfo("User Name", "ACCOUNT ADMIN");

            htmlReporter.config().setDocumentTitle("VisTracks Portal Automation Test Results");
            htmlReporter.config().setReportName("VisTracks Portal Automation Test Results");
            htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setCSS(".video-links {text-decoration: underline;}");

        }
        return extent;
    }

    void setExtent(ExtentReports extent) {
        this.extent = extent;
    }

    ExtentTest getLogger() {
        return logger;
    }

    void setLogger(ExtentTest logger) {
        this.logger = logger;
    }
}
