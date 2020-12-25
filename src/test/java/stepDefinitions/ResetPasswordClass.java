package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.Then;
import pageObjects.BaseClass;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utilities.ReportUtility;
import utilities.ScenarioContext;

public class ResetPasswordClass extends BaseClass {
    /*
    This is the Step Definition class to reset the user's password in the Buildxact application
     */
    public ScenarioContext scenarioContext;

    //Dependency injection
    public ResetPasswordClass(ScenarioContext scenarioContext)
    {
        super(scenarioContext);
        this.scenarioContext = scenarioContext;
    }

    //To reset the user's password from the My Profile screen in the Buildxact application
    @Then ("I reset the user's password under My Profile")
    public void i_reset_the_users_password_under_my_profile() throws Exception {
        Boolean flag = getPage(DashboardPage.class).resetPassword();
        new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.INFO, new ReportUtility(scenarioContext.getExtentTest()).attachScreen(getPage(LoginPage.class).getScreenshot()));
        if(flag) {

            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"New user creation successful");
        }
        else {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"New user creation failed");
        }
    }
}