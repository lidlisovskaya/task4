package service;

import java.util.ResourceBundle;

public class TestDataReader {
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment","dev"));

    private TestDataReader() {
    }

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
