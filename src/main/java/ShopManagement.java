import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopManagement {
    WebDriver driver;

    public ShopManagement(WebDriver driver) {
        this.driver = driver;
    }

    public void ChangeSettings() throws InterruptedException {
        // REDIRECT TO SETTINGS
        driver.get("https://gruplm.com/user/item/settings");

        // DEACTIVE SHOP, CATALOG MODE, RATING SYSTEM
        List<WebElement> deactive_buttons = driver.findElements(By.xpath("//span[@class='selectgroup-button'][normalize-space()='Deactive']"));
        deactive_buttons.get(0).click();
        deactive_buttons.get(1).click();
        deactive_buttons.get(2).click();

        // INPUT TAX 0
        WebElement tax = driver.findElement(By.xpath("//input[@name='tax']"));
        tax.clear();
        tax.sendKeys("0");

        // SUBMIT
        tax.sendKeys(Keys.RETURN);
        Thread.sleep(1000);
    }
}
