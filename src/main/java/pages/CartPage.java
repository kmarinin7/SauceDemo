package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By CART_ITEMS = By.xpath("//*[@data-test='inventory-item']");
    private final By CART_BADGE = By.xpath("//*[@class='shopping_cart_badge']");
    private final By CONTINUE_SHOPPING_BTN = By.xpath("//*[@data-test='continue-shopping']");
    private final By CHECKOUT_BTN = By.xpath("//*[@data-test='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Проверить загрузку страницы корзины")
    public CartPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(TITLE, "Your Cart"));
        return this;
    }

    @Override
    @Step("Открыть страницу корзины")
    public CartPage openPage() {
        driver.get(BASE_URL + "/cart.html");
        return isPageOpened();
    }

    @Step("Вернуться на страницу с товарами (кнопка 'Continue Shopping')")
    public ProductsPage backShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_SHOPPING_BTN)).click();
        return new ProductsPage(driver);
    }

    @Step("Перейти на страницу оформления заказа (кнопка 'Checkout')")
    public CheckoutPage goCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(CHECKOUT_BTN)).click();
        return new CheckoutPage(driver);
    }

    @Step("Получить заголовок страницы корзины")
    public String getTitleCart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE)).getText();
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