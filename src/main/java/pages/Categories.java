package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Utils;

import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Categories extends BasePage {
    public Categories(WebDriver driver) {
        super(driver);
    }
    public void categoryFilter() {

        WebElement categoryLink = driver.findElement(By.xpath
                ("//*[@id=\"accordian\"]/div[1]/div[1]/h4/a/span/i"));
        categoryLink.click();
        WebElement allProducts = driver.findElement(By.cssSelector("div[class='features_items']"));
        List<WebElement> productElements = allProducts.findElements(By.cssSelector("div[class='col-sm-4']"));

        WebElement topsLink = driver.findElement(By.cssSelector
                ("div[id='Women']>div[class='panel-body'] >ul >li:nth-child(2) > a"));
        Utils.waitForSeconds(2);
        topsLink.sendKeys(Keys.ARROW_DOWN);
        topsLink.sendKeys(Keys.ENTER);

        WebElement filteredProducts = driver.findElement(By.cssSelector(".col-sm-9.padding-right"));
        List<WebElement> filteredProductList = filteredProducts.findElements(By.cssSelector(".product-image-wrapper"));

        Utils.waitForSeconds(3);
        Assert.assertTrue(filteredProductList.size() < productElements.size(),
                "Number of products are not decreased.");



    }
}
