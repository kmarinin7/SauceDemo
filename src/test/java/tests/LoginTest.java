package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                // TC-01: Успешная авторизация
                {"standard_user", "secret_sauce", "Products", null, true},

                // TC-02: Пустой пароль
                {"standard_user", "", null, "Epic sadface: Password is required", false},

                // TC-03: Пустой логин
                {"", "secret_sauce", null, "Epic sadface: Username is required", false},

                // TC-04: Невалидные данные
                {"name", "pass", null, "Epic sadface: Username and password do not match any user in this service",
                        false}

        };
    }

    @Test(
            dataProvider = "loginData",
            groups = {"smoke", "regression"},
            description = "Параметризованный тест авторизации",
            testName = "Авторизация в системе SauceDemo"
    )
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