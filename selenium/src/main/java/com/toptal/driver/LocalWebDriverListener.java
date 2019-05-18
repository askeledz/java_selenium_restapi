package com.toptal.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * @author askeledzija
 */

public class LocalWebDriverListener implements IInvokedMethodListener {

    private static final Logger logger = LogManager.getLogger(LocalWebDriverListener.class);

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.debug("BEGINNING: driver.LocalWebDriverListener.beforeInvocation");
        if (method.isTestMethod()) {
            // get browser name specified in the TestNG XML test suite file
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");

            // get and set new instance of local WebDriver
            WebDriver driver = LocalDriverFactory.createInstance(browserName);
            DriverManager.setWebDriver(driver);
        } else {
            logger.debug("Provided method is NOT a TestNG testMethod!!!");
        }
        logger.debug("END: driver.LocalWebDriverListener.beforeInvocation");
    }


    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {


        if (method.isTestMethod()) {
            try {
                logger.info("Taking screenshot");
                DriverManager.makeScreenshot();
            } catch (Exception ex) {
                logger.info("ex:\n" + ex.getMessage());
            } finally {
                // close the browser
                WebDriver driver = DriverManager.getDriver();
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }
}