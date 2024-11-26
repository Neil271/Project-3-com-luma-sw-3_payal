package utilities;

import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Utility extends BaseTest {
    /**
     * This method will send text on element
     */
    public void sendTextOnElement(By by, String text) {
      //  WebElement e = driver.findElement(By.xpath("")).sendKeys("");
        WebElement element=driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * This method will get the text from element
     */
    public String getTextFromElem(By by) {
        return driver.findElement(by).getText();
    }

    /**
     * This method will click on the element
     */
    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    /**
     * This method will return webelement
     */
    public WebElement findWebElement(By by) {
        return driver.findElement(by);
    }

}
