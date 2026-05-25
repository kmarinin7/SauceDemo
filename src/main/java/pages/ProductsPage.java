package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ProductsPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By CART_ICON = By.xpath("//*[@class='shopping_cart_link']");
    private final By BURGER_MENU = By.xpath("//*[@class='bm-burger-button']");
    private final By LOGOUT_LINK = By.xpath("//*[@id='logout_sidebar_link']");
    private final By CART_BADGE = By.xpath("//*[@class='shopping_cart_badge']");
    private final By INVENTORY_ITEMS = By.xpath("//*[@class='inventory_item']");

    public ProductsPage(WebDriver driver) {
        super(driver);
        log.info("Создан объект ProductsPage");
    }

    private String getAddButtonXpath(int position) {
        return String.format("(//*[@class='inventory_item'])[%d]//button[text()='Add to cart']", position + 1);
    }

    private String getRemoveButtonXpath(int position) {
        return String.format("(//*[@class='inventory_item'])[%d]//button[text()='Remove']", position + 1);
    }

    @Override
    public ProductsPage isPageOpened() {
        log.debug("Проверка загрузки страницы товаров");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(TITLE, "Products"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(INVENTORY_ITEMS));
        log.info("Страница товаров загружена");
        return this;
    }

    public ProductsPage open() {
        log.info("Открытие страницы товаров: {}/inventory.html", BASE_URL);
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }

    public ProductsPage addToCart(int position) {
        log.info("Добавление товара в корзину. Позиция: {}", position);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getAddButtonXpath(position)))).click();
        return this;
    }

    public ProductsPage removeFromCart(int position) {
        log.info("Удаление товара из корзины. Позиция: {}", position);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getRemoveButtonXpath(position)))).click();
        return this;
    }

    public CartPage goToCart() {
        log.info("Переход в корзину");
        wait.until(ExpectedConditions.elementToBeClickable(CART_ICON)).click();
        return new CartPage(driver);
    }

    public LoginPage logoutBurger() {
        log.info("Выход из системы через бургер-меню");
        wait.until(ExpectedConditions.elementToBeClickable(BURGER_MENU)).click();
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK));
        driver.findElement(LOGOUT_LINK).click();
        return new LoginPage(driver);
    }

    public void addItemBucket(int position) {
        log.info("Добавление товара в корзину (старый метод). Позиция: {}", position);
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Add to cart']", position);
        driver.findElement(By.xpath(xpath)).click();
    }

    public boolean isAddButtonVisible(int position) {
        boolean visible = !driver.findElements(By.xpath(getAddButtonXpath(position))).isEmpty();
        log.debug("Кнопка Add to cart видна для позиции {}: {}", position, visible);
        return visible;
    }

    public boolean isRemoveButtonVisible(int position) {
        boolean visible = !driver.findElements(By.xpath(getRemoveButtonXpath(position))).isEmpty();
        log.debug("Кнопка Remove видна для позиции {}: {}", position, visible);
        return visible;
    }

    public String getTitle() {
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE)).getText();
        log.debug("Заголовок страницы: {}", title);
        return title;
    }

    public int getItemsCount() {
        int count = driver.findElements(INVENTORY_ITEMS).size();
        log.debug("Количество товаров на странице: {}", count);
        return count;
    }

    public String getCartBadgeCount() {
        if (driver.findElements(CART_BADGE).isEmpty()) {
            log.debug("Счетчик корзины: 0");
            return "0";
        }
        String count = driver.findElement(CART_BADGE).getText();
        log.debug("Счетчик корзины: {}", count);
        return count;
    }

    public boolean isProductAddedToCart(int position) {
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Remove']", position);
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    public void removeItem(int position) {
        log.info("Удаление товара (старый метод). Позиция: {}", position);
        String xpath = String.format("(//div[@class='inventory_item'])[%d]//button[text()='Remove']", position);
        driver.findElement(By.xpath(xpath)).click();
    }
}