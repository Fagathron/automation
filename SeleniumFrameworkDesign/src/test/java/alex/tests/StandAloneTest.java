package alex.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.findElement(By.id("userEmail")).sendKeys("faga@testmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("P@ssw0rd1");
        driver.findElement(By.id("login")).click();

        String zaraCoat = "zara coat 3";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("col-sm-10"))));
        List<WebElement> products = driver.findElements(By.className("col-sm-10"));

        /**
         * Filter the products to find what we are interested in.
         * We already identified all the products and added to a list
         *
         * Below we are in scope of products elements so we only need to find the correct element in that scope not withing the entire html
         *
         * Here prd.findElement(By.cssSelector(".card-body button:last-child")).click(); we look again in the scope of the product we want to add
         * and search for the Add to cart button
         *
         * Within the scope of this element there are 2 button tags: View and Add to cart that's why we use last-child because the Add to cart
         * is the second button
         */

        WebElement prd = products.stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(zaraCoat))
                .findFirst().orElse(null);

        if (prd != null) {
            prd.findElement(By.cssSelector(".card-body button:last-child")).click();
        }

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ngx-spinner-overlay"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));

        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
        boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(zaraCoat));

        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Romania").build().perform();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("ta-results"))));
        driver.findElement(By.className("ta-item")).click();

        driver.findElement(By.className("action__submit")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();

    }
}
