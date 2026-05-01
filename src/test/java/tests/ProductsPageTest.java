package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsPageTest extends BaseTest {

    // TC-11: Проверка заголовка страницы "Products"
    @Test
    public void testProductsPageTitle() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    // TC-12: Проверка отображения списка товаров
    @Test
    public void testProductsListDisplayed() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getItemsCount(), 6);
    }

    // TC-13: Добавление товара в корзину
    @Test
    public void testAddToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isAddButtonVisible(0), "Должна быть кнопка 'Add to cart'");
        productsPage.addToCart(0);
        assertTrue(productsPage.isRemoveButtonVisible(0), "После добавления должна быть кнопка 'Remove'");
    }

    // TC-14: Удаление товара из корзины
    @Test
    public void testRemoveFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(0);
        assertTrue(productsPage.isRemoveButtonVisible(0), "После добавления должна быть кнопка 'Remove'");
        productsPage.removeFromCart(0);
        assertTrue(productsPage.isAddButtonVisible(0), "После удаления должна вернуться кнопка 'Add to cart'");
    }

    // TC-15: Проверка счётчика товаров в иконке корзины
    @Test
    public void testCartBadgeCount() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(0);
        assertEquals(productsPage.getCartBadgeCount(), "1", "Счётчик корзины должен быть 1");
        productsPage.addToCart(1);
        assertEquals(productsPage.getCartBadgeCount(), "2", "Счётчик корзины должен быть 2");
    }
}