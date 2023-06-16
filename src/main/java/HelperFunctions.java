import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperFunctions {
    public static void waitDomReady(WebDriver driver, WebDriverWait wait) {
        wait.until(web -> ((JavascriptExecutor) web).executeScript("return document.readyState").toString().equals("complete"));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public static void waitNotificationClosed(WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-notify='container']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-notify='container']")));
    }

    public static void waitLoaderFaded(WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='request-loader show']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='request-loader show']")));
    }
}
