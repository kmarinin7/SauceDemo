package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Epic("SauceDemo E2E")
@Feature("Корзина")
public class CartTest extends BaseTest {

    @Test(
            groups = {"smoke", "regression"},
            description = "TC-05: Проверка кнопки 'Continue Shopping'",
            testName = "Кнопка Continue Shopping"
    )
    @Description("Нажать кнопку 'Continue Shopping' на странице корзины и вернуться к товарам")
    @Story("Навигация по корзине")
    @Severity(SeverityLevel.NORMAL)
    @Owner("kmarinin7")
    @TmsLink("TC-05")
    public void btnCntnShp() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.backShopping();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(
            groups = {"regression"},
            description = "TC-06: Проверка кнопки 'Cancel' на странице оформления",
            testName = "Кнопка Cancel"
    )
    @Description("Нажать кнопку 'Cancel' на странице оформления заказа и вернуться в корзину")
    @Story("Навигация по корзине")
    @Severity(SeverityLevel.NORMAL)
    @Owner("kmarinin7")
    @TmsLink("TC-06")
    public void btnCancelCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.backToCart();
        assertEquals(cartPage.getTitleCart(), "Your Cart");
    }
}