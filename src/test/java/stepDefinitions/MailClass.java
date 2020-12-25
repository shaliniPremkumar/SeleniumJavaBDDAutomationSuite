package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.Then;
import pageObjects.BaseClass;
import pageObjects.LoginPage;
import pageObjects.MailboxPage;
import utilities.ReportUtility;
import utilities.ScenarioContext;

public class MailClass extends BaseClass {
    /*
    This is the Step Definition class to handle the mail based steps
     */

    public ScenarioContext scenarioContext;

    //Dependency injection
    public MailClass(ScenarioContext scenarioContext)
    {
        super(scenarioContext);
        this.scenarioContext = scenarioContext;
    }

    //To navigate to user's mailbox, open the Reset mail and reset the user's password
    @Then("I navigate to the user's mailbox, open the Password Reset mail and reset the password as per {string}")
    public void i_navigate_to_the_user_mailbox_open_the_Password_Reset_mail_and_reset_the_password_as_per(String testCaseName) throws Exception {
        Boolean flag = getPage(MailboxPage.class).accessMailBoxResetPassword(testCaseName);
        new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.INFO, new ReportUtility(scenarioContext.getExtentTest()).attachScreen(getPage(LoginPage.class).getScreenshot()));
        if(flag) {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"Password reset successful");
        }
        else {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"Password reset failed");
        }
    }

    //To navigate to user's mailbox, open the Welcome mail and set the user's password
    @Then("I navigate to the user's mailbox, open the Welcome mail, activate the user account as per {string}")
    public void i_navigate_to_the_user_mailbox_open_the_welcome_mail_activate_the_user_account_as_per(String testCaseName) throws Exception {
        Boolean flag = getPage(MailboxPage.class).accessMailBoxActivateAccount(testCaseName);
        new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.INFO, new ReportUtility(scenarioContext.getExtentTest()).attachScreen(getPage(LoginPage.class).getScreenshot()));
        if(flag) {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"New user activation successful");
        }
        else {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"New user activation failed");
        }
    }

}