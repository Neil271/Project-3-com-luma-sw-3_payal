package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class MenTest extends Utility {


    //Base url
    String baseUrl = "https://magento.softwaretestingboard.com/";

    //Set up the browser
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() {
        //Mouse Hover on the ‘Men’ Menu
        WebElement menElement = findWebElement(By.xpath("//a[@id='ui-id-5']"));

        //Mouse Hover on the ‘Bottoms’
        WebElement bottomElement = findWebElement(By.cssSelector("#ui-id-18"));

        //perform mouse hoover
        Actions actions = new Actions(driver);
        actions.moveToElement(menElement).moveToElement(bottomElement).build().perform();

        //Click on the ‘Pants’
        clickOnElement(By.xpath("//a[@id='ui-id-23']"));

        //Mouse Hover on the product name ‘Cronus Yoga Pant’ and click on the size 32.
        WebElement pantName = findWebElement(By.linkText("Cronus Yoga Pant"));
        //Mouse Hoover
        actions.moveToElement(pantName).click().build().perform();
        //click on size 32.
        clickOnElement(By.xpath("//div[@id='option-label-size-143-item-175']"));

        //click on the Color Black.
        clickOnElement(By.xpath("//div[@id='option-label-color-93-item-49']"));

        //click on the ‘Add To Cart’ Button.
        clickOnElement(By.xpath("//button[@id='product-addtocart-button']"));

        //Verify the text ‘You added Cronus Yoga Pant to your shopping cart.’
        String expectedShoppingCartMessage = "You added Cronus Yoga Pant to your shopping cart.";
        String actualShoppingCartMessage = getTextFromElem(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
        Assert.assertEquals("the text ‘You added Cronus Yoga Pant to your shopping cart.’ is not displayed", expectedShoppingCartMessage, actualShoppingCartMessage);

        //Click on the ‘shopping cart’ Link into message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        //Verify the text ‘Shopping Cart.’
        String expectedShoppingCartText="Shopping Cart";
        String actualShoppingCartText = getTextFromElem(By.xpath("//span[@class='base']"));
        Assert.assertEquals("Shopping cart text is not displayed", expectedShoppingCartText,actualShoppingCartText);

        //Verify the product name ‘Cronus Yoga Pant’
        String actualAddedProductName = getTextFromElem(By.xpath("//td[@class='col item']//a[normalize-space()='Cronus Yoga Pant']"));
        String expectedAddedProductName = "Cronus Yoga Pant";
        Assert.assertEquals("the product name ‘Cronus Yoga Pant’ is not displayed",expectedAddedProductName,actualAddedProductName);

        //Verify the product size ‘32’
        String expectedSizeText = "32";
        String actualSizeText= getTextFromElem(By.xpath("//dd[contains(text(),'32')]"));
        Assert.assertEquals("the product size ‘32’ is not displayed",expectedSizeText,actualSizeText);

        //Verify the product color ‘Black’
        String expectedColor="Black";
        String actualColor=getTextFromElem(By.xpath("//dd[contains(text(),'Black')]"));
        Assert.assertEquals("The product color black is not displayed",expectedColor,actualColor);
    }

    //Close the browser
    @After
    public void tearDown() {
        closeBrowser();
    }
}
