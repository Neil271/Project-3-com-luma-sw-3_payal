package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WomenTest extends Utility {

    //Base url
    String baseUrl = "https://magento.softwaretestingboard.com/";

    //Set up the browser
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    //Verify the SortBy ProductName Filer
    @Test
    public void verifyTheSortByProductNameFilter() {
        //* Mouse Hover on the ‘Women’ Menu
        WebElement women = findWebElement(By.xpath("//a[@id='ui-id-4']"));

        // * Mouse Hover on the ‘Tops’
        WebElement tops = findWebElement(By.xpath("//a[@id='ui-id-9']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(women).moveToElement(tops).build().perform();

        // * Click on the ‘Jackets’
        clickOnElement(By.xpath("//a[@id='ui-id-11']"));

        //* Select Sort By filter “Product Name”
        WebElement dropDown = findWebElement(By.xpath("//div[@class='page-wrapper']//div[2]//div[3]//select[1]"));
        //Create the object of select class
        Select select = new Select(dropDown);
        //Select by visible text
        select.selectByVisibleText("Product Name");

        //* Verify the product name displayed in alphabetical order
        List<WebElement> productNames = driver.findElements(By.xpath("//li//div[1]//div[1]//strong[1]/a"));

        //store product name in the actualProductName List.
        List<String> actualProductsName = new ArrayList<>();
        for (WebElement product : productNames) {
//            System.out.println(product.getText());
            actualProductsName.add(product.getText());
        }

        //Create a copy of list array from actualProductsName
        List<String> expectedProductName = new ArrayList<>(actualProductsName);
        Collections.sort(expectedProductName); //Extract the product names and check if they are in alphabetical order using Collections.sort()
        //System.out.println(actualProductsName);

        //Compare actualProductsName VS expectedProductName
        Assert.assertEquals("product names are not in alphabetical order", expectedProductName, actualProductsName);


    }

    @Test
    public void verifyTheSortByPriceFilter() {
        //* Mouse Hover on the ‘Women’ Menu
        WebElement women = findWebElement(By.xpath("//a[@id='ui-id-4']"));

        // * Mouse Hover on the ‘Tops’
        WebElement tops = findWebElement(By.xpath("//a[@id='ui-id-9']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(women).moveToElement(tops).build().perform();

        // * Click on the ‘Jackets’
        clickOnElement(By.xpath("//a[@id='ui-id-11']"));

        //* Select Sort By filter “Product Name”
        WebElement dropDown = findWebElement(By.xpath("//div[@class='page-wrapper']//div[2]//div[3]//select[1]"));
        //Create the object of select class
        Select select = new Select(dropDown);
        //Select by visible text
        select.selectByVisibleText("Price");

        //* * Verify the product price displayed in Low to High
        List<WebElement> priceElements = driver.findElements(By.xpath("//span[@class='price-wrapper ']"));

        List<String> actualPrice = new ArrayList<>(); // add actual price in this list
        for (WebElement price : priceElements) {
            actualPrice.add(price.getText());
        }

        List<String> expectedPriceOrder = new ArrayList<>(actualPrice);
        Collections.sort(expectedPriceOrder);
        Assert.assertEquals("Price are not displayed in the Low to High order", expectedPriceOrder, actualPrice);
    }

    //Close the browser
    @After
    public void tearDown() {
         closeBrowser();
    }
}
