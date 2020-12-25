package pageObjects;

import org.openqa.selenium.*;
import static pageObjects.LoginPage.getCellData;

public class MyBusinessPage extends BaseClass {
    /*
    This is the Page Object class for the MyBusiness page of the Buildxact application
     */

    ///Elements for My Business page
    By busyOverlayContainer = By.id("busy-overlay-container");
    By collapseExpandMenuButton = By.xpath("//*[@title='Collapse/Expand Menu']");
    By myBusinessButton = By.id("my-business-button");
    By usersButton = By.xpath("//label[contains(text(),'Users')]");
    By newUserPlusButton = By.xpath("//*[@type='button' and @class='btn btn-success']");
    By pageIdentifier = By.xpath("//*[@class='modal ng-aside horizontal left fade in']");
    By firstNameTextBox = By.xpath("//*[@type='text' and @class='form-control ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-maxlength ng-touched']");
    By lastNameTextBox = By.xpath("//*[@type='text' and @class='form-control ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required ng-valid-maxlength']");
    By emailTextBox = By.xpath("//*[@type='email' and @class='form-control ng-pristine ng-untouched ng-empty ng-valid-email ng-invalid ng-invalid-required ng-valid-maxlength']");
    By mobilePhoneTextBox = By.xpath("//*[@type='tel' and @class='form-control bxtest-input']");
    By okButton = By.xpath("//*[@type='button'and @class='btn btn-primary btn-o btn-wide']");
    By collapseButton = By.id("btn-menuLock-collapse");
    By alertBox = By.xpath("//*[@class='alert alert-danger']");

    public MyBusinessPage(WebDriver driver) {
        super(driver);
    }

    //This method navigates to My Business screen and creates a new user
    public Boolean navigateCreateNewUser(String testCaseName) {
        try {
            String use = "NEW USER CREATION";
            String firstName = getCellData(testCaseName, use, "FirstName");
            String lastName = getCellData(testCaseName, use, "LastName");
            String eMail = getCellData(testCaseName, use, "UserID");
            String mobilePhone = getCellData(testCaseName, use, "MobilePhone");

            getCommands().moveToDefaultContent();
            getCommands().waitForElementVisible(myBusinessButton);
            getCommands().waitAndClick(myBusinessButton);
            getCommands().waitForElementVisible(usersButton);
            getCommands().waitAndClick(usersButton);
            verifyBusyOverlayContainerElementPresence();

            getCommands().fluentWait(newUserPlusButton);
            getCommands().waitForElementVisible(newUserPlusButton);
            getCommands().waitAndClick(newUserPlusButton);
            verifyBusyOverlayContainerElementPresence();

            getCommands().waitForElementVisible(pageIdentifier);
            getCommands().waitAndClick(pageIdentifier);
            getCommands().waitForElementVisible(firstNameTextBox);
            getCommands().waitAndClick(firstNameTextBox);
            getCommands().clear(firstNameTextBox);
            getCommands().type(firstNameTextBox, firstName);

            getCommands().waitForElementVisible(lastNameTextBox);
            getCommands().clear(lastNameTextBox);
            getCommands().type(lastNameTextBox, lastName);

            getCommands().waitForElementVisible(emailTextBox);
            getCommands().clear(emailTextBox);
            getCommands().type(emailTextBox, eMail);

            getCommands().waitForElementVisible(mobilePhoneTextBox);
            getCommands().clear(mobilePhoneTextBox);
            getCommands().type(mobilePhoneTextBox, mobilePhone);

            getCommands().waitForElementVisible(okButton);
            getCommands().waitAndClick(okButton);

            if(getCommands().isElementPresent(alertBox)) {
                return false;
            }

            verifyBusyOverlayContainerElementPresence();
            getCommands().waitForElementVisible(newUserPlusButton);

            getCommands().waitForElementVisible(collapseButton);
            getCommands().waitAndClick(collapseButton);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to create new users");
            return false;
        }
    }

    //This method is to identify and handle the busy overlay container element
    public void verifyBusyOverlayContainerElementPresence() {
        if(getCommands().isDisplayed(busyOverlayContainer)) {
            while (getCommands().isDisplayed(busyOverlayContainer)) {
                getCommands().waitForElementNotVisible(busyOverlayContainer);
                if (getCommands().isNotDisplayed(busyOverlayContainer)) {
                    break;
                }
            }
            getCommands().forceWait(2);
            getCommands().waitForElementVisible(collapseExpandMenuButton);
        }
    }
}