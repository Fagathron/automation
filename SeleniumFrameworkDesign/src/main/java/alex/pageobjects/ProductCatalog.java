package alex.pageobjects;

import alex.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Hello world!
 *
 */
public class ProductCatalog extends AbstractComponent {
    private WebDriver driver;
    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className="col-sm-10")
    List<WebElement> products;
    @FindBy(className="ngx-spinner-overlay")
    WebElement spinnerOverlay;

    @FindBy(className="col-sm-10")
    WebElement productBy;
    @FindBy(id="toast-container")
    WebElement toastContainer;

    By addToCart = By.cssSelector(".card-body button:last-child");

    public List<WebElement> getProductsList(){
        waitForElementToAppear(productBy);
        return products;
    }

    public  WebElement getProductByName(String productName){

        return getProductsList().stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
    }

    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();

        waitForElementToDisappear(spinnerOverlay);
        //waitForElementToAppear(toastContainer);
    }

}

