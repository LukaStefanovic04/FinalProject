package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RegisterPage extends  BasePage {
    private String username;
    private String email;
    private String password;

    private By registerLink = By.cssSelector(".nav.navbar-nav > li:nth-child(4) > a");
    private By registerButton = By.cssSelector("form[action = '/signup']>:nth-child(5)");
    private By nameField = By.cssSelector("form[action = '/signup']>:nth-child(2)");
    private By emailField = By.cssSelector("form[action = '/signup']>:nth-child(3)");
    private By firstNameField = By.cssSelector("input[data-qa = 'first_name']");
    private By lastNameField = By.cssSelector("input[data-qa = 'last_name']");
    private By addressField = By.cssSelector("input[data-qa='address']");

    private By addressField2 = By.cssSelector("input[data-qa='address2']");
    private By postCodeField = By.cssSelector("input[data-qa='zipcode']");
    private By companyField = By.cssSelector("input[id = 'company']");
    private By cityField = By.cssSelector("input[data-qa='city']");
    private By stateField = By.cssSelector("input[data-qa='state']");
    private By countryDropdown = By.cssSelector("select[data-qa='country']");
    private By phoneNumberField = By.cssSelector("input[data-qa='mobile_number']");
    private By passwordField = By.cssSelector("input[type='password']");

    private By cretaAccountButton = By.cssSelector("button[data-qa = 'create-account']");
    private By genderPick = By.cssSelector("label[for='id_gender1']");

    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    private By accountText = By.cssSelector("div[class='col-sm-9 col-sm-offset-1'] >h2 > b");



    public RegisterPage goToRegisterPage() {
        clickOnElement(registerLink);
        return this;
    }


   public boolean isUserRegistered(){
       return matchesExpectedText(accountText, "ACCOUNT CREATED!");
   }
    public RegisterPage(WebDriver driver) {
        super(driver);
    }



    public String getEmail() {
        return email;
    }

    public RegisterPage registerUser() {
        username = faker.internet().domainName();
        email = faker.internet().emailAddress();
        typeIn(nameField, username);
        typeIn(emailField, email);
        clickOnElement(registerButton);
        clickOnElement(genderPick);
        password =faker.internet().password();
        typeIn(passwordField, password );
        selectRandomDate();
        typeIn(firstNameField, faker.name().firstName());
        typeIn(lastNameField, faker.name().lastName());
        typeIn(companyField, faker.name().toString());
        typeIn(addressField, faker.address().fullAddress());
        typeIn(addressField2, faker.address().fullAddress());
        typeIn(postCodeField, faker.address().zipCode());
        typeIn(cityField, faker.address().city());
        typeIn(stateField, faker.address().state());
        selectCountry();
        typeIn(phoneNumberField, faker.number().digits(8));
        Utils.waitForSeconds(2);
        clickOnElement(cretaAccountButton);
//        username = "test_user";
//        email = "test.user@example.com";
//        typeIn(nameField, username);
//        typeIn(emailField, email);
//        clickOnElement(registerButton);
//        clickOnElement(genderPick);
          //password = "StrongPassword123!";
//        typeIn(passwordField, password);
//        selectRandomDate();
//        typeIn(firstNameField, "Test");
//        typeIn(lastNameField, "User");
//        typeIn(companyField, "TestCorp");
//        typeIn(addressField, "456 Oak Street");
//        typeIn(addressField2, "Suite 789");
//        typeIn(postCodeField, "67890");
//        typeIn(cityField, "Testburg");
//        typeIn(stateField, "Teststate");
//        selectCountry();
//        typeIn(phoneNumberField, "5551234567");
//        clickOnElement(cretaAccountButton);
//        Utils.waitForSeconds(5);
//        clickOnElement(continueButton);
        return this;
    }


    private LocalDate generateRandomDate() {

        LocalDate currentDate = LocalDate.now();
        int randomYear = ThreadLocalRandom.current().nextInt(currentDate.minusYears(10).getYear(), currentDate.getYear() + 1);
        int randomMonth = ThreadLocalRandom.current().nextInt(1, 13);
        int randomDay = ThreadLocalRandom.current().nextInt(1, LocalDate.of(randomYear, randomMonth, 1).plusMonths(1).minusDays(1).getDayOfMonth() + 1);

        return LocalDate.of(randomYear, randomMonth, randomDay);
    }

    private void selectCountry() {
        WebElement countryDropdownElement = getElement(countryDropdown);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].value='United States';", countryDropdownElement);
    }

    private void selectRandomDate() {
        By dayDropdown = By.cssSelector("select[data-qa ='days']");
        By monthDropdown = By.cssSelector("select[data-qa ='months']");
        By yearDropdown = By.cssSelector("#years");
        WebElement dayDropdownElement = getElement(dayDropdown);
        WebElement monthDropdownElement = getElement(monthDropdown);
        WebElement yearDropdownElement = getElement(yearDropdown);


        LocalDate randomDate = generateRandomDate();

        setDropdownValue(dayDropdownElement, String.valueOf(randomDate.getDayOfMonth()));
        setDropdownValue(monthDropdownElement, String.valueOf(randomDate.getMonthValue()));
        setDropdownValue(yearDropdownElement, String.valueOf(randomDate.getYear()));
    }

    private void setDropdownValue(WebElement dropdownElement, String value) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].value='" + value + "';", dropdownElement);
    }


}
