import dataproviders.DataProviders;
import model.LoginUserModel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Utils;

import java.util.List;

public class LoginTest extends BaseTest {

    LoginPage login;
    RegisterPage registerPage;
    @BeforeMethod
    public void loginSetup() {
        login = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void login() {
        String registeredUsername = "test.user@example.com";
        String registeredPassword = "StrongPassword123!";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();


        loginPage.loginWithCredentials(registeredUsername, registeredPassword);
        Assert.assertTrue(loginPage.isLoginSuccessful(),"User is not logged in!");
    }

//    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviders.class)
//    public void invalidLoginTest(String username, String password) {
//        login.goToLoginPage()
//                .loginWithCredentials(username, password);
//        Assert.assertTrue(login.isLoginSuccessful(),"Login is not successful!");
//
//    } //data provider
//
//    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviders.class)
//    public void invalidLoginTestFromJson() {
//        List<LoginUserModel> list = Utils.getDataFromJson();
//        for (int i = 0; i < list.size(); i++) {
//            login.goToLoginPage()
//                    .loginWithCredentials(list.get(i).getUsername(), list.get(i).getPassword());
//        }
//        Assert.assertTrue(login.isLoginSuccessful(),"User is not logged in!");
//
//    } //json


}
