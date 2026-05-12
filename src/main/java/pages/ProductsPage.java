package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductsPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By CART_ICON = By.xpath("//*[@class='shopping_cart_link']");
    private final By BURGER_MENU = By.xpath("//*[@class='bm-burger-button']");
    private final By LOGOUT_LINK = By.xpath("//*[@id='logout_sidebar_link']");
    private final By CART_BADGE = By.xpath("//*[@class='shopping_cart_badge']");
    private final By INVENTORY_ITEMS = By.xpath("//*[@class='inventory_item']");

    private String getAddButtonXpath(int position) {
        return String.format("(//*[@class='inventory_item'])[%d]//button[text()='Add to cart']", position + 1);
    }
    private String getRemoveButtonXpath(int position) {
        return String.format("(//*[@class='inventory_item'])[%d]//button[text()='Remove']", position + 1);
    }

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу с товарами")
    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }

    @Step("Получить заголовок страницы товаров")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавить товар в корзину по позиции {position}")
    public void addItemBucket(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Add to cart']", position);
        driver.findElement(By.xpath(xpath)).click();
    }

    @Step("Добавить товар в корзину по позиции {position}")
    public void addToCart(int position) {
        driver.findElement(By.xpath(getAddButtonXpath(position))).click();
    }

    @Step("Удалить товар из корзины по позиции {position}")
    public void removeFromCart(int position) {
        driver.findElement(By.xpath(getRemoveButtonXpath(position))).click();
    }

    @Step("Проверить, видна ли кнопка 'Add to cart' у товара {position}")
    public boolean isAddButtonVisible(int position) {
        return !driver.findElements(By.xpath(getAddButtonXpath(position))).isEmpty();
    }

    @Step("Проверить, видна ли кнопка 'Remove' у товара {position}")
    public boolean isRemoveButtonVisible(int position) {
        return !driver.findElements(By.xpath(getRemoveButtonXpath(position))).isEmpty();
    }

    @Step("Получить количество товаров на странице")
    public int getItemsCount() {
        return driver.findElements(INVENTORY_ITEMS).size();
    }

    @Step("Получить значение счетчика корзины")
    public String getCartBadgeCount() {
        if (driver.findElements(CART_BADGE).isEmpty()) {
            return "0";
        }
        return driver.findElement(CART_BADGE).getText();
    }

    @Step("Проверить, добавлен ли товар {position} в корзину (есть кнопка Remove)")
    public boolean isProductAddedToCart(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Remove']", position);
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    @Step("Удалить товар по позиции {position}")
    public void removeItem(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Remove']", position);
        driver.findElement(By.xpath(xpath)).click();
    }

    @Step("Перейти в корзину")
    public void goToCart() {
        driver.findElement(CART_ICON).click();
    }

    @Step("Выйти из системы через бургер-меню")
    public void logoutBurger() {
        driver.findElement(BURGER_MENU).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK));
        driver.findElement(LOGOUT_LINK).click();
    }
}