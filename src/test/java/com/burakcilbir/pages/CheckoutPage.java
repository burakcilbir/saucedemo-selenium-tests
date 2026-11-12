package com.burakcilbir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By completeHeader = By.className("complete-header");
    private final By error = By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) { this.driver = driver; }

    public CheckoutPage fillInformation(String first, String last, String zip) {
        driver.findElement(firstName).sendKeys(first);
        driver.findElement(lastName).sendKeys(last);
        driver.findElement(postalCode).sendKeys(zip);
        driver.findElement(continueButton).click();
        return this;
    }

    public CheckoutPage finish() {
        driver.findElement(finishButton).click();
        return this;
    }

    public String completeMessage() { return driver.findElement(completeHeader).getText(); }

    public String errorMessage() { return driver.findElement(error).getText(); }
}
