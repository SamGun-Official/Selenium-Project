import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
    public Login(WebDriver driver) {
        // REDIRECT TO LOGIN
        driver.get("https://gruplm.com/login");

        // INPUT EMAIL
        driver.findElements(By.xpath("//input[@name='email']")).get(0).sendKeys("dummy@gmail.com");

        // INPUT PASSWORD AND ENTER
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("dummydummy");
        password.sendKeys(Keys.RETURN);
    }
}
