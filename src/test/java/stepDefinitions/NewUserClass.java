package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.Then;
import pageObjects.BaseClass;
import pageObjects.LoginPage;
import pageObjects.MyBusinessPage;
import utilities.ReportUtility;
import utilities.ScenarioContext;

public class NewUserClass extends BaseClass {
    /*
    This is the Step Definition class to create a new user in the buildxact application
     */

    public ScenarioContext scenarioContext;

    //Dependency injection
    public NewUserClass(ScenarioContext scenarioContext)
    {
        super(scenarioContext);
        this.scenarioContext = scenarioContext;
    }

    //To navigate to My Business screen, fill and submit the New User form in Buildxact application
    @Then("I navigate to My Business and fill and submit the new user form as per {string}")
    public void i_navigate_to_my_business_and_fill_and_submit_the_new_user_form_as_per (String testCaseName) throws Exception {
        Boolean flag = getPage(MyBusinessPage.class).navigateCreateNewUser(testCaseName);
        new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.INFO, new ReportUtility(scenarioContext.getExtentTest()).attachScreen(getPage(LoginPage.class).getScreenshot()));
        if(flag) {

            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"New user creation successful");
        }
        else {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"New user creation failed");
        }
    }
}