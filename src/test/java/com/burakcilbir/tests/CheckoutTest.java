package com.burakcilbir.tests;

import com.burakcilbir.pages.CartPage;
import com.burakcilbir.pages.CheckoutPage;
import com.burakcilbir.pages.LoginPage;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest {

    private CartPage cartWithBackpack() {
        return new LoginPage(driver).open()
                .loginAs("standard_user", "secret_sauce")
                .addToCart("sauce-labs-backpack")
                .openCart();
    }

    @Test(description = "User can complete an order end to end")
    public void completeOrder() {
        CartPage cart = cartWithBackpack();
        assertEquals(cart.itemNames(), List.of("Sauce Labs Backpack"));

        String message = cart.checkout()
                .fillInformation("Burak", "Cilbir", "16000")
                .finish()
                .completeMessage();
        assertEquals(message, "Thank you for your order!");
    }

    @Test(description = "Checkout requires a first name")
    public void checkoutWithoutFirstName() {
        CheckoutPage checkout = cartWithBackpack().checkout().fillInformation("", "Cilbir", "16000");
        assertTrue(checkout.errorMessage().contains("First Name is required"));
    }
}
