
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CotactForm;
import pages.LoginPage;
import pages.RegisterPage;

import static org.testng.Assert.assertEquals;

public class RegisterTest extends BaseTest {
    RegisterPage registerPage;
    LoginPage loginPage;
    @BeforeMethod
    public void setupRegister() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }
    @Test()
    public void registration(){
        registerPage.goToRegisterPage()
                .registerUser();
        Assert.assertTrue(registerPage.isUserRegistered(),"User is not registered and logged in!");
    }


}

