package com.toptal.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author askeledzija
 */

public class RemoteWebDriverListener implements IInvokedMethodListener {

    private static final Logger logger = LogManager.getLogger(RemoteWebDriverListener.class);


    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.debug("BEGINNING: RemoteWebDriverListener.beforeInvocation");
        if (method.isTestMethod()) {
            // get browser name specified in the TestNG XML test suite file
           String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");

            URL hubURL = null;
            try {
                // get hub URL specified in the TestNG XML test suite file
                hubURL = new URL(method.getTestMethod().getXmlTest().getParameter("hubURL"));
            } catch (MalformedURLException e) {
                logger.info("ex:\n" + e.getMessage() + "");
                e.printStackTrace();
            }
            logger.info("HUB URL: " + hubURL);
            // get and set new instance of remote WebDriver
            WebDriver driver = RemoteDriverFactory.createInstance(hubURL, browserName);
            DriverManager.setWebDriver(driver);
        } else {
            logger.debug("Provided method is NOT a TestNG testMethod!!!");
        }
        logger.debug("END: RemoteWebDriverListener.beforeInvocation");
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            try {
                logger.info("Taking screenshot");
               DriverManager.makeScreenshot();
            } catch (Exception ex) {
                logger.info("ex:\n" + ex.getMessage());
            } finally {
                WebDriver driver = DriverManager.getDriver();
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }
}