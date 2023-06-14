import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import java.time.Duration;

class PageDirect{
    public static void navigateToPricing(WebDriver driver) {
        WebElement pricingTab = driver.findElement(By.linkText("Pricing"));
        pricingTab.click();

        WebElement lifetimeTab = driver.findElement(By.linkText("Lifetime"));
        lifetimeTab.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2)");
    }

    public static void clickPurchaseButton(WebDriver driver) {
        String href = "https://gruplm.com/registration/step-1/regular/21";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"a[href='" + href + "']\").click();");
    }
}

public class Main {
    public static final String DRIVER_PATH = System.getenv("DRIVER_PATH");
    public static final String TARGET_URL = "https://gruplm.com/";

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        try {
            System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
            driver.manage().window().maximize();
            driver.get(TARGET_URL);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(web -> ((JavascriptExecutor) web).executeScript("return document.readyState").toString().equals("complete"));
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

            PageDirect.navigateToPricing(driver);
            PageDirect.clickPurchaseButton(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("DONE!");
            Thread.sleep(5000);
            driver.quit();
        }
    }
}
