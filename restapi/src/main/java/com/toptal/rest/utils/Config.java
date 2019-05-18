package com.toptal.rest.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * @author askeledzija
 */

public class Config {

    //Logger
    private static final Logger logger = LogManager.getLogger(Config.class);

    private static final Properties PROPS = new Properties();

    static {
        try {
            PROPS.load(Config.class.getResourceAsStream("/config-main.properties"));
            String configName = System.getProperty("configName");
            if (configName != null) {
                PROPS.load(Config.class.getResourceAsStream("/config-" + configName + ".properties"));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    //get properties from file
    public static final String USER_BASE_URI = get("user.baseuri");
    public static final String V1_BASEPATH = get("v1.basepath");
    public static final String V4_BASEPATH = get("v4.basepath");
    public static final String V5_BASEPATH = get("v5.basepath");
    public static String USER_USERNAME = get("user.username");
    public static String USER_ACCESS_TOKEN = get("user.access_token");



    public static String get(String key) {

        //If config-main.properties doesn't contain key then return from comand line
        if (!PROPS.containsKey(key)) {
            //throw new IllegalStateException("Required configuration property " + key + " is not found.");

            // If "user.url" is commented out in properties file, you should provide url in command line
            // e.g. mvn clean test -am -DtestSuite=testng_remote.xml -Durl="http://automationpractice.com/"
//            return USER_URL = System.getProperty("user.url");
        }

        return PROPS.getProperty(key);
    }
}
