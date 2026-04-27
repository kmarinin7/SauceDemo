package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    // TC-01: Успешная авторизация с валидными данными
    @Test
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    // TC-02: Авторизация с пустым паролем
    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    // TC-03: Авторизация с пустым логином
    @Test
    public void checkLoginWithEmptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    // TC-04: Авторизация с невалидными данными
    @Test
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("name", "pass");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }
}