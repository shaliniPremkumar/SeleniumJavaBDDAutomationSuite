package pageObjects;

import org.openqa.selenium.*;
import java.util.List;

import static pageObjects.LoginPage.getCellData;
    /*
    This is the Page Object class for the Inbox page of the Ghost inspector email application
     */

public class MailboxPage extends BaseClass {
    //Mailbox elements
    By inboxTable = By.id("inbox");
    By resetPasswordButton = By.linkText("RESET PASSWORD");
    By passwordTextBox = By.id("pswd");
    By passwordRepeatTextBox = By.id("pswd-rpt");
    By passwordSubmitButton = By.xpath("//*[@type='submit']");
    By busyOverlayContainer = By.id("busy-overlay-container");
    String pageTitle = "Buildxact-stg: Dashboard";
    By activateButton = By.linkText("ACTIVATE");

    public MailboxPage(WebDriver driver) {
        super(driver);
    }

    //This method accesses the inbox, clicks on reset / activation emails and resets / sets the user password
    public Boolean accessMailBoxResetPassword(String testCaseName) throws Exception {
        try {
            loginToMailBox(testCaseName, "Old user login");
            String passwordAfterReset = getCellData(testCaseName, "Login", "PasswordAfterReset");
            selectMail("Reset your Buildxact account password");
            resetPassword(passwordAfterReset,"reset");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to access user's mail box and reset password");
            return false;
        }
    }

    //This method logs into the user's ghost inspector email id
    private void loginToMailBox(String testCaseName, String use) throws Exception {
        try {
            String userID = getCellData(testCaseName, use, "UserID");
            String[] splitList = userID.split("@");
            String userName = splitList[0];
            String url = "https://email.ghostinspector.com/" + userName;
            getCommands().navigateToURL(url);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to login to mail box");
        }
    }

    //This method resets / sets the user's password in buildxact application
    private void resetPassword(String password, String windowNameEnding) {
        try {
            if(windowNameEnding.toUpperCase().contains("RESET")) {
                getCommands().moveToDefaultContent();
                getCommands().fluentWait(resetPasswordButton);
                getCommands().waitForElementVisible(resetPasswordButton);
                getCommands().waitAndClick(resetPasswordButton);
            }
            getCommands().switchToWindow("app-v2-staging.buildxact.com/"+windowNameEnding);
            getCommands().waitForElementVisible(passwordTextBox);
            getCommands().clear(passwordTextBox);
            getCommands().type(passwordTextBox,password);
            getCommands().clear(passwordRepeatTextBox);
            getCommands().type(passwordRepeatTextBox,password);
            getCommands().waitAndClick(passwordSubmitButton);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to reset the user's password");
        }
    }

    //This method selects the reset / activation email
    private void selectMail(String mailSubject) {
        try {
            By rowTagName = By.tagName("tr");
            By columnTagName = By.tagName("td");

            getCommands().waitForElementVisible(inboxTable);
            WebElement table = getCommands().getElement(inboxTable);
            List <WebElement> rowsList = getCommands().getElementsFromAnotherElement(table, rowTagName);
            List<WebElement> columnsList = null;
            Boolean flag = false;
            for (WebElement row : rowsList) {
                columnsList = row.findElements(columnTagName);
                for (WebElement column : columnsList) {
                    if (column.getText().equalsIgnoreCase(mailSubject)) {
                        column.click();
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                    break;
                }
            }
            if(flag==false) {
                System.out.println("Mail not found.");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to open the user's password reset email");
        }
    }

    //This method activates the user's account
    public boolean accessMailBoxActivateAccount(String testCaseName) {
        try {
            String use = "NEW USER CREATION";
            loginToMailBox(testCaseName, use);
            selectMail("Welcome to Buildxact");
            String password = getCellData(testCaseName, use, "Password");
            getCommands().fluentWait(activateButton);
            getCommands().waitForElementVisible(activateButton);
            getCommands().waitAndClick(activateButton);
            resetPassword(password,"activate");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to activate account");
            return false;
        }
    }

}