import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Quotes {
    WebDriver driver;
    WebDriverWait wait;

    public Quotes(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void changeQuotesStatus() throws InterruptedException {
        // REDIRECT TO QUOTES PAGE
        driver.get("https://gruplm.com/user/all/quotes");
        HelperFunctions.waitDomReady(driver, wait);

        // OPEN QUOTE DETAIL
        driver.findElement(By.xpath("//button[@class='btn btn-secondary btn-sm']")).click();

        // CLOSE QUOTE DETAIL
        driver.findElement(By.xpath("//button[@class='btn btn-secondary']")).click();

        // CHANGE QUOTE STATUS TO PROCESSING
        new Select(driver.findElement(By.xpath("//select[@name='status']"))).selectByIndex(1);

        // GO TO QUOTE STATUS PROCESSING
        driver.get("https://gruplm.com/user/processing/quotes");

        // CHANGE QUOTE STATUS TO COMPLETED
        new Select(driver.findElement(By.xpath("//select[@name='status']"))).selectByIndex(2);

        // OPEN COMPLETED PAGE
        driver.get("https://gruplm.com/user/completed/quotes");

        // DELETE QUOTE
        driver.findElement(By.xpath("//button[@class='btn btn-danger btn-sm deletebtn']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }

    public void insertQuotes() throws InterruptedException {
        // REDIRECT TO ADD QUOTE PAGE
        driver.get("https://gruplm.com/dummy/quote");
        HelperFunctions.waitDomReady(driver, wait);

        // ISI NAMAE
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Airlangga");

        // ISI EMAIL
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("tidur@gmail.com");

        // MASUKKAN QUOTE
        driver.findElement(By.xpath("//input[@name='Masukkan_Quote']")).sendKeys("Manusia butuh tidur sebanyak 12 jam dalam sehari");

        // SUBMIT QUOTE
        driver.findElement(By.xpath("//button[@class='main-btn template-btn']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }

    public void formBuilder() throws InterruptedException {
        // REDIRECT KE FORM BUILDER
        driver.get("https://gruplm.com/user/quote/form?language=en");
        HelperFunctions.waitDomReady(driver, wait);

        // PICK NO
        WebElement noRadioButton = driver.findElement(By.xpath("//label[contains(@class, 'selectgroup-item')]//span[text()='No']/preceding-sibling::input[@type='radio']"));
        noRadioButton.click();

        // INPUT LABEL NAME
        driver.findElement(By.xpath("//input[name='label']")).sendKeys("Quote ke-2");

        // INPUT PLACEHOLDER
        driver.findElement(By.xpath("//input[name='placeholder']")).sendKeys("Tidak harus diisi");

        // SUBMIT
        driver.findElement(By.id("submitBtn")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }
}
