package utilities;

import configurations.EnvironmentConfiguration;
import configurations.TestConfiguration;
import constants.enums.Environments;

import java.util.Arrays;

public class TestDataHelper {
    /*
    This class acts as the interface between the user-loaded input files and the HashMaps.
     */

    //This method fetches the current java directory path in test
    public static String getJavaDirectoryPathInTest() {
        return System.getProperty("user.dir").concat("\\src\\test\\java");
    }

    //This method fetches the current environment cofiguration details using config.properties and TestConfiguration.json files
    public static EnvironmentConfiguration getCurrentEnvironmentConfiguration() throws Exception {
        try {
            String envData = ReaderUtility.propertyLoading(System.getProperty("user.dir").concat("\\config.properties"), "Environment");
            Environments environment = Environments.valueOf(envData);
            String file = System.getProperty("user.dir") + "\\TestConfiguration.json";
            TestConfiguration[] testConfiguration = ReaderUtility.readJsonAsArray(file, TestConfiguration[].class);

            TestConfiguration configuration = Arrays.stream(testConfiguration).distinct()
                    .findFirst().orElse(null);
            return configuration.Configuration;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to fetch the current environment");
            return null;
        }
    }

    //This method fetches the UsersDataSheet as declared in TestConfiguration.json file
    public static String getUsersDataSheet() throws Exception {
        return getJavaDirectoryPathInTest()+TestDataHelper.getCurrentEnvironmentConfiguration().getUsersDataSheet();
    }

    //This method fetches the Sheet name (Users) in the UsersDataSheet as declared in TestConfiguration.json file
    public static String getUsersDataSheet_SheetName() throws Exception {
        return TestDataHelper.getCurrentEnvironmentConfiguration().getUsersDataSheet_SheetName();
    }

    //This method fetches the Sheet name (NewUsers) in the UsersDataSheet as declared in TestConfiguration.json file
    public static String getUsersDataSheet_NewUsersSheetName() throws Exception {
        return TestDataHelper.getCurrentEnvironmentConfiguration().getUsersDataSheet_NewUsersSheetName();
    }
}