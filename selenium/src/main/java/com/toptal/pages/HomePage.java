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


public class HomePage extends BasePage {


    @FindBy(how = How.XPATH, using = "//a[@href=\"https://www.sancta-domenica.hr/customer/account/login/\"]")
    private WebElement signInBtn;

    @FindBy(how = How.CLASS_NAME, using = "exponea-form")
    private WebElement newsForm;

    @FindBy(how = How.CLASS_NAME, using = "exponea-close-cross")
    private WebElement newsFormCloseBtn;

    @FindBy(how = How.XPATH, using = "//button[@id='btn-cookie-allow']")
    private WebElement cookieAllowBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public ResultsPage searchItemByKeyword(String keyWord) {
        searchBox.clear();
        searchBox.sendKeys(keyWord);
        searchBtn.submit();
        return new ResultsPage(driver);
    }

    public LoginPage signIn() throws Exception {
        signInBtn.click();
        return new LoginPage(driver);
    }

    public CartPage goToCart() throws Exception {
        MySeleniumMethods.scrollToElement(cartBtn, driver);
        cartBtn.click();
        return new CartPage(driver);
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
