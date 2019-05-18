package com.toptal.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author askeledzija
 */


public class BasePage {

    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;

    @FindBy(how = How.CLASS_NAME, using = "logo")
    public WebElement logoImage;

    @FindBy(how = How.ID, using = "sn-search")
    public WebElement searchBox;

    @FindBy(how = How.ID, using = "sn-search-submit-btn")
    public WebElement searchBtn;


    @FindBy(how = How.XPATH, using = "//a[@class=\"action showcart\"]")
    public WebElement cartBtn;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
