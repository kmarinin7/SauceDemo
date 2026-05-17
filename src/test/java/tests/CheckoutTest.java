package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Epic("SauceDemo E2E")
@Feature("Оформление заказа")
public class CheckoutTest extends BaseTest {

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return new Object[][]{
                {"David", "Wilson", "789-012", "Checkout: Overview", null},
                {"", "Wilson", "789-012", null, "Error: First Name is required"},
                {"David", "", "789-012", null, "Error: Last Name is required"},
                {"David", "Wilson", "", null, "Error: Postal Code is required"}
        };
    }

    @Test(
            dataProvider = "checkoutData",
            groups = {"smoke", "regression"},
            description = "Параметризованный тест оформления заказа",
            testName = "Оформление заказа"
    )
    @Description("Проверка оформления заказа с валидными и невалидными данными")
    @Story("Заполнение формы заказа")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("kmarinin7")
    @TmsLink("TC-07")
    public void checkoutTest(String firstName, String lastName, String postalCode,
                             String successTitle, String errorMessage) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemBucket(1);
        productsPage.goToCart();
        cartPage.goCheckout();

        checkoutPage.makeOrder(firstName, lastName, postalCode);

        if (successTitle != null) {
            assertEquals(checkoutPage.getTitleCheckout(), successTitle);
        } else {
            assertEquals(checkoutPage.getErrorMessage(), errorMessage);
        }
    }
}