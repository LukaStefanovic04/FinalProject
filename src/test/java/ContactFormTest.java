import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CotactForm;
import pages.LoginPage;
import utils.Utils;

public class ContactFormTest extends BaseTest {


    LoginPage loginPage;
    CotactForm contactForm;

    @BeforeMethod
    public void setupDrivers() {
        loginPage = new LoginPage(driver);
        contactForm = new CotactForm(driver);
    }

    @Test
    public void goToContact() {
        loginPage.goToLoginPage().
                loginWithCredentials("test.user@example.com", "StrongPassword123!");
        Utils.waitForSeconds(4);
        contactForm.goToContact();
        Utils.waitForSeconds(4);

        contactForm.FillForm();
        Utils.waitForSeconds(3);

        String successMessage = driver.findElement(By.cssSelector("div[class ='status alert alert-success']")).getText();
        Assert.assertTrue(successMessage.contains("Success! Your details have been submitted successfully."),
                "Form is not submited.");

    }

}
