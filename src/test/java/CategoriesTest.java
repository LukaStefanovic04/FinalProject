import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.Categories;
import pages.LoginPage;
import utils.Utils;

public class CategoriesTest extends BaseTest{
    LoginPage loginPage;
    Categories categories;

    CartPage cartPage;
    @BeforeMethod
    public void loginSetup() {
        loginPage = new LoginPage(driver);
        categories = new Categories(driver);
        cartPage = new CartPage(driver);
    }
    @Test
    public void categoriesTest(){
        loginPage.goToLoginPage().
                loginWithCredentials("test.user@example.com", "StrongPassword123!");
        cartPage.goToProductPage();
        categories.categoryFilter();
    }
}
