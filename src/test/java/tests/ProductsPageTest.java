package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("SauceDemo E2E")
@Feature("Товары")
public class ProductsPageTest extends BaseTest {

    @Test(
            groups = {"smoke", "regression"},
            description = "TC-11: Проверка заголовка страницы 'Products'",
            testName = "Заголовок Products"
    )
    @Description("Проверка, что после авторизации отображается страница с товарами")
    @Story("Отображение товаров")
    @Severity(SeverityLevel.NORMAL)
    @Owner("kmarinin7")
    @TmsLink("TC-11")
    public void testProductsPageTitle() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(
            groups = {"regression"},
            description = "TC-12: Проверка отображения списка товаров",
            testName = "Количество товаров"
    )
    @Description("Проверка, что на странице отображается 6 товаров")
    @Story("Отображение товаров")
    @Severity(SeverityLevel.NORMAL)
    @Owner("kmarinin7")
    @TmsLink("TC-12")
    public void testProductsListDisplayed() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getItemsCount(), 6);
    }

    @Test(
            groups = {"smoke", "regression"},
            description = "TC-13: Добавление товара в корзину",
            testName = "Добавление в корзину"
    )
    @Description("Добавление товара в корзину и проверка появления кнопки 'Remove'")
    @Story("Управление корзиной")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("kmarinin7")
    @TmsLink("TC-13")
    public void testAddToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isAddButtonVisible(0), "Должна быть кнопка 'Add to cart'");
        productsPage.addToCart(0);
        assertTrue(productsPage.isRemoveButtonVisible(0), "После добавления должна быть кнопка 'Remove'");
    }

    @Test(
            groups = {"regression"},
            description = "TC-14: Удаление товара из корзины",
            testName = "Удаление из корзины"
    )
    @Description("Удаление товара из корзины и проверка возвращения кнопки 'Add to cart'")
    @Story("Управление корзиной")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("kmarinin7")
    @TmsLink("TC-14")
    public void testRemoveFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(0);
        assertTrue(productsPage.isRemoveButtonVisible(0), "После добавления должна быть кнопка 'Remove'");
        productsPage.removeFromCart(0);
        assertTrue(productsPage.isAddButtonVisible(0), "После удаления должна вернуться кнопка 'Add to cart'");
    }

    @Test(
            groups = {"regression"},
            description = "TC-15: Проверка счётчика товаров в иконке корзины",
            testName = "Счётчик корзины"
    )
    @Description("Проверка правильности отображения количества товаров в корзине")
    @Story("Управление корзиной")
    @Severity(SeverityLevel.NORMAL)
    @Owner("kmarinin7")
    @TmsLink("TC-15")
    public void testCartBadgeCount() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(0);
        assertEquals(productsPage.getCartBadgeCount(), "1", "Счётчик корзины должен быть 1");
        productsPage.addToCart(1);
        assertEquals(productsPage.getCartBadgeCount(), "2", "Счётчик корзины должен быть 2");
    }
}