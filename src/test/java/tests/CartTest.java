package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {


    // TC-05: Проверка кнопки "Continue Shopping"
    @Test(
            groups = {"smoke", "regression"},
            description = "TC-05: Проверка кнопки 'Continue Shopping'",
            testName = "Кнопка Continue Shopping"
    )
    public void btnCntnShp() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.backShopping();
        assertEquals(productsPage.getTitle(), "Products");
    }

    // TC-06: Проверка кнопки "Cancel" на странице оформления заказа
    @Test(
            groups = {"regression"},
            description = "TC-06: Проверка кнопки 'Cancel' на странице оформления",
            testName = "Кнопка Cancel"
    )
    public void btnCancelCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.backToCart();
        assertEquals(cartPage.getTitleCart(), "Your Cart");
    }
}