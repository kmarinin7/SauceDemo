package pages;

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

    // открыть страницу товаров
    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }

    // получить заголовок
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    // добавить товар в корзину 1
    public void addItemBucket(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Add to cart']", position);
        driver.findElement(By.xpath(xpath)).click();
    }

    // добавить товар в корзину 2
    public void addToCart(int position) {
        driver.findElement(By.xpath(getAddButtonXpath(position))).click();
    }

    // удалить из корзины
    public void removeFromCart(int position) {
        driver.findElement(By.xpath(getRemoveButtonXpath(position))).click();
    }

    // видима ли кнопка "Add to cart"
    public boolean isAddButtonVisible(int position) {
        return !driver.findElements(By.xpath(getAddButtonXpath(position))).isEmpty();
    }

    // видима ли кнопка "Remove"
    public boolean isRemoveButtonVisible(int position) {
        return !driver.findElements(By.xpath(getRemoveButtonXpath(position))).isEmpty();
    }

    // получить количество товаров на странице
    public int getItemsCount() {
        return driver.findElements(INVENTORY_ITEMS).size();
    }

    // получить значение счетчика корзины
    public String getCartBadgeCount() {
        if (driver.findElements(CART_BADGE).isEmpty()) {
            return "0";
        }
        return driver.findElement(CART_BADGE).getText();
    }

    // получить текст кнопки "Remove" у товара
    public String getRemoveMessage(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Remove']", position);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    // проверить, есть ли кнопка "Remove" у товара
    public boolean isProductAddedToCart(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Remove']", position);
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    // удалить товар по позиции
    public void removeItem(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Remove']", position);
        driver.findElement(By.xpath(xpath)).click();
    }

    // перейти в корзину
    public void goToCart() {
        driver.findElement(CART_ICON).click();
    }

    // выход из системы через бургер-меню
    public void logoutBurger() {
        driver.findElement(BURGER_MENU).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK));
        driver.findElement(LOGOUT_LINK).click();
    }
}