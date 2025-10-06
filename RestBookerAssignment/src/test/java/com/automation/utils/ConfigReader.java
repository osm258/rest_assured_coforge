package com.automation.utils;

import com.automation.pojo.CreateBookingRequestPojo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    private static final ThreadLocal<java.util.Map<String, Object>> scenarioStore =
            ThreadLocal.withInitial(java.util.HashMap::new);

    public static void initConfig() {
        try {
            prop = new Properties();
            prop.load(new FileReader("src/test/resources/config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        Object value = scenarioStore.get().get(key);
        if (value != null) {
            return value.toString();
        }
        return prop.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        scenarioStore.get().put(key, value);
    }

    public static Object getObject(String key) {
        return scenarioStore.get().get(key);
    }

    public static void setObject(String key, Object value) {
        scenarioStore.get().put(key, value);
    }

    public static void clearScenarioStore() {
        scenarioStore.get().clear();
    }

}
