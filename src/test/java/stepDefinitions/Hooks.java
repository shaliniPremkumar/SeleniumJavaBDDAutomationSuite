package stepDefinitions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import utilities.ReflectionUtility;
import utilities.ScenarioContext;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {
    /*
    This is the Hooks class that sets the before and after run configurations
     */

    public Hooks(){}
    private ScenarioContext scenarioContext;
    public Hooks(ScenarioContext scenarioContext)
    {
        this.scenarioContext=scenarioContext;
    }
    @BeforeClass
    public static void suiteinitializer()
    {
        String timestampForFileName = getTimestamp();
        ExtentReports extentReports= new ExtentReports(System.getProperty("user.dir")+"\\testReport\\BuildxactAutomationReport_"+timestampForFileName+".html");
        extentReports.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
        ScenarioContext.registerExtentReportInContext(extentReports);
    }

    private static String getTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
        Date date = new Date();
        String output = dateFormat.format(date);
        return output;
    }

    @AfterClass
    public static void suiteTeardown()
    {
        ScenarioContext.getExtentReports().close();
    }

    @Before
    public void BeforeScenario(Scenario scenario)
    {
        System.out.println("-----Before Scenario----");
        ExtentTest extentTest =startExtentTest(scenario.getName());
        this.scenarioContext.setExtentTest(extentTest);
    }


    @After
    public void AfterScenario(Scenario scenario)
    {
        System.out.println("-----After Scenario----");

        this.scenarioContext
                .getExtentReports()
                .endTest(this.scenarioContext.getExtentTest());
    }

    /**
     * To Start extent test case
     * @param testCaseName
     */
    public ExtentTest startExtentTest(String testCaseName){
        ExtentReports report;
        try {
            return new ReflectionUtility()
                    .getStaticFieldValueFromClass("EXTENTREPORTS")
                    .startTest(testCaseName);
        }catch (Exception e)
        {
            System.out.println("error");
            return null;
        }
    }

}