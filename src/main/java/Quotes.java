import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Quotes {
    WebDriver driver;
    public Quotes(WebDriver driver) {
        this.driver = driver;
    }

    public void changeQuotesStatus(){
        //REDIRECT TO QUOTES PAGE
        driver.get("https://gruplm.com/user/all/quotes");

        //OPEN QUOTE DETAIL
        driver.findElement(By.xpath("//button[@class='btn btn-secondary btn-sm']")).click();

        //CLOSE QUOTE DETAIL
        driver.findElement(By.xpath("//button[@class='btn btn-secondary']")).click();

        //CHANGE QUOTE STATUS TO PROCESSING
        new Select(driver.findElement(By.xpath("//select[@name='status']"))).selectByIndex(1);

        //GO TO QUOTE STATUS PROCESSING
        driver.get("https://gruplm.com/user/processing/quotes");

        //CHANGE QUOTE STATUS TO COMPLETED
        new Select(driver.findElement(By.xpath("//select[@name='status']"))).selectByIndex(2);

        //OPEN COMPLETED PAGE
        driver.get("https://gruplm.com/user/completed/quotes");

        //DELETE QUOTE
        driver.findElement(By.xpath("//button[@class='btn btn-danger btn-sm deletebtn']")).click();
    }

    public void insertQuotes(){
        //REDIRECT TO ADD QUOTE PAGE
        driver.get("https://gruplm.com/dummy/quote");

        //ISI NAMAE
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Airlangga");

        //ISI EMAIL
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("tidur@gmail.com");

        //MASUKKAN QUOTE
        driver.findElement(By.xpath("//input[@name='Masukkan_Quote']")).sendKeys("Manusia butuh tidur sebanyak 12 jam dalam sehari");

        //SUBMIT QUOTE
        driver.findElement(By.xpath("//button[@class='main-btn template-btn']")).click();
    }

    public void formBuilder(){
        //REDIRECT KE FORM BUILDER
        driver.get("https://gruplm.com/user/quote/form?language=en");

        //PICK NO
        WebElement noRadioButton = driver.findElement(By.xpath("//label[contains(@class, 'selectgroup-item')]//span[text()='No']/preceding-sibling::input[@type='radio']"));
        noRadioButton.click();

        //INPUT LABEL NAME
        driver.findElement(By.xpath("//input[name='label']")).sendKeys("Quote ke-2");

        //INPUT PLACEHOLDER
        driver.findElement(By.xpath("//input[name='placeholder']")).sendKeys("Tidak harus diisi");
    }
}
