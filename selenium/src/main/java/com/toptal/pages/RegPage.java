package com.toptal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @author askeledzija
 */

public class RegPage extends BasePage {

    @FindBy(how = How.ID, using = "firstname")
    private WebElement firstNameBox;

    @FindBy(how = How.ID, using = "lastname")
    private WebElement lastNameBox;

    @FindBy(how = How.ID, using = "email_address")
    private WebElement emailAddressBox;

    @FindBy(how = How.ID, using = "password")
    private WebElement passBox;

    @FindBy(how = How.ID, using = "password-confirmation")
    private WebElement passConfirmBox;

    @FindBy(how = How.XPATH, using = "//*[@id=\"form-validate\"]/div/div[1]/button")
    private WebElement submitBtn;


    @FindBy(how = How.XPATH, using = "//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div/div")
    private WebElement errorRegMsg;


    public RegPage(WebDriver driver) {
        super(driver);
    }


    public boolean isFirstNameBoxVisible() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(firstNameBox));
        return firstNameBox.isDisplayed();
    }


    public void fillOutTheForm(String fname, String lname, String mail, String pass, String cpass ) {
        isFirstNameBoxVisible();
        firstNameBox.sendKeys(fname);
        lastNameBox.sendKeys(lname);
        emailAddressBox.sendKeys(mail);
        passBox.sendKeys(pass);
        passConfirmBox.sendKeys(cpass);
        //UnComment if you want to create a new user
        submitBtn.click();
        Assert.assertTrue(isErrorRegMsg());
    }



    public boolean isErrorRegMsg() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errorRegMsg));
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

