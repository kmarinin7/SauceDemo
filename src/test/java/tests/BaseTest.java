package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.TestListener;

import java.util.HashMap;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, ITestContext iTestContext) {
        log.info("========== ЗАПУСК ТЕСТА: {} ==========", iTestContext.getName());
        log.info("Браузер: {}", browser);

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            log.debug("ChromeDriver инициализирован");
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            log.debug("EdgeDriver инициализирован");
        }

        iTestContext.setAttribute("driver", driver);

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

        log.debug("Все Page Object инициализированы");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("========== ЗАВЕРШЕНИЕ ТЕСТА ==========");
        if (driver != null) {
            log.debug("Закрытие браузера");
            driver.quit();
        }
    }
}