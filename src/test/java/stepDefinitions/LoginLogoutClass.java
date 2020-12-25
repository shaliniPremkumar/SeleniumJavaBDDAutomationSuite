package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pageObjects.BaseClass;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utilities.ReportUtility;
import utilities.ScenarioContext;

public class LoginLogoutClass extends BaseClass {

    /*
    This is the Step Definition class to login and logout from Buildxact application
     */

    public ScenarioContext scenarioContext;

    //Creating a dependency injection
    public LoginLogoutClass(ScenarioContext scenarioContext)
    {
        super(scenarioContext);
        this.scenarioContext = scenarioContext;
    }

    //To login to the Buildxact Application using existing password
    @And("I login to Buildxact application as per {string}")
    public void i_login_to_buildxact_application_as_per (String testCaseName) throws Exception {
        Boolean flag = getPage(LoginPage.class).loginToBuilxact(testCaseName, "UserID", "PasswordBeforeReset");
        new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.INFO, new ReportUtility(scenarioContext.getExtentTest()).attachScreen(getPage(LoginPage.class).getScreenshot()));
        if(flag) {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"Buildxact application logging in");
        }
        else {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"Buildxact application logging in");
        }
    }

    //To login to the Buildxact Application using new password
    @Then("I login to the Buildxact application with the new password as per {string}")
    public void i_login_to_the_buildxact_application_with_the_new_password_as_per(String testCaseName) throws Exception {
        Boolean flag = getPage(LoginPage.class).loginToBuilxact(testCaseName, "UserID", "PasswordAfterReset");
        new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.INFO, new ReportUtility(scenarioContext.getExtentTest()).attachScreen(getPage(LoginPage.class).getScreenshot()));
        if(flag) {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"Logged into Buildxact application with new password");
        }
        else {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"Unable to login to Buildxact application with new password");
        }
    }

    //To verify if the login to the Buildxact Application using old password after reset fails
    @Then("I verify if the login is successful or not after logging in with old password")
    public void i_verify_if_the_login_is_successful_or_not_after_logging_in_with_old_password() throws Exception {
        Boolean flag = getPage(LoginPage.class).verifyLoginSuccessful("Login verification after resetting password");
        new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.INFO, new ReportUtility(scenarioContext.getExtentTest()).attachScreen(getPage(LoginPage.class).getScreenshot()));
        if(flag) {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"Buildxact application login verification successful");
        }
        else {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"Buildxact application login verification failed");
        }
    }

    //To verify if the login to the Buildxact Application using current password is successful
    @Then("I verify if the login is successful or not")
    public void i_verify_if_the_login_is_successful_or_not() throws Exception {
        Boolean flag = getPage(LoginPage.class).verifyLoginSuccessful("Login verification without resetting password");
        new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.INFO, new ReportUtility(scenarioContext.getExtentTest()).attachScreen(getPage(LoginPage.class).getScreenshot()));
        if(flag) {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"Buildxact application login verification successful");
        }
        else {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"Buildxact application login verification failed");
        }
    }

    //To verify if the login to the Buildxact Application using new password is successful
    @And("I verify if the login is successful or not after password reset with old password")
    public void i_verify_if_the_login_is_successful_or_not_after_password_reset_with_old_password() throws Exception {
        Boolean flag = getPage(LoginPage.class).verifyLoginSuccessful("Login verification after resetting password");

        new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.INFO, new ReportUtility(scenarioContext.getExtentTest()).attachScreen(getPage(LoginPage.class).getScreenshot()));
        if(flag) {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"Buildxact application login verification successful");
        }
        else {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"Buildxact application login verification failed");
        }
    }

    //To logout from the Buildxact Application
    @And("I logout from Buildxact application")
    public void i_logout_from_buildxact_application() throws Exception {
        getPage(DashboardPage.class).logoutFromBuilxact();
        Boolean flag = getPage(DashboardPage.class).verifyLogoutSuccessful();
        new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.INFO, new ReportUtility(scenarioContext.getExtentTest()).attachScreen(getPage(LoginPage.class).getScreenshot()));
        if(flag) {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.PASS,"Buildxact application logout successful");
        }
        else {
            new ReportUtility(scenarioContext.getExtentTest()).log(LogStatus.FAIL,"Buildxact application logout failed");
        }
    }
}