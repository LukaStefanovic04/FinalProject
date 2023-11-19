package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPage extends BasePage{

    RegisterPage  registerPage;
    private By emailField = By.cssSelector("input[data-qa='login-email']");
    private By passwordField = By.cssSelector("input[data-qa='login-password']");
    private By loginButton = By.cssSelector("button[data-qa='login-button']");
    private By logOut = By.cssSelector("ul[class = 'nav navbar-nav'] > li:nth-child(4) > a");
    private By registerLink = By.cssSelector(".nav.navbar-nav > li:nth-child(4) > a");

    private By cart = By.xpath("//ul[@class='nav navbar-nav']//a[contains(text(),'Cart')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginPage(){
        clickOnElement(registerLink);
        return this;
    }
    public void loginWithCredentials(String username, String password) {
        driver.findElement(emailField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
    public boolean isLoginSuccessful() {
        try {
            return driver.findElement(cart).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void logout() {
        driver.findElement(logOut).click();
    }

    public boolean isLogoutSuccessful() {
        try {
            return driver.findElement(loginButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;  // Element za prijavljivanje nije pronađen, korisnik je uspešno odjavljen
        }
    }

}
