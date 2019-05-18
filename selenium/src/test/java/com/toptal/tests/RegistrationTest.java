package com.toptal.tests;

import com.toptal.pages.AccountPage;
import com.toptal.pages.HomePage;
import com.toptal.pages.LoginPage;
import com.toptal.pages.RegPage;
import com.toptal.test.BaseTest;
import com.toptal.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author askeledzija
 */
public class RegistrationTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RegistrationTest.class);
    private static WebDriver driver = null;

    LoginPage loginPage;
    AccountPage accountPage;
    HomePage homePage;



    @Parameters
    @Test(description = "TC01 Verify registration with already used email", groups = {"selenium"}, priority = 1)
    public void T01_RegistrationTestInvalid() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        RegPage regPage = loginPage.registration();

        regPage.fillOutTheForm("Andrej","Skeledzija","andrej.skeledzija@gmail.com","qwerty", "qwerty");

    }

    //Excluded from testng_selenium.xml
    @Parameters
    @Test(description = "TC02 Verify registration with valid mail", groups = {"selenium"}, priority = 2)
    public void T02_RegistrationTestValid() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();


        RegPage regPage = loginPage.registration();

        //toDo Random mail should be provided
        regPage.fillOutTheForm("firstname","lastname","firstname.lastname@mailinator.com","passtest", "passtest");

        //If creation of new user is UnCommented then you should uncomment this part for verification
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'firstname lastname')]")));

    }




}


