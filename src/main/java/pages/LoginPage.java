package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.xpath("//*[@data-test='username']");
    private final By PASSWORD_FIELD = By.xpath("//*[@data-test='password']");
    private final By LOGIN_BUTTON = By.xpath("//*[@data-test='login-button']");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");
    private final By LOGO = By.xpath("//div[@class='login_logo']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Проверить загрузку страницы логина")
    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGO));
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
        return this;
    }

    @Override
    @Step("Открыть страницу авторизации")
    public LoginPage openPage() {
        driver.get(BASE_URL);
        return isPageOpened();
    }

    @Step("Ввести логин: '{username}'")
    public LoginPage enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD))
                .sendKeys(username);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage enterPassword(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку логина")
    public ProductsPage clickLogin() {
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Нажать кнопку логина (ожидая ошибку)")
    public LoginPage clickLoginExpectingError() {
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    @Step("Открыть страницу авторизации")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Авторизация с логином '{name}' и паролем '{password}'")
    public ProductsPage login(String name, String password) {
        return enterUsername(name)
                .enterPassword(password)
                .clickLogin();
    }

    @Step("Попытка авторизации с невалидными данными")
    public LoginPage loginInvalid(String name, String password) {
        return enterUsername(name)
                .enterPassword(password)
                .clickLoginExpectingError();
    }

    @Step("Получить сообщение об ошибке авторизации")
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE)).getText();
    }
}