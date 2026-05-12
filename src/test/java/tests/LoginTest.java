package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Epic("SauceDemo E2E")
@Feature("Авторизация")
public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "Products", null, true},
                {"standard_user", "", null, "Epic sadface: Password is required", false},
                {"", "secret_sauce", null, "Epic sadface: Username is required", false},
                {"name", "pass", null, "Epic sadface: Username and password do not match any user in this service", false}
        };
    }

    @Test(
            dataProvider = "loginData",
            groups = {"smoke", "regression"},
            description = "Параметризованный тест авторизации",
            testName = "Авторизация в системе SauceDemo"
    )
    @Description("Проверка авторизации с различными комбинациями логина и пароля")
    @Story("Позитивная и негативная авторизация")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("kmarinin7")
    @TmsLink("TC-01")
    @Link(name = "SauceDemo", url = "https://saucedemo.com")
    public void checkLogin(String username, String password, String successTitle,
                           String errorMessage, boolean isPositive) {
        loginPage.open();
        loginPage.login(username, password);

        if (isPositive) {
            assertEquals(productsPage.getTitle(), successTitle);
        } else {
            assertEquals(loginPage.getErrorMessage(), errorMessage);
        }
    }
}