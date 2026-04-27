package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    // TC-07: Успешное оформление заказа
    @Test
    public void placeOrder() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemBucket(1);
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.makeOrder("David", "Wilson", "789-012");
        assertEquals(checkoutPage.getTitleCheckout(), "Checkout: Overview");
    }

    // TC-08: Пустое поле First Name
    @Test
    public void emptyFirstName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemBucket(1);
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.makeOrder("", "Wilson", "789-012");
        assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
    }

    // TC-09: Пустое поле Last Name
    @Test
    public void emptyLastName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemBucket(1);
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.makeOrder("David", "", "789-012");
        assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
    }

    // TC-10: Пустое поле Postal Code
    @Test
    public void emptyPostalCode() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemBucket(1);
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.makeOrder("David", "Wilson", "");
        assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
    }
}