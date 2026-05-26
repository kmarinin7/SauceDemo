package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Log4j2
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    public final String BASE_URL = "https://saucedemo.com/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.debug("Инициализация страницы: {}", this.getClass().getSimpleName());
    }

    public abstract BasePage isPageOpened();
}