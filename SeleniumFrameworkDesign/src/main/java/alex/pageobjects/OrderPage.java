package alex.pageobjects;

import alex.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    @FindBy(css = ".totalRow button")
    WebElement checkOutElement;
    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;


    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyOrderDisplay(String productName){
        boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }


}
