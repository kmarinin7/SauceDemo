package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.xpath("//*[@data-test='username']");
    private final By PASSWORD_FIELD = By.xpath("//*[@data-test='password']");
    private final By LOGIN_BUTTON = By.xpath("//*[@data-test='login-button']");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");
    private final By LOGO = By.xpath("//div[@class='login_logo']");

    public LoginPage(WebDriver driver) {
        super(driver);
        log.info("Создан объект LoginPage");
    }

    @Override
    public LoginPage isPageOpened() {
        log.debug("Проверка загрузки страницы логина");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGO));
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
        log.info("Страница логина загружена");
        return this;
    }

    public LoginPage open() {
        log.info("Открытие страницы логина: {}", BASE_URL);
        driver.get(BASE_URL);
        return this;
    }

    public LoginPage enterUsername(String username) {
        log.info("Ввод логина: {}", username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD)).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        log.info("Ввод пароля");
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    public ProductsPage clickLogin() {
        log.info("Нажатие кнопки логина");
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }
    public LoginPage clickLoginExpectingError() {
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }
    public LoginPage loginInvalid(String name, String password) {
        return enterUsername(name)
                .enterPassword(password)
                .clickLoginExpectingError();
    }  

    public ProductsPage login(String name, String password) {
        log.info("Авторизация пользователя: {}", name);
        return enterUsername(name).enterPassword(password).clickLogin();
    }

    public String getErrorMessage() {
        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE)).getText();
        log.error("Получено сообщение об ошибке: {}", error);
        return error;
    }
}