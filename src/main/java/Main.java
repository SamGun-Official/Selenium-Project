import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("DONE!");
            Thread.sleep(5000);
            driver.quit();
        }
    }
}
