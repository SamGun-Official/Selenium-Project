import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Footer {
    WebDriver driver;
    WebDriverWait wait;

    public Footer(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void AddFooter() throws InterruptedException {
        // REDIRECT TO FOOTER SECTION
        driver.get("https://gruplm.com/user/footer/text?language=en");
        HelperFunctions.waitDomReady(driver, wait);

        // ADD FOOTER LOGO
        driver.findElement(By.xpath("//input[@name='logo']")).sendKeys(System.getProperty("user.dir") + "/src/images/tuhkan_babi.jpg");

        // ADD COPYRIGHT TEXT
        driver.findElement(By.xpath("//div[@role='textbox']")).clear();
        driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys("Copyright By Samuel Gunawan - Ignatius Odi - Anderson Soegiharto - Airlangga");

        // SAVE
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }

    public void AddQuickLinks() throws InterruptedException {
        // REDIRECT TO QUICK LINKS
        driver.get("https://gruplm.com/user/footer/quick_links?language=en");
        HelperFunctions.waitDomReady(driver, wait);

        // CLICK ADD
        driver.findElement(By.xpath("//a[@class='btn btn-sm btn-primary float-lg-right float-left']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createModal")));

        // SELECT LANGUAGE
        new Select(driver.findElement(By.xpath("//select[@id='language']"))).selectByIndex(1);
        HelperFunctions.waitLoaderFaded(wait);

        // INPUT TITLE
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Check This Out!");

        // INPUT URL
        driver.findElement(By.xpath("//input[@name='url']")).sendKeys("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        // INPUT SERIAL NUMBER
        driver.findElement(By.xpath("//input[@name='serial_number']")).sendKeys("1");

        // SAVE
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }
}
