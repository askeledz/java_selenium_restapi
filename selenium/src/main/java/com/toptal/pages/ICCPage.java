package com.toptal.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


/**
 * @author askeledzija
 */


public class ICCPage extends BasePage {


    @FindBy(how = How.ID, using = "card-data-nameoncard")
    private WebElement creditCardNameOnCardTxtBox;

    @FindBy(how = How.ID, using = "card-data-number")
    private WebElement creditCardNumberTxtBox;

    @FindBy(how = How.ID, using = "card-data-exp")
    private WebElement creditCardExpTxtBox;

    @FindBy(how = How.ID, using = "card-data-cvv")
    private WebElement creditCardCVVTxtBox;

    @FindBy(how = How.XPATH, using = "//*[@id=\"btn-pay\"]")
    private WebElement confirmBtn;


    public ICCPage(WebDriver driver) {
        super(driver);
    }


    public void creditCardInfo(String name, String number, String expDate, String cvv) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", creditCardNameOnCardTxtBox);
        creditCardNameOnCardTxtBox.sendKeys(name);

        js.executeScript("arguments[0].scrollIntoView(true);", creditCardNumberTxtBox);
        creditCardNumberTxtBox.sendKeys(number);

        js.executeScript("arguments[0].scrollIntoView(true);", creditCardExpTxtBox);
        creditCardExpTxtBox.sendKeys(expDate);

        js.executeScript("arguments[0].scrollIntoView(true);", creditCardCVVTxtBox);
        creditCardCVVTxtBox.sendKeys(cvv);

        js.executeScript("arguments[0].scrollIntoView(true);", confirmBtn);
        confirmBtn.click();

    }

}

