package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutPage extends BasePage {

    private final By FIRST_NAME_FIELD = By.xpath("//*[@data-test='firstName']");
    private final By LAST_NAME_FIELD = By.xpath("//*[@data-test='lastName']");
    private final By ZIP_POSTAL_CODE = By.xpath("//*[@data-test='postalCode']");
    private final By CONTINUE_BUTTON = By.xpath("//*[@data-test='continue']");
    private final By CANCEL_BUTTON = By.xpath("//*[@data-test='cancel']");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");
    private final By TITLE = By.xpath("//*[@data-test='title']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        log.info("Создан объект CheckoutPage");
    }

    @Override
    public CheckoutPage isPageOpened() {
        log.debug("Проверка загрузки страницы оформления заказа");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_FIELD));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LAST_NAME_FIELD));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ZIP_POSTAL_CODE));
        log.info("Страница оформления заказа загружена");
        return this;
    }

    public CheckoutPage open() {
        log.info("Открытие страницы оформления заказа: {}/checkout-step-one.html", BASE_URL);
        driver.get(BASE_URL + "/checkout-step-one.html");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        log.info("Ввод имени: {}", firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_FIELD)).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        log.info("Ввод фамилии: {}", lastName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterPostalCode(String postalCode) {
        log.info("Ввод почтового индекса: {}", postalCode);
        driver.findElement(ZIP_POSTAL_CODE).sendKeys(postalCode);
        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        log.info("Нажатие кнопки Continue");
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver);
    }

    public CartPage backToCart() {
        log.info("Возврат в корзину (Cancel)");
        wait.until(ExpectedConditions.elementToBeClickable(CANCEL_BUTTON)).click();
        return new CartPage(driver);
    }

    public void makeOrder(String firstName, String lastName, String postalCode) {
        log.info("Оформление заказа: {} {}, {}", firstName, lastName, postalCode);
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(ZIP_POSTAL_CODE).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public String getTitleCheckout() {
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE)).getText();
        log.debug("Заголовок страницы оформления: {}", title);
        return title;
    }

    public String getErrorMessage() {
        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE)).getText();
        log.error("Сообщение об ошибке на странице оформления: {}", error);
        return error;
    }
}