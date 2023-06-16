import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Portofolio {
    WebDriver driver;
    WebDriverWait wait;

    public Portofolio(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void AddCategory(String name) throws InterruptedException {
        // REDIRECT TO PORTOFOLIO CATEGORY
        driver.get("https://gruplm.com/user/portfolio-categories?language=en");
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);

        // CLICK ADD PORTOFOLIO CATEGORY
        driver.findElement(By.xpath("//a[@class='btn btn-primary float-right btn-sm']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createModal")));

        // SELECT LANGUAGE
        new Select(driver.findElement(By.xpath("//select[@name='user_language_id']"))).selectByIndex(1);
        HelperFunctions.waitLoaderFaded(wait);

        // INPUT NAME
        driver.findElement(By.xpath("//form[@id='ajaxForm']//input[@name='name']")).sendKeys(name);

        // SELECT STATUS
        new Select(driver.findElement(By.xpath("//form[@id='ajaxForm']//select[@name='status']"))).selectByIndex(1);

        // INPUT SERIAL NUMBER
        driver.findElement(By.xpath("//input[@name='serial_number']")).sendKeys("1");

        // SUBMIT
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }

    public void FeaturedCategory() throws InterruptedException {
        HelperFunctions.waitDomReady(driver, wait);

        // SELECT FEATURED CATEGORY YES
        new Select(driver.findElements(By.xpath("//select[@name='status']")).get(0)).selectByIndex(0);

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }
}
