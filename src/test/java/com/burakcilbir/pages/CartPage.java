package com.burakcilbir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private final WebDriver driver;
    private final By itemNames = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        // The products page uses the same item class, so wait until the cart page has really loaded.
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("cart.html"));
    }

    public List<String> itemNames() {
        return driver.findElements(itemNames).stream().map(WebElement::getText).toList();
    }

    public CheckoutPage checkout() {
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }
}
