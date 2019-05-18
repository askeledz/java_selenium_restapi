package com.toptal.pages;

import com.toptal.util.MySeleniumMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author askeledzija
 */


public class CheckoutPage extends BasePage {


    @FindBy(how = How.XPATH, using = "//input[@name='firstname']")
    private WebElement firstName;

    @FindBy(how = How.XPATH, using = "//input[@name='lastname']")
    private WebElement lastName;

    @FindBy(how = How.XPATH, using = "//input[@name='street[0]']")
    private WebElement street0;


    @FindBy(how = How.XPATH, using = "//input[@name='city']")
    private WebElement city;


    @FindBy(how = How.XPATH, using = "//input[@name='postcode']")
    private WebElement postcode;


    @FindBy(how = How.XPATH, using = "//input[@name='telephone']")
    private WebElement telephone;


    @FindBy(how = How.XPATH, using = "//input[@name='ko_unique_1']")
    private WebElement radioBtn;


    @FindBy(how = How.XPATH, using = "//form[@class='form methods-shipping']//button[@type='submit']")
    private WebElement proceedBtn;


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    public boolean isformVisible() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(firstName));
        return firstName.isDisplayed();
    }

    public boolean isBtnVisible() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(radioBtn));
        return radioBtn.isDisplayed();
    }


    public PaymentPage fillTheForm(String st, String ct, String pc, String tp) throws Exception {

        // Needs to be done only once
        //        street0.sendKeys(st);
        //        city.sendKeys(ct);
        //        postcode.sendKeys(pc);
        //        telephone.sendKeys(tp);

        WebDriverWait wait = new WebDriverWait(driver, 120);
        MySeleniumMethods.scrollToElement(proceedBtn, driver);
        proceedBtn.submit();
        return new PaymentPage(driver);
    }

}

