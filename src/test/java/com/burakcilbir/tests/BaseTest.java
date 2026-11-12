package com.burakcilbir.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Run without a visible window by default; use -Dheadless=false to watch the browser.
        if (!"false".equals(System.getProperty("headless"))) options.addArguments("--headless=new");
        options.addArguments("--window-size=1280,900");
        driver = new ChromeDriver(options); // Selenium Manager downloads the matching driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
