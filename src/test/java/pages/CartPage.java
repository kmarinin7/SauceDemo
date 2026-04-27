package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By CART_ITEMS = By.xpath("//*[@data-test='inventory-item']");
    private final By CART_BADGE = By.xpath("//*[@class='shopping_cart_badge']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // вернуться на страницу с товарами
    public void backShopping() {
        driver.findElement(By.xpath("//*[@data-test='continue-shopping']")).click();
    }

    // перейти на страницу оформления заказа
    public void goCheckout() {
        driver.findElement(By.xpath("//*[@data-test='checkout']")).click();
    }

    // проверка, что мы на странице корзина
    public String getTitleCart() {
        return driver.findElement(TITLE).getText();
    }

    // получить количество товаров в корзине
    public int getCartItemsCount() {
        if (driver.findElements(CART_BADGE).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(driver.findElement(CART_BADGE).getText());
    }

    // проверить корзину на пустоту
    public boolean isCartEmpty() {
        return driver.findElements(CART_ITEMS).isEmpty();
    }
}