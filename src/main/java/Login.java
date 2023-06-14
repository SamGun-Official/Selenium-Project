import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
    public Login(WebDriver driver) {
        driver.get("https://kelas.gruplm.com/");

        driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Enter Email Address']"));
        email.click();
        email.sendKeys("samgun@gmail.com");

        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Enter Password']"));
        password.click();
        password.sendKeys("samsamgun");

        driver.findElement(By.xpath("//button[normalize-space()='Login Now']")).click();
    }
}
