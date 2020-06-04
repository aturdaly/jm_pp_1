package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public static String getPropertiesValue(String fileName, String key) {
        Properties properties = new Properties();
        try (InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
