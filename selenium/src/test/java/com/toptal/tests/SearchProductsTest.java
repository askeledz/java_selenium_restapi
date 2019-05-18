package com.toptal.tests;

import com.toptal.pages.AccountPage;
import com.toptal.pages.HomePage;
import com.toptal.pages.LoginPage;
import com.toptal.pages.ResultsPage;
import com.toptal.test.BaseTest;
import com.toptal.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;
/**
 * @author askeledzija
 */
public class SearchProductsTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    private static WebDriver driver = null;

    LoginPage loginPage;
    AccountPage accountPage;
    HomePage homePage;


    @Parameters
    @Test(description = "TC05 Searching products by Product Name", groups = {"selenium"}, priority = 5)
    public void T05_SearchByProduct() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        accountPage = loginPage.signIn();
        homePage = accountPage.goToHomePage();

        ResultsPage resultsPage = homePage.searchItemByKeyword("LED TV Philips 32");
        List<WebElement> res = resultsPage.returnResultsList();

        logger.info(res.get(0).getText());

        assertTrue(res.get(0).getText().contains("LED TV Philips"));

    }

    @Parameters
    @Test(description = "TC06 Searching products by Model No", groups = {"selenium"}, priority = 6)
    public void T06_SearchByModel() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        accountPage = loginPage.signIn();
        homePage = accountPage.goToHomePage();

        ResultsPage resultsPage = homePage.searchItemByKeyword("2WA95EA");
        List<WebElement> res = resultsPage.returnResultsList();

        logger.info(res.get(0).getText());

        assertTrue(res.get(0).getText().contains("2WA95EA"));

    }


    @Parameters
    @Test(description = "TC07 Searching products by KeyWord", groups = {"selenium"}, priority = 7)
    public void T07_SearchByKeyWord() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        accountPage = loginPage.signIn();
        homePage = accountPage.goToHomePage();

        ResultsPage resultsPage = homePage.searchItemByKeyword("Cover");
        List<WebElement> res = resultsPage.returnResultsList();

        logger.info(res.get(0).getText());

        assertTrue(res.get(0).getText().contains("Cover"));

    }

}


