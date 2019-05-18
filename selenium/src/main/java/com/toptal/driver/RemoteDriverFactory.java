package com.toptal.driver;


import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author askeledzija
 */

public class RemoteDriverFactory {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(RemoteDriverFactory.class);

    static RemoteWebDriver createInstance(String browserName) {
        URL hubUrl = null;
        try {
            hubUrl = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return RemoteDriverFactory.createInstance(hubUrl, browserName);
    }

    static RemoteWebDriver createInstance(URL hubUrl, String browserName) {
        RemoteWebDriver driver = null;

        if (browserName.equalsIgnoreCase("firefox")) {
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(hubUrl, capability);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        }
        if (browserName.equalsIgnoreCase("chrome")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("70.0");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);
            driver = new RemoteWebDriver(hubUrl, capabilities);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            return driver;
        }
        if (browserName.equalsIgnoreCase("ie")) {
            DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
            driver = new RemoteWebDriver(hubUrl, capability);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        }
        if (browserName.equalsIgnoreCase("safari")) {
            DesiredCapabilities capability = DesiredCapabilities.safari();
            driver = new RemoteWebDriver(hubUrl, capability);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        }

        if (browserName.equalsIgnoreCase("edge")) {
            DesiredCapabilities capability = DesiredCapabilities.edge();
//
//            EdgeOptions options = new EdgeOptions();
//
//            capability.setCapability(EdgeOptions.CAPABILITY, options);
//            capability.setVersion("ANY");
            driver = new RemoteWebDriver(hubUrl, capability);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        }

        //logger.info("Browser name: " + browserName);
        return driver;
    }
}
