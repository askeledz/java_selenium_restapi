package com.toptal.pages;

import com.toptal.util.MySeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;


/**
 * @author askeledzija
 */


public class CartPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"amasty-shopby-product-list\"]/div[2]")
    private WebElement resultsGrid;

    @FindBy(how = How.XPATH, using = "//a[@class=\"action continue\"]")
    private WebElement continueShopingBtn;

    @FindBy(how = How.ID, using = "shopping-cart-table")
    private WebElement cartTable;

    @FindBy(how = How.XPATH, using = "//span[@class='counter-number']")
    private WebElement counterNumber;

    //Didn't find better locator -- Developers need to implement some unique identifier
    @FindBy(how = How.XPATH, using = "//a[@title='Ukloni stavku']")
    private WebElement removeItem;

    @FindBy(how = How.XPATH, using = "//button[@data-role=\"proceed-to-checkout\"]")
    private WebElement checkoutBtn;

    @FindBy(how = How.XPATH, using = "//div[@data-bind=\"html: message.text\"]")
    private WebElement itemAddedToCartMsg;


    public CartPage(WebDriver driver) {
        super(driver);
    }


    public List returnResultsList() {
        List<WebElement> results = resultsGrid.findElements(By.tagName("li"));
        return results;
    }

    public void continueShoping() throws Exception {
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(continueShopingBtn));
        MySeleniumMethods.scrollToElement(continueShopingBtn, driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Perform Click on ContinueShoping button using JavascriptExecutor
        js.executeScript("arguments[0].click();", continueShopingBtn);
    }

    public List returnCartItemsList() {

        try {
            List<WebElement> results = cartTable.findElements(By.tagName("tbody"));
            return results;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return Collections.emptyList();
        }
    }

    public boolean isItemInCartWithText(List<WebElement> results, String txt) {
        return results.stream().anyMatch(we -> we.getText().contains(txt));
    }


    public void removeFromCartByIndex(int index) {

        if (index == 1) {
            removeItem.click();
        } else {
            WebElement removeBtn = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody[" + index + "]/tr[2]/td/div/a[3]"));
            removeBtn.click();
        }


    }


    public boolean isCartEmpty() {
        return counterNumber.getText().equals("0");
    }

    public boolean isItemAdded() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(itemAddedToCartMsg));
        return true;
    }

    public String addedItemToCartContainText() {
        return new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(itemAddedToCartMsg)).getText();
    }

    public int getQtyByIndex(int index) {
        int qty = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody[" + index + "]/tr[1]/td[3]/div/div/input")).getAttribute("value"));
        return qty;
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

    public CheckoutPage goToCheckout() throws Exception {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }


}
