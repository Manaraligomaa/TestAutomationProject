package testPackage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestClass {
    @Test
    public void testMethod(){
        WebDriverManager.chromedriver().setup();

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to a URL
        driver.navigate().to("https://www.google.com/");
        By googleLogo_image = By.xpath("//img[@alt='Google']\n");
        boolean isDisplayed= driver.findElement(googleLogo_image).isDisplayed();
        Assert.assertTrue(isDisplayed);

        // Close the driver after execution
        driver.quit();
    }
}





