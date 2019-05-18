package com.toptal.pages;

import com.toptal.util.MySeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


/**
 * @author askeledzija
 */


public class ItemPage extends BasePage {


    @FindBy(how = How.ID, using = "product-addtocart-button")
    private WebElement addToCartBtn;


    public ItemPage(WebDriver driver) {
        super(driver);
    }


    //Helper method
    public boolean isElementPresent(By by) {
        try {
            driver.findElements(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public CartPage addToCart() throws Exception {
        MySeleniumMethods.scrollToElement(addToCartBtn, driver);
        addToCartBtn.click();
        return new CartPage(driver);
    }


}
