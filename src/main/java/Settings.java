import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Settings {
    WebDriver driver;
    WebDriverWait wait;

    public Settings(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void AddLogo() throws InterruptedException {
        // REDIRECT TO LOGO
        driver.get("https://gruplm.com/user/logo");
        HelperFunctions.waitDomReady(driver, wait);

        // ADD LOGO
        driver.findElement(By.id("image")).sendKeys(System.getProperty("user.dir") + "/src/images/stts.png");

        // SUBMIT
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }

    public void ChangeThemes() throws InterruptedException {
        // REDIRECT TO THEMES
        driver.get("https://gruplm.com/user/theme/version");
        HelperFunctions.waitDomReady(driver, wait);

        // CHANGE TO THEME 5
        driver.findElement(By.xpath("//img[@src='https://gruplm.com/assets/front/img/user/templates/home_five.png']")).click();

        // SUBMIT
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }

    public void ChangeGeneralSettings() throws InterruptedException {
        // REDIRECT TO GENERAL SETTINGS
        driver.get("https://gruplm.com/user/general-settings");
        HelperFunctions.waitDomReady(driver, wait);

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

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }

    public void ChangeColorSettings() throws InterruptedException {
        // REDIRECT TO COLOR SETTINGS
        driver.get("https://gruplm.com/user/color");
        HelperFunctions.waitDomReady(driver, wait);

        // INPUT BASE COLOR
        WebElement base_color = driver.findElement(By.xpath("//input[@name='base_color']"));
        base_color.clear();
        base_color.sendKeys("FF0000");

        // SUBMIT
        driver.findElement(By.xpath("//button[@id='permissionBtn']")).click();

        // WAIT FOR NOTIFICATION
        HelperFunctions.waitDomReady(driver, wait);
        Thread.sleep(1000);
    }
}
