package com.toptal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;


/**
 * @author askeledzija
 */


public class ResultsPage extends BasePage {


    @FindBy(how = How.XPATH, using = "//*[@id=\"amasty-shopby-product-list\"]/div[2]")
    private WebElement resultsGrid;


    public ResultsPage(WebDriver driver) {
        super(driver);
    }


    public List returnResultsList() {
        List<WebElement> results = resultsGrid.findElements(By.tagName("li"));
        return results;
    }

    public CartPage putItemInCartByTxtAndIndex(String txt, int index) {
        List<WebElement> results = resultsGrid.findElements(By.tagName("li"));
        WebElement result = results.get(index).findElement(By.tagName("button"));
        result.submit();
        return new CartPage(driver);
    }

}
