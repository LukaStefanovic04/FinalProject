import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import utils.Utils;

public class CartTest extends BaseTest {
    LoginPage loginPage;
    CartPage cartPage;

    @BeforeMethod
    public void setupDrivers() {
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
    }
    @Test
    public void cartTest() {
        loginPage.goToLoginPage().
                loginWithCredentials("test.user@example.com", "StrongPassword123!");
        Utils.waitForSeconds(4);
        cartPage.goToProductPage();
        Utils.waitForSeconds(2);
        cartPage.addProducts();
        Utils.waitForSeconds(2);
        cartPage.goToCartpage();
        Utils.waitForSeconds(2);
        cartPage.proceedToCheckout();
        cartPage.checkPrices();
        Utils.waitForSeconds(2);
        cartPage.buyProducts();

        String successMessage = driver.findElement(By.cssSelector("#form > div > div > div > p")).getText();
        Assert.assertTrue(successMessage.contains("Congratulations! Your order has been confirmed!"),
                "FAIL.");


    }
}







