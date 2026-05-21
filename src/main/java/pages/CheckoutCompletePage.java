package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By COMPLETE_HEADER = By.xpath("//*[@data-test='complete-header']");
    private final By BACK_HOME_BUTTON = By.xpath("//*[@data-test='back-to-products']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Проверить загрузку страницы завершения заказа")
    public CheckoutCompletePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(TITLE, "Checkout: Complete!"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_HEADER));
        return this;
    }

    @Override
    @Step("Открыть страницу завершения заказа")
    public CheckoutCompletePage openPage() {
        driver.get(BASE_URL + "/checkout-complete.html");
        return isPageOpened();
    }

    @Step("Нажать кнопку 'Back Home'")
    public ProductsPage clickBackHome() {
        wait.until(ExpectedConditions.elementToBeClickable(BACK_HOME_BUTTON)).click();
        return new ProductsPage(driver);
    }

    @Step("Получить текст завершения")
    public String getCompleteHeader() {
        return driver.findElement(COMPLETE_HEADER).getText();
    }

    @Step("Получить заголовок страницы")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}
