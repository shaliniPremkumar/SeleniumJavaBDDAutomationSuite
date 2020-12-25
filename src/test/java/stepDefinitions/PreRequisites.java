package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;
import constants.Constants;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageObjects.BaseClass;
import utilities.ReportUtility;
import utilities.ScenarioContext;
import utilities.TestDataHelper;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PreRequisites extends BaseClass {
    /*
    This is the Step Definition class to execute the pre-requisite steps such as launching the browser
    and navigating to Buildxact application
     */
    private WebDriver driver;

    //Dependency injection
    public ScenarioContext scenarioContext;

    public PreRequisites (ScenarioContext scenarioContext)
    {
        super(scenarioContext);
        this.scenarioContext = scenarioContext;
    }

    //To launch the browser session
    @Given("I launch {string} browser session")
    public void i_launch_browser_session(String browserName) throws Exception {
        try {
            String filePath = System.getProperty("user.dir");
            switch (browserName.toUpperCase())
            {
                case "CHROME" :
                    System.setProperty("webdriver.chrome.driver", filePath + "/src/test/resources/drivers/chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case "FIREFOX" :
                    System.setProperty("webdriver.gecko.driver", filePath + "/src/test/resources/drivers/geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                case "IE" :
                    System.setProperty("webdriver.ie.driver", filePath + "/src/test/resources/drivers/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    break;
                case "EDGE" :
                    System.setProperty("webdriver.edge.driver", filePath + "/src/test/resources/drivers/MicrosoftWebDriver.exe");
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new Exception("Browser "+browserName+" not supported");
            }
            scenarioContext.registerInstance(Constants.WEBDRIVER_KEY,driver);
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,browserName +" is started successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,browserName + " is not started");
        }
    }

    //To navigate to Buildxact application
    @When("I navigate to Buildxact application")
    public void i_navigate_to_buildxact_application() throws Exception {
        try {
            driver.get(Objects.requireNonNull(TestDataHelper.getCurrentEnvironmentConfiguration()).getBuildxactStagingURL());
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"Navigated into Buildxact application successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to navigate to Buildxact application");
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"Navigation to Buildxact application failed");
        }
    }

    @After
    @And("I close the browser")
    public void i_close_the_browser() {
        driver.close();
        driver.quit();
    }
}