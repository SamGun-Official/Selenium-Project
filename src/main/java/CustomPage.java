import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomPage {
    WebDriver driver;
    WebDriverWait wait;

    public CustomPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void CreatePage() throws InterruptedException {
        // REDIRECT TO CREATE PAGE
        driver.get("https://gruplm.com/user/page/create");
        HelperFunctions.waitDomReady(driver, wait);

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

        // REDIRECT TO MENU BUILDER
        driver.get("https://gruplm.com/user/menu-builder?language=en");

        // ADD CUSTOM PAGE TO MENU BUILDER
        driver.findElement(By.xpath("//a[@data-text='Feeds']")).click();

        // UPDATE MENU
        driver.findElement(By.xpath("//button[@id='btnOutput']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }
}
