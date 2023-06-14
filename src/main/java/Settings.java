import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Settings {
    WebDriver driver;

    public Settings(WebDriver driver) {
        this.driver = driver;
    }

    public void ChangeThemes() throws InterruptedException {
        // REDIRECT TO THEMES
        driver.get("https://gruplm.com/user/theme/version");

        // CHANGE TO THEME 5
        driver.findElement(By.xpath("//img[@src='https://gruplm.com/assets/front/img/user/templates/home_five.png']")).click();

        // SUBMIT
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
        Thread.sleep(1000);
    }

    public void ChangeGeneralSettings() throws InterruptedException {
        // REDIRECT TO GENERAL SETTINGS
        driver.get("https://gruplm.com/user/general-settings");

        // INPUT WEBSITE TITLE
        WebElement website_title = driver.findElement(By.xpath("//input[@name='website_title']"));
        website_title.clear();
        website_title.sendKeys("KELOMPOK 2");

        // INPUT TIMEZONE
        new Select(driver.findElement(By.xpath("//select[@name='timezone']"))).selectByVisibleText("Asia/Jakarta / (UTC 7.00)");

        // INPUT BASE CURRENCY SYMBOL
        WebElement base_currency_symbol = driver.findElement(By.xpath("//input[@name='base_currency_symbol']"));
        base_currency_symbol.clear();
        base_currency_symbol.sendKeys("IDR");

        // INPUT BASE CURRENCY TEXT
        WebElement base_currency_text = driver.findElement(By.xpath("//input[@name='base_currency_text']"));
        base_currency_text.clear();
        base_currency_text.sendKeys("IDR");

        // INPUT BASE CURRENCY RATE
        WebElement base_currency_rate = driver.findElement(By.xpath("//input[@name='base_currency_rate']"));
        base_currency_rate.clear();
        base_currency_rate.sendKeys("14840");

        // SUBMIT
        base_currency_rate.sendKeys(Keys.RETURN);
        Thread.sleep(1000);
    }

    public void ChangeColorSettings() throws InterruptedException {
        // REDIRECT TO COLOR SETTINGS
        driver.get("https://gruplm.com/user/color");

        // INPUT BASE COLOR
        WebElement base_color = driver.findElement(By.xpath("//input[@name='base_color']"));
        base_color.clear();
        base_color.sendKeys("FF0000");

        // SUBMIT
        driver.findElement(By.xpath("//button[@id='permissionBtn']")).click();
        Thread.sleep(1000);
    }
}
