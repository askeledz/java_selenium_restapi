package com.toptal.pages;

import org.openqa.selenium.WebDriver;


/**
 * @author askeledzija
 */


public class AccountPage extends BasePage {


    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public HomePage goToHomePage() {
        logoImage.click();
        return new HomePage(driver);
    }
}
