package com.toptal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


/**
 * @author askeledzija
 */


public class PaymentPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[1]/label")
    private WebElement bankTransferCheckBox;

    @FindBy(how = How.XPATH, using = "//input[@id='cashondelivery']")
    private WebElement cashOnDeliveryCheckBox;

    @FindBy(how = How.XPATH, using = "//input[@id='gpndata_iframe']")
    private WebElement creditCardCheckBox;

    @FindBy(how = How.XPATH, using = "//input[@id='agreement_banktransfer_1']")
    private WebElement agrBankCheckBox;

    @FindBy(how = How.XPATH, using = "//input[@id='agreement_cashondelivery_1']")
    private WebElement agrCashCheckBox;

    @FindBy(how = How.XPATH, using = "//input[@id='agreement_gpndata_iframe_1']")
    private WebElement agrCreditCheckBox;


    @FindBy(how = How.XPATH, using = "//*[@id=\"checkout-payment-method-load\"]/div/div/div[4]/div[2]/div[3]/div[7]/button")
    private WebElement creditCardInputBtn;


    @FindBy(how = How.XPATH, using = "//*[@id=\"checkout-payment-method-load\"]/div/div/div[3]/div[2]/div[4]/div/button")
    private WebElement confirmAndOrderBtn;


    public PaymentPage(WebDriver driver) {
        super(driver);
    }


    public void bankTransferPayment() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", bankTransferCheckBox);
        js.executeScript("arguments[0].click();", agrBankCheckBox);
        confirmAndOrderBtn();
    }

    public void cashOnDeliveryPayment() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cashOnDeliveryCheckBox);
        js.executeScript("arguments[0].click();", agrCashCheckBox);
        confirmAndOrderBtn();
    }

    public ICCPage creditCardPayment() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", creditCardCheckBox);
        js.executeScript("arguments[0].click();", agrCreditCheckBox);

        Thread.sleep(3000);
        js.executeScript("arguments[0].scrollIntoView(true);", creditCardInputBtn);
        js.executeScript("arguments[0].click();", creditCardInputBtn);

        //totla number of frames
        int size = driver.findElements(By.tagName("iframe")).size();
        // You need to go outside for all frames first
        driver.switchTo().defaultContent();
        //Switch to nested frame
        driver.switchTo().frame("gpndata-main-iframe").switchTo().frame("gpndata_checkput_app");

        return new ICCPage(driver);

    }

    public void confirmAndOrderBtn() {
        logger.info("Order confirmed!");
        // Commented for Production
        // confirmAndOrderBtn.click();
    }


}

