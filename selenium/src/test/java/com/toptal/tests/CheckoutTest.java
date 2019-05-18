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
public class CheckoutTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    private static WebDriver driver = null;

    LoginPage loginPage;
    AccountPage accountPage;
    HomePage homePage;
    ICCPage iccPage;
    CartPage cartPage;


    @Parameters
    @Test(description = "TC10 Checkout using Bank Transfer Payment", groups = {"selenium"}, priority = 10, invocationCount = 1)
    public void TC10_BankTransferPayment() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        accountPage = loginPage.signIn();
        homePage = accountPage.goToHomePage();

        ResultsPage resultsPage = homePage.searchItemByKeyword("Genius HS-M225R");
        List<WebElement> res = resultsPage.returnResultsList();

        logger.info(res.get(0).getText());

        assertTrue(res.get(0).getText().contains("Genius"));


        CartPage cartPage = resultsPage.putItemInCartByTxtAndIndex("Genius", 0);
        //ToDo - write a ticket and improve check
        cartPage.isItemAdded();

        Assert.assertTrue(cartPage.isItemInCartWithText(cartPage.returnCartItemsList(), "Genius"));

        CheckoutPage checkoutPage = cartPage.goToCheckout();

        PaymentPage paymentPage = checkoutPage.fillTheForm("Gundulic", "Osijek", "31000", "325466346");
        paymentPage.bankTransferPayment();

    }

    @Parameters
    @Test(description = "TC11 Checkout using Cash Payment", groups = {"selenium"}, priority = 11)
    public void TC11_CashOnDeliveryPayment() throws Exception {


        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        accountPage = loginPage.signIn();
        homePage = accountPage.goToHomePage();

        ResultsPage resultsPage = homePage.searchItemByKeyword("Genius HS-M225R");
        List<WebElement> res = resultsPage.returnResultsList();

        logger.info(res.get(0).getText());

        assertTrue(res.get(0).getText().contains("Genius"));


        CartPage cartPage = resultsPage.putItemInCartByTxtAndIndex("Genius", 0);
        //ToDo - write a ticket and improve check
        Assert.assertFalse(cartPage.isCartEmpty());
        Assert.assertTrue(cartPage.isItemInCartWithText(cartPage.returnCartItemsList(), "Genius"));

        CheckoutPage checkoutPage = cartPage.goToCheckout();

        PaymentPage paymentPage = checkoutPage.fillTheForm("Gundulic", "Osijek", "31000", "325466346");
        paymentPage.cashOnDeliveryPayment();

    }


    @Parameters
    @Test(description = "TC12 Checkout using Credit Card Payment", groups = {"selenium"}, priority = 12)
    public void TC12_CreditCardPayment() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        accountPage = loginPage.signIn();
        homePage = accountPage.goToHomePage();

        ResultsPage resultsPage = homePage.searchItemByKeyword("Genius HS-M225R");
        List<WebElement> res = resultsPage.returnResultsList();

        logger.info(res.get(0).getText());

        assertTrue(res.get(0).getText().contains("Genius"));


        CartPage cartPage = resultsPage.putItemInCartByTxtAndIndex("Genius", 0);
        //ToDo - write a ticket and improve check
        Assert.assertFalse(cartPage.isCartEmpty());
        Assert.assertTrue(cartPage.isItemInCartWithText(cartPage.returnCartItemsList(), "Genius"));

        CheckoutPage checkoutPage = cartPage.goToCheckout();

        PaymentPage paymentPage = checkoutPage.fillTheForm("Gundulic", "Osijek", "31000", "325466346");

        iccPage = paymentPage.creditCardPayment();
        iccPage.creditCardInfo("Andrej",  "1234123412341234",  "12/2999", "999");

        paymentPage.confirmAndOrderBtn();
    }


    @Parameters
    @Test(description = "TC13 Checkout Negative - Payment not approved", groups = {"selenium"}, priority = 13)
    public void TC13_CheckoutNegative() throws Exception {

        driver = invokeBrowser(Config.USER_URL);
        homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), Config.USER_URL);
        homePage.closeNewsForm();
        loginPage = homePage.signIn();

        loginPage.inputEmail(Config.USER_MAIL);
        loginPage.inputPassword(Config.USER_PASSWORD);

        accountPage = loginPage.signIn();
        homePage = accountPage.goToHomePage();

        ResultsPage resultsPage = homePage.searchItemByKeyword("Genius HS-M225R");
        List<WebElement> res = resultsPage.returnResultsList();

        logger.info(res.get(0).getText());

        assertTrue(res.get(0).getText().contains("Genius"));


        CartPage cartPage = resultsPage.putItemInCartByTxtAndIndex("Genius", 0);
        //ToDo - write a ticket and improve check
        Assert.assertFalse(cartPage.isCartEmpty());
        //cartPage.isItemAdded();
        Assert.assertTrue(cartPage.isItemInCartWithText(cartPage.returnCartItemsList(), "Genius"));

        CheckoutPage checkoutPage = cartPage.goToCheckout();

        PaymentPage paymentPage = checkoutPage.fillTheForm("Gundulic", "Osijek", "31000", "325466346");

        iccPage = paymentPage.creditCardPayment();
        iccPage.creditCardInfo("Andrej",  "1234123412341234",  "12/2999", "999");

        paymentPage.confirmAndOrderBtn();

        //toDo Payment is not approved and we should check Error message

    }

    @Parameters
    @Test(description = "TC09 Remove products from Shopping Cart", groups = {"selenium"}, priority = 14)
    public void TC14_CleanUp() throws Exception {

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



