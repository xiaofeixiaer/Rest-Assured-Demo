package com.init;

import java.io.IOException;
import java.util.Properties;

public class RomProperty {

    private static Properties properties = new Properties();

    static {
        loadFile();
    }

    public RomProperty() {
    }

    static boolean loadFile() {
        String romEnv = System.getProperty("ROM_ENV");
        String sourceFileName = "application_" + romEnv.toLowerCase() + ".property";
        try {
            properties.load(RomProperty.class.getClassLoader().getResourceAsStream(sourceFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}
