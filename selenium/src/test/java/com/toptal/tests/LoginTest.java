package com.toptal.tests;

import com.toptal.pages.AccountPage;
import com.toptal.pages.HomePage;
import com.toptal.pages.LoginPage;
import com.toptal.test.BaseTest;
import com.toptal.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author askeledzija
 */
public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    private static WebDriver driver = null;

    LoginPage loginPage;
    AccountPage accountPage;
    HomePage homePage;


    @Parameters
    @Test(description = "TC03 Verify login with invalid email and password", groups = {"selenium"}, priority = 3)
    public void T03_LoginTest() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail("invalid@mail.com");
        loginPage.inputPassword("wrongpasword");

        accountPage = loginPage.signIn();
        Assert.assertTrue(loginPage.isErrorMsg());

    }

    @Parameters
    @Test(description = "TC04 Verify login with valid email or password", groups = {"selenium"}, priority = 4)
    public void T04_LoginTest() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        accountPage = loginPage.signIn();
        homePage = accountPage.goToHomePage();

    }


}


