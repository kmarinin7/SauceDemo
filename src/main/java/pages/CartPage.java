package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By CART_ITEMS = By.xpath("//*[@data-test='inventory-item']");
    private final By CART_BADGE = By.xpath("//*[@class='shopping_cart_badge']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Вернуться на страницу с товарами (кнопка 'Continue Shopping')")
    public void backShopping() {
        driver.findElement(By.xpath("//*[@data-test='continue-shopping']")).click();
    }

    @Step("Перейти на страницу оформления заказа (кнопка 'Checkout')")
    public void goCheckout() {
        driver.findElement(By.xpath("//*[@data-test='checkout']")).click();
    }

    @Step("Получить заголовок страницы корзины")
    public String getTitleCart() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Получить количество товаров в корзине")
    public int getCartItemsCount() {
        if (driver.findElements(CART_BADGE).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(driver.findElement(CART_BADGE).getText());
    }

    @Step("Проверить, пуста ли корзина")
    public boolean isCartEmpty() {
        return driver.findElements(CART_ITEMS).isEmpty();
    }
}
