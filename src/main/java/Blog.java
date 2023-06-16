import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Blog {
    WebDriver driver;
    WebDriverWait wait;

    public Blog(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void addCategory() throws InterruptedException {
        driver.get("https://gruplm.com/user/blog-categories?language=en");
        HelperFunctions.waitDomReady(driver, wait);

        // CLICK ADD BLOG CATEGORY
        driver.findElement(By.xpath("//a[@class='btn btn-primary float-right btn-sm']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createModal")));

        // SELECT LANGUAGE
        new Select(driver.findElement(By.xpath("//select[@name='user_language_id']"))).selectByIndex(1);
        HelperFunctions.waitLoaderFaded(wait);

        // INPUT NAME
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Lo Ga Bahaya Ta");

        // SELECT ACTIVE
        new Select(driver.findElement(By.xpath("//select[@name='status']"))).selectByIndex(1);

        // SERIAL NUMBER
        driver.findElement(By.xpath("//input[@name='serial_number']")).sendKeys("1");

        // SUBMIT
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }
}
