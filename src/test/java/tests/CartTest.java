package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    // TC-05: Проверка кнопки "Continue Shopping"
    @Test
    public void btnCntnShp() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.backShopping();
        assertEquals(productsPage.getTitle(), "Products");
    }

    // TC-06: Проверка кнопки "Cancel" на странице оформления заказа
    @Test
    public void btnCancelCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.backToCart();
        assertEquals(cartPage.getTitleCart(), "Your Cart");
    }
}