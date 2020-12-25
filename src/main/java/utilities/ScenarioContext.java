package utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.HashMap;

public class ScenarioContext {
    /*
    This class helps with the dependency injection by registering the instances to HashMaps and retrieving them
     */
    private HashMap<String, Object> a = new HashMap();

    public ScenarioContext() {
    }

    public void registerInstance(String var1, Object var2) {
        this.a.put(var1, var2);
    }

    public Object get(String var1) {
        return this.a.get(var1);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private String sessionId="init";

    public String getWebDriver() {
        return WebDriver;
    }

    public void setWebDriver(String webDriver) {
        WebDriver = webDriver;
    }

    private String WebDriver;

    public ReportUtility reportUtility;


    public static ExtentReports getExtentReports() {
        return extentReports;
    }

    public static void registerExtentReportInContext(ExtentReports extentReports) {
        ScenarioContext.extentReports = extentReports;
    }

    public static ExtentReports extentReports;


    public ExtentTest getExtentTest() {
        return extentTest;
    }

    public void setExtentTest(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }

    public ExtentTest extentTest;

}
