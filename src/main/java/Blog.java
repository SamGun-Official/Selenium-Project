import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Blog {
    WebDriver driver;

    public Blog(WebDriver driver) {
        this.driver = driver;
    }

    public void addCategory(){
        driver.get("https://gruplm.com/user/blog-categories?language=en");

        //CLICK ADD BLOG CATEGORY
        driver.findElement(By.xpath("//a[@class='btn btn-primary float-right btn-sm']")).click();

        // SELECT LANGUAGE
        new Select(driver.findElement(By.xpath("//select[@name='user_language_id']"))).selectByIndex(1);

        // INPUT NAME
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Lo Ga Bahaya Ta");

        //SELECT ACTIVE
        new Select(driver.findElement(By.xpath("//select[@name='status']"))).selectByIndex(1);

        //SERIAL NUMBER
        driver.findElement(By.xpath("//input[@name='serial_number']")).sendKeys("1");

        //SUBMIT
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
    }
}
