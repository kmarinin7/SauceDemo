package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOverviewPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By FINISH_BUTTON = By.xpath("//*[@data-test='finish']");
    private final By CANCEL_BUTTON = By.xpath("//*[@data-test='cancel']");
    private final By TOTAL_LABEL = By.xpath("//*[@data-test='total-label']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Проверить загрузку страницы Overview")
    public CheckoutOverviewPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(TITLE, "Checkout: Overview"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TOTAL_LABEL));
        return this;
    }

    @Override
    @Step("Открыть страницу Overview")
    public CheckoutOverviewPage openPage() {
        driver.get(BASE_URL + "/checkout-step-two.html");
        return isPageOpened();
    }

    @Step("Нажать кнопку 'Finish'")
    public CheckoutCompletePage clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(FINISH_BUTTON)).click();
        return new CheckoutCompletePage(driver);
    }

    @Step("Нажать кнопку 'Cancel'")
    public ProductsPage clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(CANCEL_BUTTON)).click();
        return new ProductsPage(driver);
    }

    @Step("Получить заголовок страницы")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}
