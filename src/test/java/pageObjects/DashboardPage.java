package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class DashboardPage extends BaseClass {
    /*
    This is the Page Object class for the Dashboard page of the Buildxact application
     */

    //Elements for dashboard page
    String expectedDashboardPageTitle = "Buildxact-stg: Dashboard";
    String expectedLoginPageTitle = "Buildxact-stg: Login";
    By busyOverlayContainer = By.id("busy-overlay-container");
    By myProfileButton = By.id("my-profile-button");
    By collapseExpandMenuButton = By.xpath("//*[@title='Collapse/Expand Menu']");
    By logoutButton = By.xpath("//*[contains(text(),'Log Out')]");
    By confirmationPopup = By.id("swal2-title");
    By yesButton = By.xpath("//*[text()='Yes']");
    By continueButton = By.id("bx-continue");
    By okButton = By.xpath("//*[text()='OK']");
    By collapseButton = By.id("btn-menuLock-collapse");
    By sidebarMenuButton = By.xpath("//*[@class='icon-ic-menu']");
    By resetPasswordButton = By.xpath("//*[contains(text(),'Reset Password')]");


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    //This method logs out the user from the Buildxact application
    public void logoutFromBuilxact() throws Exception {
        try {
            getCommands().moveToDefaultContent();
            verifyBusyOverlayContainerElementPresence();
            getCommands().forceWait(3);
            getCommands().waitForElementVisible(myProfileButton);
            getCommands().fluentWait(myProfileButton);
            getCommands().waitAndClick(myProfileButton);
            getCommands().waitForElementVisible(logoutButton);
            getCommands().waitAndClick(logoutButton);
            getCommands().waitForElementVisible(confirmationPopup);
            getCommands().fluentWait(yesButton);
            getCommands().waitAndClick(yesButton);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to logout from Buildxact");
        }
    }

    //This method verifies if the logout is successful
    public Boolean verifyLogoutSuccessful() {
        getCommands().waitForElementVisible(continueButton);
        return getCommands().verifyTitle(expectedLoginPageTitle);
    }

    //This method resets the user's Buildxact application password
    public Boolean resetPassword(){
        try {
            getCommands().moveToDefaultContent();
            verifyBusyOverlayContainerElementPresence();
            getCommands().fluentWait(collapseExpandMenuButton);
            getCommands().waitForElementVisible(collapseExpandMenuButton);
            getCommands().fluentWait(myProfileButton);
            getCommands().waitForElementVisible(myProfileButton);
            getCommands().forceWait(3);
            getCommands().waitAndClick(myProfileButton);
            getCommands().waitForElementVisible(resetPasswordButton);
            getCommands().waitAndClick(resetPasswordButton);
            getCommands().waitForElementVisible(confirmationPopup);
            getCommands().fluentWait(okButton);
            getCommands().waitAndClick(okButton);
            getCommands().waitForElementVisible(collapseButton);
            getCommands().waitAndClick(collapseButton);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to reset password");
            return  false;
        }
    }

    //This method handles the busy overlay container element
    public void verifyBusyOverlayContainerElementPresence() {
        if(getCommands().isDisplayed(busyOverlayContainer)) {
            while (getCommands().isDisplayed(busyOverlayContainer)) {
                getCommands().waitForElementNotVisible(busyOverlayContainer);
                if (getCommands().isNotDisplayed(busyOverlayContainer)) {
                    break;
                }
            }
            getCommands().waitForElementVisible(collapseExpandMenuButton);
        }
    }
}