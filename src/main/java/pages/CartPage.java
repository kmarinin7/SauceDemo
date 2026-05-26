package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CartPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By CART_ITEMS = By.xpath("//*[@data-test='inventory-item']");
    private final By CART_BADGE = By.xpath("//*[@class='shopping_cart_badge']");
    private final By CONTINUE_SHOPPING_BTN = By.xpath("//*[@data-test='continue-shopping']");
    private final By CHECKOUT_BTN = By.xpath("//*[@data-test='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
        log.info("Создан объект CartPage");
    }

    @Override
    public CartPage isPageOpened() {
        log.debug("Проверка загрузки страницы корзины");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(TITLE, "Your Cart"));
        log.info("Страница корзины загружена");
        return this;
    }

    public CartPage open() {
        log.info("Открытие страницы корзины: {}/cart.html", BASE_URL);
        driver.get(BASE_URL + "/cart.html");
        return this;
    }

    public ProductsPage backShopping() {
        log.info("Возврат на страницу товаров (Continue Shopping)");
        wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_SHOPPING_BTN)).click();
        return new ProductsPage(driver);
    }

    public CheckoutPage goCheckout() {
        log.info("Переход на страницу оформления заказа (Checkout)");
        wait.until(ExpectedConditions.elementToBeClickable(CHECKOUT_BTN)).click();
        return new CheckoutPage(driver);
    }

    public String getTitleCart() {
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE)).getText();
        log.debug("Заголовок корзины: {}", title);
        return title;
    }

    public int getCartItemsCount() {
        if (driver.findElements(CART_BADGE).isEmpty()) {
            log.debug("Количество товаров в корзине: 0");
            return 0;
        }
        int count = Integer.parseInt(driver.findElement(CART_BADGE).getText());
        log.debug("Количество товаров в корзине: {}", count);
        return count;
    }

    public boolean isCartEmpty() {
        boolean empty = driver.findElements(CART_ITEMS).isEmpty();
        log.debug("Корзина пуста: {}", empty);
        return empty;
    }
}