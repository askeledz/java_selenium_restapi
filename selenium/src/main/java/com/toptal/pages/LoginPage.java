package com.toptal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author askeledzija
 */

public class LoginPage extends BasePage {

    @FindBy(how = How.ID, using = "email")
    private WebElement input_email_box;

    @FindBy(how = How.ID, using = "pass")
    private WebElement input_password_box;


    @FindBy(how = How.ID, using = "send2")
    private WebElement signInButton;

    @FindBy(how = How.CLASS_NAME, using = "exponea-form")
    private WebElement newsForm;

    @FindBy(how = How.CLASS_NAME, using = "exponea-close-cross")
    private WebElement newsFormCloseBtn;

    @FindBy(how = How.XPATH, using = "//button[@id='btn-cookie-allow']")
    private WebElement cookieAllowBtn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div/div")
    private WebElement wrongCredsMsg;

    @FindBy(how = How.XPATH, using = "//a[@class='action create primary']")
    private WebElement regBtn;




    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public boolean isEmailBoxVisible() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(input_email_box));
        return input_email_box.isDisplayed();
    }

    public boolean isPasswordBoxVisible() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(input_password_box));
        return input_password_box.isDisplayed();
    }


    public boolean iscookieAllowBtnVisible() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(cookieAllowBtn));
        return cookieAllowBtn.isDisplayed();
    }

    public void closeNewsForm() {
        if (isElementPresent(newsForm))
            newsFormCloseBtn.click();
        allowCookie();
    }

    public void allowCookie() {
        if (iscookieAllowBtnVisible()) {
            cookieAllowBtn.click();
        }
    }


    public void inputEmail(String email) {
        isEmailBoxVisible();
        input_email_box.sendKeys(email);
    }

    public void inputPassword(String pass) {
        isPasswordBoxVisible();
        input_password_box.sendKeys(pass);
    }

    public AccountPage signIn() {
        signInButton.click();
        return new AccountPage(driver);
    }

    public RegPage registration() {
        regBtn.click();
        return new RegPage(driver);
    }


    public boolean isErrorMsg() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(wrongCredsMsg));
        return true;
    }



    //Helper methods

    public boolean isElementPresent(WebElement we) {
        try {
            we.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}

