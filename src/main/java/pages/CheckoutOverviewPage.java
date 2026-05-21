package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutOverviewPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By FINISH_BUTTON = By.xpath("//*[@data-test='finish']");
    private final By CANCEL_BUTTON = By.xpath("//*[@data-test='cancel']");
    private final By TOTAL_LABEL = By.xpath("//*[@data-test='total-label']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        log.info("Создан объект CheckoutOverviewPage");
    }

    @Override
    public CheckoutOverviewPage isPageOpened() {
        log.debug("Проверка загрузки страницы Overview");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(TITLE, "Checkout: Overview"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TOTAL_LABEL));
        log.info("Страница Overview загружена");
        return this;
    }

    public CheckoutOverviewPage open() {
        log.info("Открытие страницы Overview: {}/checkout-step-two.html", BASE_URL);
        driver.get(BASE_URL + "/checkout-step-two.html");
        return this;
    }

    public CheckoutCompletePage clickFinish() {
        log.info("Нажатие кнопки Finish");
        wait.until(ExpectedConditions.elementToBeClickable(FINISH_BUTTON)).click();
        return new CheckoutCompletePage(driver);
    }

    public String getTitle() {
        String title = driver.findElement(TITLE).getText();
        log.debug("Заголовок страницы Overview: {}", title);
        return title;
    }
}