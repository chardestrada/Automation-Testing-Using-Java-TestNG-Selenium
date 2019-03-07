package util.logging;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.Status;

public class Log {

    // Initialize Log4j logs
    private static Logger Log = Logger.getLogger(Log.class.getName());

    public static void startTestCase(String testCaseName){
        Log.info("TEST CASE STARTING    : " + testCaseName);
    }

    public static void endTestCase(String testCaseName, Status status){
        if (status == Status.PASS) {
            info("TEST CASE FINISHED    : " + testCaseName);
        } else if (status == Status.FAIL) {
            error("TEST CASE FINISHED   : " + testCaseName);
        } else if (status == Status.SKIP) {
            warn("TEST CASE FINISHED    : " + testCaseName);
        }
    }

    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }

}
