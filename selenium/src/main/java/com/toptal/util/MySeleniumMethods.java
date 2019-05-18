package com.toptal.util;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
/**
 * @author askeledzija
 */

public class MySeleniumMethods {

    private static final Logger logger = LogManager.getLogger(MySeleniumMethods.class);

    /** Is Element Displayed
     * @param locator  By.? should be provided
     */
    public static boolean isDisplayed(By locator, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    /** Find element by xpath
     * @param xPath  String
     */
    public static WebElement getElementByXpath(String xPath, WebDriver driver) {
        return driver.findElement(By.xpath(xPath));
    }

    /** Find element by css
     * @param css  String
     */
    public static WebElement getElementByCSS(String css, WebDriver driver) {
        return driver.findElement(By.xpath(css));
    }

    /** Find element by LinkText
     * @param linkText  String
     */
    public static WebElement getElementByLinkText(String linkText, WebDriver driver) {
        return driver.findElement(By.linkText(linkText));
    }


    /** Title contains string
     * @param str  String which title contains
     */
    public static boolean pageTitleContains(String str, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.titleContains(str));
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }

    }

     /** Take screenshot
     * @param className  simple class name to make screenshot name
     */
    public static void takeScreenshot(WebDriver driver, String className) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screenshot_" + className + ".png"));
            logger.info("The screenshot is taken!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** If you want to move slider ( both horizontal and vertical) , you can use this method:
     * Move / Drag slider by some offset
     * A convenience method that performs
     * click-and-hold at the location of the source element,
     * moves by a given offset, then releases the mouse.
     *
     * @param slider  webElement
     * @param xOffset - horizontal move offset.
     *                Positive value means moving to the right,
     *                negative - to the left
     * @param yOffset - vertical move offset.
     *                Positive value means moving up, negative - down
     * @throws Exception
     */
    public static void dragSlider(WebElement slider, int xOffset, int yOffset, WebDriver driver) throws Exception {
        Actions moveSlider = new Actions(driver);
        Action action = moveSlider.clickAndHold(slider)
                .moveByOffset(xOffset, yOffset)
                .release()
                .build();
        action.perform();
        //Thread.sleep(500);
    }


    /** In case if you want to drag element from one position to another, you can use this method:
     * @param element webElement
     * @param xOffset - horizontal move offset.
    Positive value means moving to the right,
    negative - to the left
     * @param yOffset - vertical move offset.
    Positive value means moving up,
    negative - down
     * @throws Exception
     */
    public void dragAndDrop(WebElement element, int xOffset, int yOffset, WebDriver driver)
            throws Exception {
        Actions builder = new Actions(driver);
        Action dragAndDrop = builder.dragAndDropBy(element, xOffset, yOffset) .build();
        dragAndDrop.perform();
    }


    /**
     * Scroll down to Web element
     * A convenience method that performs
     * scroll at the location of the source web element
     * @param element  webElement to scroll down
     * @throws Exception
     */
    public static void scrollToElement(WebElement element, WebDriver driver) throws Exception {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public static String stripNonDigits(final CharSequence input ){
        final StringBuilder sb = new StringBuilder(input.length());
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }


    /** Return Text from passing elements
     * @param element  element
     */
    public static String elementGetText(WebElement element) {
        String text = element.getText();
        return text;
    }
}
