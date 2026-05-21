package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private final By FIRST_NAME_FIELD = By.xpath("//*[@data-test='firstName']");
    private final By LAST_NAME_FIELD = By.xpath("//*[@data-test='lastName']");
    private final By ZIP_POSTAL_CODE = By.xpath("//*[@data-test='postalCode']");
    private final By ORDER_BUTTON = By.xpath("//*[@data-test='continue']");
    private final By CANCEL_BUTTON = By.xpath("//*[@data-test='cancel']");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");
    private final By TITLE = By.xpath("//*[@data-test='title']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Проверить загрузку страницы оформления заказа")
    public CheckoutPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_FIELD));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LAST_NAME_FIELD));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ZIP_POSTAL_CODE));
        return this;
    }

    @Override
    @Step("Открыть страницу оформления заказа")
    public CheckoutPage openPage() {
        driver.get(BASE_URL + "/checkout-step-one.html");
        return isPageOpened();
    }

    @Step("Ввести имя: '{firstName}'")
    public CheckoutPage enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_FIELD))
                .sendKeys(firstName);
        return this;
    }

    @Step("Ввести фамилию: '{lastName}'")
    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        return this;
    }

    @Step("Ввести почтовый индекс: '{postalCode}'")
    public CheckoutPage enterPostalCode(String postalCode) {
        driver.findElement(ZIP_POSTAL_CODE).sendKeys(postalCode);
        return this;
    }

    @Step("Нажать кнопку 'Continue'")
    public CheckoutOverviewPage clickContinue() {
        driver.findElement(ORDER_BUTTON).click();
        return new CheckoutOverviewPage(driver);
    }

    @Step("Нажать кнопку 'Continue' (ожидая ошибку)")
    public CheckoutPage clickContinueExpectingError() {
        driver.findElement(ORDER_BUTTON).click();
        return this;
    }

    @Step("Вернуться в корзину (кнопка 'Cancel')")
    public CartPage backToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(CANCEL_BUTTON)).click();
        return new CartPage(driver);
    }

    @Step("Оформить заказ с валидными данными")
    public CheckoutOverviewPage makeOrderValid(String firstName, String lastName, String postalCode) {
        return enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode)
                .clickContinue();
    }

    @Step("Оформить заказ с невалидными данными")
    public CheckoutPage makeOrderInvalid(String firstName, String lastName, String postalCode) {
        return enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode)
                .clickContinueExpectingError();
    }

    @Step("Оформить заказ (общий метод для обратной совместимости)")
    public void makeOrder(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(ZIP_POSTAL_CODE).sendKeys(postalCode);
        driver.findElement(ORDER_BUTTON).click();
    }

    @Step("Получить заголовок страницы оформления заказа")
    public String getTitleCheckout() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE)).getText();
    }

    @Step("Получить сообщение об ошибке на странице оформления")
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE)).getText();
    }
}