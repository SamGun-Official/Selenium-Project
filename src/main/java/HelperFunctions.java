import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperFunctions {
    public static void waitDomReady(WebDriver driver, WebDriverWait wait) {
        wait.until(web -> ((JavascriptExecutor) web).executeScript("return document.readyState").toString().equals("complete"));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }
}
