package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import utilities.Utility;

public class GearTest extends Utility {


    //Base url
    String baseUrl = "https://magento.softwaretestingboard.com/";

    //Set up the browser
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    //user Should Add Product SuccessFully To ShoppingCart
    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() {

        //Mouse Hover on the ‘Gear’ Menu
        WebElement gearElement = findWebElement(By.xpath("//span[normalize-space()='Gear']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearElement).build().perform();

        //Click on the ‘Bags’
        clickOnElement(By.id("ui-id-25"));

        //Click on Product Name ‘Overnight Duffle’
        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));

        //Verify the text ‘Overnight Duffle’
        String expectProductName = "Overnight Duffle";
        String actualProductName = getTextFromElem(By.className("base"));
        Assert.assertEquals("the text ‘Overnight Duffle’ is not displayed", expectProductName, actualProductName);

        //Change the Qty 3
        sendTextOnElement(By.id("qty"), "3");

        //Click on the ‘Add to Cart’ Button.
        clickOnElement(By.id("product-addtocart-button"));

        //Verify the text ‘You added Overnight Duffle to your shopping cart.’
        String actualShoppingCartMessage = getTextFromElem(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
        String expectedShoppingCartMessage = "You added Overnight Duffle to your shopping cart.";
        Assert.assertEquals("the text You added Overnight Duffle to your shopping car is not displayed", expectedShoppingCartMessage, actualShoppingCartMessage);

        //Click on the ‘shopping cart’ Link into message
        clickOnElement(By.linkText("shopping cart"));

        //Verify the product name ‘Overnight Duffle’
        String actualAddedProductName = getTextFromElem(By.linkText("Overnight Duffle"));
        String expectedAddedProductName = "Overnight Duffle";
        Assert.assertEquals("the product name ‘Overnight Duffle is not displayed", expectedAddedProductName, actualAddedProductName);

        //Verify the Qty is ‘3’
        String expectedQtyValue = "3";
        String actualQtyValue = driver.findElement(RelativeLocator.with(By.tagName("input")).toRightOf(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$45.00']"))).getText();
        System.out.println(actualQtyValue);
        //Assert.assertEquals("The Qty is not 3.", expectedQtyValue, actualQtyValue);

        //Verify the product price ‘$135.00’
        String actualPrice = getTextFromElem(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$135.00']"));
        String expectedPrice = "$135.00";
        Assert.assertEquals("The product price is not $135.00. ", expectedPrice, actualPrice);

        //Change the Qty to ‘5’
        // sendTextOnElement(By.className("input-text qty"),"5");
        driver.findElement(RelativeLocator.with(By.tagName("input")).toRightOf(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$45.00']"))).clear();
        driver.findElement(RelativeLocator.with(By.tagName("input")).toRightOf(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$45.00']"))).sendKeys("5");

        //Click on the ‘Update Shopping Cart’ button
        clickOnElement(By.xpath("//button[@title='Update Shopping Cart']"));

        //Verify the product price ‘$225.00’
        String actualUpdatedPrice = driver.findElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$225.00']")).getText();
        String expectedUpdatedPrice = "$225.00";
        Assert.assertEquals("The product price is not $225.00. ", expectedUpdatedPrice, actualUpdatedPrice);
    }


    //Close the browser
    @After
    public void tearDown() {
        closeBrowser();
    }
}
