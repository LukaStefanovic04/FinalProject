import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Utils;

public class LogoutTest extends BaseTest{
    LoginPage loginPage;
    @BeforeMethod
    public void logoutSetup() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogout() {

        loginPage.goToLoginPage();
        loginPage.loginWithCredentials("test.user@example.com", "StrongPassword123!");

        Utils.waitForSeconds(5);
        loginPage.logout();
        Assert.assertTrue(loginPage.isLogoutSuccessful(),"Logout is not successful!");

    }
}
