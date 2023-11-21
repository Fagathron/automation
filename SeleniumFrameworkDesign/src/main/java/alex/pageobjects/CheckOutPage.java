package alex.pageobjects;

import alex.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponent {
    private final WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "[placeholder='Select Country']")
    WebElement selectCountryBox;
    @FindBy(className = "ta-item")
    WebElement selectCountry;

     @FindBy(className = "action__submit")
    WebElement submit;

     @FindBy(className = "ta-results")
     WebElement countryResults;


    public void selectCountry(String countryName){

        Actions action = new Actions(driver);
        action.sendKeys(selectCountryBox, countryName).build().perform();

        waitForElementToAppear(countryResults);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }
}
