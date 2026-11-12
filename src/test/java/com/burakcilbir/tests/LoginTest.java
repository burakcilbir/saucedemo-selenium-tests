package com.burakcilbir.tests;

import com.burakcilbir.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(description = "Valid user lands on the products page")
    public void validLogin() {
        String title = new LoginPage(driver).open()
                .loginAs("standard_user", "secret_sauce")
                .title();
        assertEquals(title, "Products");
    }

    @DataProvider
    public Object[][] invalidLogins() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Sorry, this user has been locked out."},
                {"standard_user", "wrong_password", "Username and password do not match"},
                {"", "secret_sauce", "Username is required"},
                {"standard_user", "", "Password is required"},
        };
    }

    @Test(dataProvider = "invalidLogins", description = "Invalid logins show the right error")
    public void invalidLogin(String user, String pass, String expectedError) {
        LoginPage page = new LoginPage(driver).open();
        page.loginAs(user, pass);
        assertTrue(page.errorMessage().contains(expectedError),
                "Unexpected error: " + page.errorMessage());
    }
}
