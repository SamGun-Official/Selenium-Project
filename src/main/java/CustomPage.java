import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CustomPage {
    WebDriver driver;

    public CustomPage(WebDriver driver) {
        this.driver = driver;
    }

    public void CreatePage() throws InterruptedException {
        // REDIRECT TO CREATE PAGE
        driver.get("https://gruplm.com/user/page/create");

        // SELECT LANGUAGE
        new Select(driver.findElement(By.xpath("//select[@id='language']"))).selectByIndex(1);

        // INPUT NAME
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Feeds");

        // INPUT TITLE
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Feeds");

        // INPUT BODY
        driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys("HELLO WORLD!");

        // SUBMIT
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
        Thread.sleep(1000);

        // REDIRECT TO PAGES
        driver.get("https://gruplm.com/user/pages?language=en");
    }
}
