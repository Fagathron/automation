package alex.tests;

import alex.pageobjects.CartPage;
import alex.pageobjects.ProductCatalog;
import alex.testcomponents.BaseTest;
import alex.testcomponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidationsTest extends BaseTest {
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginValidationTest(){
        landingPage.loginApplication("faga@testmail.com", "P@ss0rd1");
        Assert.assertEquals("Incorrect email or password.",landingPage.getValidationMessage());
    }

    @Test
    public void productValidationTest(){

        String zaraCoat = "zara coat 3";
        ProductCatalog productCatalog = landingPage.loginApplication("faga@testmail.com", "P@ssw0rd1");


        List<WebElement> products = productCatalog.getProductsList();

        productCatalog.addProductToCart(zaraCoat);
        CartPage cartPage = productCatalog.goToCartPage();


        boolean match = cartPage.verifyProductDisplay("random prod");
        Assert.assertFalse(match);
    }
}
