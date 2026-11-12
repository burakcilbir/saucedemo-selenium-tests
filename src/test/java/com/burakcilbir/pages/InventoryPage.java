package com.burakcilbir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage {
    private final WebDriver driver;
    private final By title = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By sortDropdown = By.className("product_sort_container");
    private final By prices = By.className("inventory_item_price");

    public InventoryPage(WebDriver driver) { this.driver = driver; }

    public String title() { return driver.findElement(title).getText(); }

    /** productId example: "sauce-labs-backpack" */
    public InventoryPage addToCart(String productId) {
        driver.findElement(By.id("add-to-cart-" + productId)).click();
        return this;
    }

    public int cartCount() {
        List<?> badge = driver.findElements(cartBadge);
        return badge.isEmpty() ? 0 : Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public InventoryPage sortBy(String visibleText) {
        new Select(driver.findElement(sortDropdown)).selectByVisibleText(visibleText);
        return this;
    }

    public List<Double> prices() {
        return driver.findElements(prices).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .toList();
    }

    public CartPage openCart() {
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }
}
