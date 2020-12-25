package pageObjects;

import org.testng.asserts.SoftAssert;
import utilities.TestDataHelper;
import utilities.ExcelUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import java.util.Map;

public class LoginPage extends BaseClass {
    /*
    This is the Page Object class for the Login page of the Buildxact application
     */

    //Elements for login page
    By usernameTextBox = By.id("username");
    By continueButton = By.id("bx-continue");
    By passwordTextBox = By.id("password");
    By loginButton = By.xpath("//button[@type='submit']");
    By busyOverlayContainer = By.id("busy-overlay-container");
    By collapseExpandMenuButton = By.xpath("//*[@title='Collapse/Expand Menu']");
    By alertDangerText = By.xpath("//*[@class='alert alert-danger']");
    String expectedDashboardPageTitle = "Buildxact-stg: Dashboard";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Method to login to Buildxact application with existing user ID and existing password
    public boolean loginToBuilxact(String testCaseName, String userName, String password) throws Exception {
        try {
            String userID = getCellData(testCaseName, "Login", userName);
            String loginPassword = getCellData(testCaseName, "Login", password);

            getCommands().moveToDefaultContent();
            getCommands().waitForElementVisible(usernameTextBox);
            getCommands().clear(usernameTextBox);
            getCommands().type(usernameTextBox, userID);
            getCommands().waitAndClick(continueButton);

            getCommands().waitForElementVisible(passwordTextBox);
            getCommands().clear(passwordTextBox);
            getCommands().type(passwordTextBox, loginPassword);
            getCommands().waitAndClick(loginButton);
            getCommands().captureScreenshot();
            return true;
        }

        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to complete login to Buildxact verification");
            getCommands().captureScreenshot();
            return false;
        }
    }

    //Mthod to fetch data from excel according to the test case name
    public static Map<String, Map<String, String>> getExcelDataAsMap(String use) throws Exception {
        String buildxactDataSheet = TestDataHelper.getUsersDataSheet();
        String usersSheet = TestDataHelper.getUsersDataSheet_SheetName();

        if(use.toUpperCase().contains("NEW USER CREATION")) {
            usersSheet = TestDataHelper.getUsersDataSheet_NewUsersSheetName();
        }

        ExcelUtility ex = new ExcelUtility(buildxactDataSheet, usersSheet);
        Map<String, Map<String, String>> excelData =ex.getExcelAsMap();
        return excelData;
    }

    //Method to fetch the cell text from excel
    public static String getCellData(String testCaseName, String use, String columnName) throws Exception {
        Map<String, Map<String, String>> excelData = getExcelDataAsMap(use);
        String cellValue = excelData.get(testCaseName).get(columnName);
        return cellValue;
    }

    //Method to verify if the login is successful
    public Boolean verifyLoginSuccessful() {
        Boolean result = false;
        SoftAssert softAssert = new SoftAssert();
        try {
            if(getCommands().isElementPresent(alertDangerText)){
                softAssert.assertTrue(!getCommands().isElementPresent(alertDangerText),"Login password verification");
                return result;
            }
            getCommands().forceWait(2);
            getCommands().moveToDefaultContent();
            if(getCommands().isElementPresent(busyOverlayContainer)){
                verifyBusyOverlayContainerElementPresence();
            }
            if(getCommands().verifyTitle(expectedDashboardPageTitle)) {
                result = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to verify if the login is successful");
        }
        return result;
    }

    //Main method that verifies if a login is successful. This takes a parameter 'VerificationType', based on which
    //the script will identify if the login will be a success or a failure
    public Boolean verifyLoginSuccessful(String verificationType) {
        try {
            Boolean result = verifyLoginSuccessful();
            if(verificationType.equalsIgnoreCase("Login verification without resetting password")) {
                if(result){
                    return result;
                }
            }
            else if (verificationType.equalsIgnoreCase("Login verification after resetting password")) {
                if(!result){
                    return true;
                }
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to verify if the login is successful and write the result into excel");
            return false;
        }
    }

    //This method handles the overlay container element in the Buildxact application
    public void verifyBusyOverlayContainerElementPresence() {
        if(getCommands().isDisplayed(busyOverlayContainer)) {
            getCommands().fluentWait(busyOverlayContainer);
            while (getCommands().isDisplayed(busyOverlayContainer)) {
                getCommands().waitForElementNotVisible(busyOverlayContainer);
                if (getCommands().isNotDisplayed(busyOverlayContainer)) {
                    break;
                }
            }
            getCommands().waitForElementVisible(collapseExpandMenuButton);
        }
    }
    // This method takes screenshot of the browser
    public String getScreenshot() {
        return getCommands().captureScreenshot();
    }
}