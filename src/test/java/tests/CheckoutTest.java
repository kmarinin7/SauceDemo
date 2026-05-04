package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return new Object[][]{
                // TC-07: Успешное оформление заказа
                {"David", "Wilson", "789-012", "Checkout: Overview", null},

                // TC-08: Пустое поле First Name
                {"", "Wilson", "789-012", null, "Error: First Name is required"},

                // TC-09: Пустое поле Last Name
                {"David", "", "789-012", null, "Error: Last Name is required"},

                // TC-10: Пустое поле Postal Code
                {"David", "Wilson", "", null, "Error: Postal Code is required"}
        };
    }

    @Test(
            dataProvider = "checkoutData",
            groups = {"smoke", "regression"},
            description = "Параметризованный тест оформления заказа",
            testName = "Оформление заказа"
    )
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