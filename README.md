# SauceDemo UI Test Automation

![UI Tests](https://github.com/burakcilbir/saucedemo-selenium-tests/actions/workflows/tests.yml/badge.svg)

End-to-end UI test automation for [SauceDemo](https://www.saucedemo.com/), a demo e-commerce site, built with **Java, Selenium WebDriver and TestNG** using the **Page Object Model** design pattern.

## Tech Stack

- Java 21
- Selenium WebDriver 4 (Selenium Manager handles the browser driver)
- TestNG (assertions, data providers, test suite)
- Maven
- GitHub Actions (tests run automatically on every push)

## Test Coverage

| Area | Scenario |
|---|---|
| Login | Valid login lands on the products page |
| Login | Invalid logins show the correct error (locked-out user, wrong password, empty username, empty password) — data-driven |
| Products | Adding products updates the cart badge |
| Products | Sorting by price (low to high) orders products correctly |
| Checkout | User completes an order end to end |
| Checkout | Checkout form requires a first name |

## Project Structure

```
src/test/java/com/burakcilbir/
├── pages/                # Page Objects: locators and page actions
│   ├── LoginPage.java
│   ├── InventoryPage.java
│   ├── CartPage.java
│   └── CheckoutPage.java
└── tests/                # Test classes: scenarios and assertions
    ├── BaseTest.java     # Browser setup and teardown
    ├── LoginTest.java
    ├── InventoryTest.java
    └── CheckoutTest.java
```

Tests only talk to page objects, never to raw locators, so a UI change is fixed in one place.

## Running the Tests

Requirements: Java 21+, Maven and Google Chrome.

```bash
mvn test                    # headless
mvn test -Dheadless=false   # watch the browser
```

The HTML report is generated at `target/surefire-reports/index.html`.
