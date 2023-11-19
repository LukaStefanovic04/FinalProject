package pages;

import com.github.javafaker.Faker;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import utils.Utils;

import java.security.Key;

public class CotactForm extends BasePage {

    By contactField = By.cssSelector("ul[class=\"nav navbar-nav\"] > li:nth-child(9)>a");
    By nameField = By.xpath("//*[@id=\"contact-us-form\"]/div[1]/input");
    By emailField = By.xpath("//*[@id=\"contact-us-form\"]/div[2]/input");
    By subjectField = By.xpath("//*[@id=\"contact-us-form\"]/div[3]/input");
    By messageField = By.xpath("//*[@id=\"message\"]");
    By chooseFile = By.cssSelector("input[name='upload_file']");
    By submitButton = By.cssSelector("div[id='form-section'] > form > div:nth-child(7) > input");
    public CotactForm(WebDriver driver) {
        super(driver);
    }

    public void goToContact(){
        clickOnElement(contactField);
    }

    public void FillForm(){
    typeIn(nameField,"John");
    typeIn(emailField,"test.user@example.com");
    typeIn(subjectField,"This is Subject field.");
    typeIn(messageField,"Here's Johny.");
    typeIn(chooseFile,"C:\\Users\\luka\\Desktop\\workspace\\selenium\\SeleniumFinalProject\\file.txt");
    Utils.waitForSeconds(5);
    clickOnElement(submitButton);
    Utils.waitForSeconds(5);

    }
}
