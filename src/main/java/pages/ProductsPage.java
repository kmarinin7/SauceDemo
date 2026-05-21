package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Override
    @Step("Проверить загрузку страницы товаров")
    public ProductsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(TITLE, "Products"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(INVENTORY_ITEMS));
        return this;
    }

    @Override
    @Step("Открыть страницу с товарами")
    public ProductsPage openPage() {
        driver.get(BASE_URL + "/inventory.html");
        return isPageOpened();
    }

    @Step("Добавить товар в корзину по позиции {position}")
    public ProductsPage addToCart(int position) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getAddButtonXpath(position)))).click();
        return this;
    }

    @Step("Удалить товар из корзины по позиции {position}")
    public ProductsPage removeFromCart(int position) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getRemoveButtonXpath(position)))).click();
        return this;
    }

    @Step("Перейти в корзину")
    public CartPage goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(CART_ICON)).click();
        return new CartPage(driver);
    }

    @Step("Выйти из системы через бургер-меню")
    public LoginPage logoutBurger() {
        wait.until(ExpectedConditions.elementToBeClickable(BURGER_MENU)).click();
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK));
        driver.findElement(LOGOUT_LINK).click();
        return new LoginPage(driver);
    }

    @Step("Открыть страницу с товарами")
    public ProductsPage open() {
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }

    @Step("Получить заголовок страницы товаров")
    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE)).getText();
    }

    @Step("Добавить товар в корзину по позиции {position}")
    public void addItemBucket(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Add to cart']", position);
        driver.findElement(By.xpath(xpath)).click();
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

    @Step("Проверить, добавлен ли товар {position} в корзину")
    public boolean isProductAddedToCart(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Remove']", position);
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    @Step("Удалить товар по позиции {position}")
    public void removeItem(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Remove']", position);
        driver.findElement(By.xpath(xpath)).click();
    }
}