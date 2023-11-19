package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Utils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CartPage  extends  BasePage{
    private By cart = By.xpath("//ul[@class='nav navbar-nav']//a[contains(text(),'Cart')]");
    private By productPage = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a/i");

    private By product1 = By.cssSelector("a[href=\"/product_details/1\"]");

    private By product2 = By.cssSelector("a[href='/product_details/3']");
    private By quantity1 = By.xpath("//*[@id=\"quantity\"]");
    private By continueShopping = By.cssSelector(".btn.btn-success.close-modal.btn-block");

    private By quantityOfProducts = By.cssSelector("button[class = 'disabled']");

    private By placeOrderButton = By.cssSelector("a[href='/payment']");

    private By prices = By.cssSelector(".cart_total");

    private By totalAmount = By.cssSelector("#cart_info > table > tbody > tr:nth-child(3) > td:nth-child(4) > p");

    private By checkoutButton = By.cssSelector("div[class = 'col-sm-6'] > a");

    private By nameCardField = By.xpath("//*[@id=\"payment-form\"]/div[1]/div/input");

    private By cardNumberField = By.xpath("//*[@id=\"payment-form\"]/div[2]/div/input");

    private By CVC = By.xpath("//*[@id=\"payment-form\"]/div[3]/div[1]/input");

    private By expiration = By.xpath("//*[@id=\"payment-form\"]/div[3]/div[2]/input");

    private By yearofExpiration = By.cssSelector("#payment-form > div:nth-child(4) > div:nth-child(3) > input");

    private By payButton = By.xpath("//*[@id='submit']");


    public CartPage(WebDriver driver) {
        super(driver);
    }
    public void goToProductPage(){
        clickOnElement(productPage);
    }

    public void addProducts(){
        Utils.waitForSeconds(2);
        clickOnElement(product1);
        WebElement quantityInput = driver.findElement(quantity1);
        quantityInput.clear();
        quantityInput.sendKeys("2");
        Utils.waitForSeconds(2);
        By addToCartButtonLocator = By.cssSelector(".btn.btn-default.cart");
        WebElement addToCartButton = driver.findElement(addToCartButtonLocator);
        addToCartButton.click();
        clickOnElement(continueShopping);
        goToProductPage();
        clickOnElement(product2);
        WebElement quantityInput2 = driver.findElement(quantity1);
        quantityInput2.clear();
        quantityInput2.sendKeys("3");
        Utils.waitForSeconds(2);
        By addToCartButtonLocator2 = By.cssSelector(".btn.btn-default.cart");
        WebElement addToCartButton2 = driver.findElement(addToCartButtonLocator2);
        addToCartButton2.click();
        clickOnElement(continueShopping);
    }
    public void checkPrices(){
        List<WebElement> pricesElements = driver.findElements(prices);
        List<Double> productPrices = pricesElements.stream()
                .map(element -> {
                    String priceText = element.getText().replace("Rs. ", "");
                    System.out.println("Product Price: " + priceText);
                    return Double.parseDouble(priceText);
                })
                .collect(Collectors.toList());


        WebElement totalAmountElement = driver.findElement(totalAmount);
        double totalAmountValue = Double.parseDouble(totalAmountElement.getText().replace("Rs. ", ""));
        System.out.println("Total Amount: " + totalAmountValue);

        double totalPrice = productPrices.stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("Total Price: " + totalPrice);

        Assert.assertEquals(totalPrice, totalAmountValue, "Prices are not equal.");
    }
    public void buyProducts(){
        clickOnElement(placeOrderButton);
        Utils.waitForSeconds(2);
        fillPaymentFormWithRandomData();
        clickOnElement(payButton);
    }
    public void fillPaymentFormWithRandomData() {
        String cardHolderName = generateRandomName();
        String cardNumber = generateRandomNumber(16);
        String cvc = generateRandomNumber(3);
        String expirationDate = generateRandomNumber(2) + generateRandomNumber(2);
        String expirationYear = generateRandomNumber(4);


        typeIn(nameCardField, cardHolderName);
        typeIn(cardNumberField, cardNumber);
        typeIn(CVC, cvc);
        typeIn(expiration, expirationDate);
        typeIn(yearofExpiration, expirationYear);
    }
    private String generateRandomName() {
        String[] names = {"John", "Jane", "David", "Emma", "Michael", "Olivia"};
        return names[new Random().nextInt(names.length)];
    }

    private String generateRandomNumber(int length) {
        StringBuilder randomData = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            randomData.append(digit);
        }

        return randomData.toString();
    }

    public void goToCartpage(){
        clickOnElement(cart);
    }
    public void proceedToCheckout(){
        clickOnElement(checkoutButton);
    }
}
