package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutCompletePage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By COMPLETE_HEADER = By.xpath("//*[@data-test='complete-header']");
    private final By BACK_HOME_BUTTON = By.xpath("//*[@data-test='back-to-products']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        log.info("Создан объект CheckoutCompletePage");
    }

    @Override
    public CheckoutCompletePage isPageOpened() {
        log.debug("Проверка загрузки страницы завершения заказа");
        wait.until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_HEADER));
        log.info("Страница завершения заказа загружена");
        return this;
    }

    public CheckoutCompletePage open() {
        log.info("Открытие страницы завершения заказа: {}/checkout-complete.html", BASE_URL);
        driver.get(BASE_URL + "/checkout-complete.html");
        return this;
    }

    public ProductsPage clickBackHome() {
        log.info("Нажатие кнопки Back Home");
        wait.until(ExpectedConditions.elementToBeClickable(BACK_HOME_BUTTON)).click();
        return new ProductsPage(driver);
    }

    public String getCompleteHeader() {
        String header = driver.findElement(COMPLETE_HEADER).getText();
        log.info("Сообщение завершения: {}", header);
        return header;
    }
}
