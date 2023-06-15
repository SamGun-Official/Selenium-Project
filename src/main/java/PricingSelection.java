import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PricingSelection {
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
