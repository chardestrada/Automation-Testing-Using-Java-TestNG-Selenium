package test.suite;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

import util.listener.WebEventListener;
import util.logging.SpecializedScreenRecorder;
import util.logging.Log;

public class AbstractTestSuite {

    private static ChromeDriver webDriver;
    private static EventFiringWebDriver eventFiringWebDriver;
    private static WebEventListener eventListener;
    private static SpecializedScreenRecorder screenRecorder;

    public WebDriver getWebDriver() {
        return eventFiringWebDriver;
    }

    public void setWebDriver(ChromeDriver webDriver) {
        this.webDriver = webDriver;
    }

    SpecializedScreenRecorder getScreenRecorder() {
        return screenRecorder;
    }

    void setScreenRecorder(SpecializedScreenRecorder screenRecorder) {
        this.screenRecorder = screenRecorder;
    }

    @BeforeSuite
    public void initialize() {
        Log.info("Initializing test environment.");
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        eventFiringWebDriver = new EventFiringWebDriver(webDriver);
        eventListener = new WebEventListener();

        eventFiringWebDriver.register(eventListener);

        eventFiringWebDriver.get("https://staging.vistracks.com/");
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/test-output/screenshots/"+screenshotName+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    protected void startRecording(String filename) throws Exception {
        File file = new File(System.getProperty("user.dir") + "/test-output/screencasts");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0,0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null, file, filename);
        getScreenRecorder().start();
        Thread.sleep(2000);
    }

    protected String stopRecording() throws Exception {
        Thread.sleep(2000);
        getScreenRecorder().stop();

        return getScreenRecorder().getCreatedMovieFiles().get(0).getAbsolutePath();
    }

    public void threeSecondSleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown() {
        Log.info("Tearing down test environment. Opening report...");
        eventFiringWebDriver.get(System.getProperty("user.dir") + "/test-output/VisTracksReport.html");
    }

}
