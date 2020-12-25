package utilities;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Map;

public class ReportUtility {
      /*
     This is a extent report utility class that helps in logging and synchronizing the test results into the report
      */

    public ReportUtility(ExtentTest test){
        this.extentTest=test;
    }
    private ExtentTest extentTest;
    private static Map<Integer, String> tcName;
    private static Map<Integer, ExtentTest> extentThread;

    private static synchronized ExtentTest extentThread() {
        return (ExtentTest)extentThread.get((int)Thread.currentThread().getId());
    }

    public void log(LogStatus logStatus, String log)
    {
        extentTest.log(logStatus,log);
    }

    public String attachScreen(String filePath)
    {
        return extentTest.addScreenCapture(filePath);
    }

}