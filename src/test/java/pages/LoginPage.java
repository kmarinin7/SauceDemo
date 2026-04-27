package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.xpath("//*[@data-test='username']");
    private final By PASSWORD_FIELD = By.xpath("//*[@data-test='password']");
    private final By LOGIN_BUTTON = By.xpath("//*[@data-test='login-button']");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // открыть страницу
    public void open() {
        driver.get(BASE_URL);
    }

    // авторизация
    public void login(String name, String password) {
        driver.findElement(USERNAME_FIELD).sendKeys(name);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    // получить сообщение об ошибке
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}