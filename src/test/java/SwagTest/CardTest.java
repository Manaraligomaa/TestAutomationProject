package SwagTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CardTest {
    static WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");

        WebElement userName = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement login = driver.findElement(By.id("login-button"));
        userName.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        login.click();
    }

    @Test
    public void testAddItemToCart() {
        addItemToCart("Example Item");
    }

    public void addItemToCart(String itemName) {
        WebElement item = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='" + itemName + "']/parent::a/parent::div/following-sibling::div/button[@class='btn btn_primary btn_small btn_inventory']"));
        item.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // re-assert the interrupted status
        }

        WebElement cart = driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a"));
        cart.click();

        WebElement cartItem = driver.findElement(By.xpath("//td[contains(text(), '" + itemName + "')]"));
        if (cartItem != null) {
            System.out.println("Item '" + itemName + "' exists in the cart.");
        } else {
            System.out.println("Item '" + itemName + "' does not exist in the cart.");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

