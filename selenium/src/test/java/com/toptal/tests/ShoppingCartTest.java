package com.toptal.tests;

import com.toptal.pages.*;
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

import static org.testng.AssertJUnit.assertTrue;

/**
 * @author askeledzija
 */
public class ShoppingCartTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    private static WebDriver driver = null;

    LoginPage loginPage;
    AccountPage accountPage;
    HomePage homePage;
    CartPage cartPage;
    ResultsPage resultsPage;


    @Parameters
    @Test(description = "TC08 Add products to Shopping Cart", groups = {"selenium"}, priority = 8)
    public void T08_AddProductsToCart() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        accountPage = loginPage.signIn();
        homePage = accountPage.goToHomePage();

        resultsPage = homePage.searchItemByKeyword("Cover");
        List<WebElement> res = resultsPage.returnResultsList();

        logger.info(res.get(0).getText());

        assertTrue(res.get(0).getText().contains("Cover"));
        assertTrue(res.get(1).getText().contains("Cover"));

        cartPage = resultsPage.putItemInCartByTxtAndIndex("Cover", 0);
//        cartPage.isItemAdded();
        Assert.assertTrue(cartPage.returnCartItemsList().size() != 0);
        Assert.assertTrue(cartPage.isItemInCartWithText(cartPage.returnCartItemsList(), "Cover"));
        Assert.assertEquals(cartPage.getQtyByIndex(1), 1, "The values are not equal!");

        cartPage.continueShoping();

        cartPage = resultsPage.putItemInCartByTxtAndIndex("Cover", 1);
//        cartPage.isItemAdded();
        Assert.assertTrue(cartPage.returnCartItemsList().size() != 0);
        Assert.assertTrue(cartPage.isItemInCartWithText(cartPage.returnCartItemsList(), "Cover"));
        Assert.assertEquals(cartPage.getQtyByIndex(2), 1, "The values are not equal!");


        cartPage.continueShoping();
    }

    @Parameters
    @Test(description = "TC09 Remove products from Shopping Cart", groups = {"selenium"}, priority = 9)
    public void T09_RemoveProductsFromCart() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        accountPage = loginPage.signIn();
        homePage = accountPage.goToHomePage();

        cartPage = homePage.goToCart();


        //Remove from cart if any
        if (cartPage.returnCartItemsList().size() != 0) {
            for (int i = cartPage.returnCartItemsList().size(); i >= 1; i--) {
                cartPage.removeFromCartByIndex(i);
            }
        }
        //ToDo - Ticket ECOM001
        //  Assert.assertTrue(cartPage.isCartEmpty());
        Assert.assertTrue(cartPage.returnCartItemsList().size() == 0);
    }
}



