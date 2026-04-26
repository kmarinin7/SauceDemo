import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;

public class LocatorsTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void checkLocator() {
        driver.get("https://www.saucedemo.com/");

        // id
        driver.findElement(By.id("login_credentials"));

        // name
        driver.findElement(By.name("user-name"));

        // classname
        driver.findElement(By.className("form_group"));

        // tagname
        driver.findElement(By.tagName("h4"));

        // логин
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();

        // linktext
        driver.findElement(By.linkText("Sauce Labs Fleece Jacket"));

        // partiallinktext
        driver.findElement(By.partialLinkText("Backpack"));

        // xpath - поиск по атрибуту
        driver.findElement(By.xpath("//div[@class='app_logo']"));

        // xpath - поиск по тексту
        driver.findElement(By.xpath("//span[text()='Products']"));

        // xpath - частичное совпадение атрибута
        driver.findElements(By.xpath("//title[text()='Swag Labs']"));

        // xpath - частичное совпадение текста
        driver.findElement(By.xpath("//*[contains(text(), 'Labs')]"));

        // xpath - ancestor
        driver.findElement(By.xpath("//span[text()='Products']/ancestor::div[@id='header_container']"));

        // xpath - descendant
        driver.findElement(By.xpath("//*[@class='inventory_item_label']//descendant::div[@class='inventory_item_desc']"));

        // xpath - following
        driver.findElements(By.xpath("//div/following::button"));

        // xpath - parent
        driver.findElements(By.xpath("//button/parent::div"));

        // xpath - preceding
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/preceding::div[@class='bm-burger-button']"));

        // xpath - AND
        driver.findElements(By.xpath("//input[contains(@class,'input_error') and contains(@class,'form_input')]"));

        // css - .class
        driver.findElement(By.cssSelector(".shopping_cart_link"));

        // css - .class1.class2
        driver.findElement(By.cssSelector(".btn_primary.btn_inventory"));

        // css - .class1 .class2
        driver.findElement(By.cssSelector(".inventory_item .inventory_item_img"));

        // css - #id
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-fleece-jacket"));

        // css - tagname
        driver.findElement(By.cssSelector("span"));

        // css - tagname.class
        driver.findElement(By.cssSelector("ul.social"));

        // css - [attribute=value]
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']"));

        // css - [attribute~=value]
        driver.findElement(By.cssSelector("[class~='btn']"));

        // css - [attribute|=value]
        driver.findElement(By.cssSelector("[name|=add]"));

        // css - [attribute^=value]
        driver.findElement(By.cssSelector("[id^='item_0_title_']"));

        // css - [attribute$=value]
        driver.findElement(By.cssSelector("[data-test$='(red)']"));

        // css - [attribute*=value]
        driver.findElement(By.cssSelector("[id*='0_title']"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
