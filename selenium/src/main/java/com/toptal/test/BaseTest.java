package com.toptal.test;

import com.toptal.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

/**
 * @author askeledzija
 */


public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeTest
    public void doBeforeTest() {

    }

    protected WebDriver invokeBrowser(String url) {
        WebDriver driver = DriverManager.getDriver();
        logger.info("START: " + DriverManager.getBrowserInfo());
        logger.info("Thread id = " + Thread.currentThread().getId());
        logger.info("Driver instance = " + driver.hashCode());
        driver.get(url);
        return driver;
    }



}
