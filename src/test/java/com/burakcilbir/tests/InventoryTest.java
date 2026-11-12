package com.burakcilbir.tests;

import com.burakcilbir.pages.InventoryPage;
import com.burakcilbir.pages.LoginPage;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class InventoryTest extends BaseTest {

    private InventoryPage login() {
        return new LoginPage(driver).open().loginAs("standard_user", "secret_sauce");
    }

    @Test(description = "Adding products updates the cart badge")
    public void addProductsToCart() {
        InventoryPage page = login()
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light");
        assertEquals(page.cartCount(), 2);
    }

    @Test(description = "Sorting by price low to high orders products correctly")
    public void sortByPriceLowToHigh() {
        List<Double> prices = login().sortBy("Price (low to high)").prices();
        assertEquals(prices, prices.stream().sorted().toList());
    }
}
