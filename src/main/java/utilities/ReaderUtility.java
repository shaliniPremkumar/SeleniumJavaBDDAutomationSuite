package utilities;

import com.google.gson.Gson;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class ReaderUtility {
    /*
    This is a property utility class, helps with the reading functions such as reading the JSON as an array,
    reading a file and loading the config.properties file into a Properties object
    */

    public ReaderUtility() {

    }

    public static <T> T[] readJsonAsArray(String var0, Class<T[]> var1) {
        StringBuilder var2 = new StringBuilder();

        try {
            BufferedReader var14 = new BufferedReader(new FileReader(var0));
            Throwable var3 = null;

            while(true) {
                boolean var9 = false;

                try {
                    var9 = true;
                    String var4;
                    if ((var4 = var14.readLine()) == null) {
                        var9 = false;
                        break;
                    }

                    var2.append(var4).append("\n");
                }
                catch (Throwable var11) {
                    var3 = var11;
                    throw var11;
                }
                finally {
                    if (var9) {
                        if (var3!=null) {
                            try {
                                var14.close();
                            }
                            catch (Throwable var10) {
                                var3.addSuppressed(var10);
                            }
                        }
                    }
                }
            }
            var14.close();
        } catch (IOException var13) {
            var13.printStackTrace();
        }
        return (new Gson()).fromJson(String.valueOf(var2), var1);
    }

    public static String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        String str_data = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while (strLine != null)
            {
                if (strLine == null)
                    break;
                str_data += strLine+"\n";

                strLine = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
        }
        return str_data;
    }

    /*
    This method loads the config.properties file into a properties object,
    which helps in retrieving the set config.properties variables.
     */
    public static String propertyLoading(String fileName, String propertyName) {
        Properties prop = new Properties();
        try {
            String input = readFile(fileName);
            prop.load(new StringReader(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName).toUpperCase();
    }
}