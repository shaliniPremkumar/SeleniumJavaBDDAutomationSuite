package pageObjects;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import utilities.ReportUtility;
import utilities.ScenarioContext;
import utilities.WebDriverUtility;
import constants.Constants;

public class BaseClass {
    /*
    This is the base class implementing dependency injection for web driver
     */
    ScenarioContext scenarioContext;

    public BaseClass(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    WebDriver driver;
    ExtentTest extentTest;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }

    public BaseClass() {

    }
    protected WebDriverUtility getCommands(){
        return new WebDriverUtility(this.driver);
    }
    protected ReportUtility getReports() {
        return new ReportUtility(this.extentTest);
    }

    public <TPage> TPage getPage(Class<TPage> pageClass) {
        try {
            WebDriver driver = getDriver();
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private WebDriver getDriver() {
        return (WebDriver) this.scenarioContext.get(Constants.WEBDRIVER_KEY);
    }
}
