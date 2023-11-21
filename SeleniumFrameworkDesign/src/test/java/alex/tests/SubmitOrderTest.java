package alex.tests;

import alex.pageobjects.*;
import alex.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

        public String zaraCoat = "zara coat 3";

        @Test(dataProvider = "getData", groups ={"purchase"})
        public void submitOrderTest(HashMap<String, String> data) throws Exception {
            ProductCatalog productCatalog = landingPage.loginApplication(data.get("email"), data.get("password"));


            List<WebElement> products = productCatalog.getProductsList();

            productCatalog.addProductToCart(data.get("product"));
            CartPage cartPage = productCatalog.goToCartPage();


            boolean match = cartPage.verifyProductDisplay(data.get("product"));
            Assert.assertTrue(match);
            CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
            checkOutPage.selectCountry("Romania");

            ConfirmationPage confirmationPage = checkOutPage.submitOrder();
            String confirmMessage = confirmationPage.getConfirmationMessage();

            Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrderTest"})
    public void orderHistoryTest() throws InterruptedException {
        ProductCatalog productCatalog = landingPage.loginApplication("faga@testmail.com", "P@ssw0rd1");
        OrderPage ordersPage = productCatalog.goToOrdersPage();

        Assert.assertTrue(ordersPage.verifyOrderDisplay(zaraCoat));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("email","faga@testmail.com");
//        map.put("password","P@ssw0rd1");
//        map.put("product", "ZARA COAT 3");
//
//        HashMap<String, String> map1 = new HashMap<>();
//        map1.put("email","fagaadmin@testmail.com");
//        map1.put("password","P@ssw0rd1");
//        map1.put("product", "ADIDAS ORIGINAL");

        List<HashMap<String, String>> data = getJsonDataMap(System.getProperty("user.dir") + "/src/test/java/alex/data/PurchaseOrder.json");
        return new Object[][] {
                {data.get(0)},
                {data.get(1)}
        };
//        return new Object[][] {
//                {map},
//                {"fagaadmin@testmail.com","P@ssw0rd1", "ADIDAS ORIGINAL"}
//        };
    }
}
