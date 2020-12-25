package configurations;

public class EnvironmentConfiguration {
    /*
    This class gets and sets the environment variables declared in the TestConfiguration.json file
    */

    public String BuildxactStagingURL;
    public String MailboxBaseURL;
    public String UsersDataSheet;
    public String UsersDataSheet_SheetName;
    public String UsersDataSheet_NewUsersSheetName;

    public String getBuildxactStagingURL() {
        return BuildxactStagingURL;
    }

    public void setBuildxactStagingURL(String buildxactStagingURL) {
        BuildxactStagingURL = buildxactStagingURL;
    }

    public String getMailboxBaseURL() {
        return MailboxBaseURL;
    }

    public void setMailboxBaseURL(String mailboxBaseURL) {
        MailboxBaseURL = mailboxBaseURL;
    }

    public String getUsersDataSheet() {
        return UsersDataSheet;
    }

    public void setUsersDataSheet(String usersDataSheet) {
        UsersDataSheet = usersDataSheet;
    }

    public String getUsersDataSheet_SheetName() {
        return UsersDataSheet_SheetName;
    }

    public void setUsersDataSheet_SheetName(String usersDataSheet_SheetName) {
        UsersDataSheet_SheetName = usersDataSheet_SheetName;
    }

    public String getUsersDataSheet_NewUsersSheetName() {
        return UsersDataSheet_NewUsersSheetName;
    }

    public void setUsersDataSheet_NewUsersSheetName(String usersDataSheet_NewUsersSheetName) {
        UsersDataSheet_NewUsersSheetName = usersDataSheet_NewUsersSheetName;
    }


}
