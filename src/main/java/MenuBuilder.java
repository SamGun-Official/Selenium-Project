import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuBuilder {
    public static void ChangeMenuItem(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        HelperFunctions.waitDomReady(driver, wait);
        driver.get("https://gruplm.com/user/menu-builder?language=en");

        List<String> textSet = new ArrayList<>();
        textSet.add("About");
        textSet.add("Services");
        textSet.add("Contact");
        textSet.add("Team");
        textSet.add("Career");
        textSet.add("FAQ");
        textSet.add("Shop");
        textSet.add("Cart");
        textSet.add("Checkout");

        for (int i = 0; i < textSet.size(); i++) {
            try {
                driver.findElement(By.xpath("//ul[@id='myEditor']//li[div/span[@class='txt'and text()='" + textSet.get(i) + "'] and contains(@class, 'list-group-item')]//a[contains(@class, 'btn-danger') and contains(@class, 'btnRemove')]")).click();
                wait.until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();
            } catch (NoSuchElementException e) {
                System.out.println("Element not exist! Proceed to next targeted element!");
            }
        }

        driver.findElement(By.id("btnOutput")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }
}
