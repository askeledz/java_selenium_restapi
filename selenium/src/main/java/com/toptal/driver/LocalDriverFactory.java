package com.toptal.driver;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;


/**
 * @author askeledzija
 */

public class LocalDriverFactory {

    private static final Logger logger = LogManager.getLogger(LocalWebDriverListener.class);
    //For Jenkins (Linux)
    //public static String chromeDriverPath = "/usr/bin/chromedriver";
    //public static String geckoDriverPath = "/usr/bin/geckodriver";


    static WebDriver createInstance(String browserName) {
        WebDriver driver = null;

        if (browserName.toLowerCase().contains("firefox")) {

            //Set Firefox Headless mode as TRUE
            FirefoxOptions options = new FirefoxOptions();
//            options.setHeadless(true);

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
            //logger.info("Browser name: " + browserName);
            return driver;

        }

        if (browserName.toLowerCase().contains("chrome")) {

            ChromeOptions options = new ChromeOptions();
            //Set Chrome Headless mode as TRUE
//            options.setHeadless(true);
            options.addArguments("disable-infobars");
            options.addArguments("--window-size=1920,1080");

            WebDriverManager.chromedriver().version("2.46").setup();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
            //logger.info("Browser name: " + browserName);
            return driver;

        }
        if (browserName.toLowerCase().contains("ie")) {

            driver = new InternetExplorerDriver();
            //logger.info("Browser name: " + browserName);
            return driver;
        }

        if (browserName.toLowerCase().contains("safari")) {
            System.setProperty("webdriver.safari.logfile", "Safari.log");
            driver = new SafariDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
            //logger.info("Browser name: " + browserName);
            return driver;
        }

        //logger.info("LocalDriverFactory created an instance of WebDriver for: " + browserName);
        return driver;
    }
}