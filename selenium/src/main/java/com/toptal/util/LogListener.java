package com.toptal.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Arrays;
/**
 * @author askeledzija
 */

public class LogListener extends TestListenerAdapter {

    protected static final Logger logger = LogManager.getLogger(LogListener.class);
    private static final String LOG_PREFIX = " - ";

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        propagateResults(tr, "FAILED. " + "<EXCEPTION>: " + tr.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        propagateResults(tr, "SKIPPED");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        propagateResults(tr, "PASSED");
    }

    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
        propagateResults(tr, "STARTED");
    }


    private void propagateResults(ITestResult tr, String testResult) {
        logger.info(tr.getName() + Arrays.toString(tr.getParameters()) + LOG_PREFIX + testResult + "\n");
    }
}
