import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Portofolio {
    WebDriver driver;

    public Portofolio(WebDriver driver) {
        this.driver = driver;
    }

    public void AddCategory(String name) throws InterruptedException {
        // REDIRECT TO CATEGORY
        driver.get("https://gruplm.com/user/portfolio-categories?language=en");

        // CLICK ADD PORTOFOLIO CATEGORY
        driver.findElement(By.xpath("//a[@class='btn btn-primary float-right btn-sm']")).click();
        Thread.sleep(500);

        // SELECT LANGUAGE
        new Select(driver.findElement(By.xpath("//select[@name='user_language_id']"))).selectByIndex(1);

        // INPUT NAME
        driver.findElement(By.xpath("//form[@id='ajaxForm']//input[@name='name']")).sendKeys(name);

        // SELECT STATUS
        new Select(driver.findElement(By.xpath("//form[@id='ajaxForm']//select[@name='status']"))).selectByIndex(1);

        // INPUT SERIAL NUMBER
        driver.findElement(By.xpath("//input[@name='serial_number']")).sendKeys("1");

        // SUBMIT
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
    }

    public void FeaturedCategory() throws InterruptedException {
        // SELECT FEATURED CATEGORY YES
        new Select(driver.findElements(By.xpath("//select[@name='status']")).get(0)).selectByIndex(0);
        Thread.sleep(1000);
    }
}
