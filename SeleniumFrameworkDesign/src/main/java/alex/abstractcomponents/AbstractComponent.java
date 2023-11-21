package alex.abstractcomponents;

import alex.pageobjects.CartPage;
import alex.pageobjects.OrderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css="button[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css="button[routerlink*='myorders']")
    WebElement orderHeader;

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver,this);
    }

    public CartPage goToCartPage() {
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrdersPage() {
        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
   public void waitForElementToAppear(WebElement element){
       wait.until(ExpectedConditions.visibilityOfAllElements(element));
   }

   public void waitForElementToDisappear(WebElement element){
       wait.until(ExpectedConditions.invisibilityOf(element));
   }
}
