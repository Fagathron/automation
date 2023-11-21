package alex.pageobjects;

import alex.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    @FindBy(css = ".totalRow button")
    WebElement checkOutElement;
    @FindBy(css = "div[class='cartSection'] h3")
    private List<WebElement> cartProducts;


    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyProductDisplay(String productName){
        boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage goToCheckOutPage()
    {
        checkOutElement.click();
        return new CheckOutPage(driver);
    }

}
