import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Registration {
    private static final String TARGET_URL = "https://gruplm.com/login";

    /**
     * Automated user registration process after selecting a pricing plan.
     */
    public static void ChoosePricingPlan(WebDriver driver, WebDriverWait wait) {
        driver.findElement(By.linkText("Pricing")).click();
        driver.findElement(By.linkText("Lifetime")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lifetime")));
        driver.findElement(By.xpath("//div[@id='lifetime']//div[div/h3[text()='Plutinum'] and @class='pricing-item']//a")).click();
    }

    /**
     * Fetch required data from spreadsheet to login into user's dashboard.
     * For testing purposes, used data will always be the first data in list.
     */
    public static List<Object> BypassRegistration(WebDriver driver, WebDriverWait wait, List<List<Object>> credentialsData) {
        return credentialsData.get(0);
    }

    /**
     * Automated user registration process after selecting a pricing plan.
     * 
     * @throws InterruptedException
     */
    public static List<Object> RegistrationProcess(WebDriver driver, WebDriverWait wait, List<List<Object>> credentialsData) throws InterruptedException {
        List<By> elementsIdentifier = new ArrayList<By>();
        List<Integer> indexFilterList = new ArrayList<Integer>();
        List<Object> randomData;

        elementsIdentifier.add(By.xpath("//input[@type='text' and @name='username']"));
        elementsIdentifier.add(By.xpath("//input[@type='email' and @name='email']"));
        elementsIdentifier.add(By.xpath("//input[@type='password' and @name='password']"));
        elementsIdentifier.add(By.xpath("//input[@type='password' and @name='password_confirmation']"));
        for (int i = 0; i < credentialsData.size(); i++) {
            indexFilterList.add(i);
        }

        boolean isPassthrough = false;
        while (true) {
            int selectedIndex = new Random().nextInt(indexFilterList.size());
            randomData = credentialsData.get(selectedIndex);

            driver.findElement(elementsIdentifier.get(0)).sendKeys(randomData.get(0).toString());
            driver.findElement(elementsIdentifier.get(1)).sendKeys(randomData.get(1).toString());
            driver.findElement(elementsIdentifier.get(2)).sendKeys(randomData.get(2).toString());
            driver.findElement(elementsIdentifier.get(3)).sendKeys(randomData.get(2).toString());
            driver.findElement(By.xpath("//button[@type='submit' and @class='main-btn']")).click();

            HelperFunctions.waitDomReady(driver, wait);
            indexFilterList.remove(selectedIndex);
            if (driver.getTitle().equals("DNA Store - Checkout") || indexFilterList.size() < 1) {
                if (driver.getTitle().equals("DNA Store - Checkout")) {
                    isPassthrough = true;
                }
                break;
            }

            driver.findElement(elementsIdentifier.get(0)).clear();
            driver.findElement(elementsIdentifier.get(1)).clear();
            driver.findElement(elementsIdentifier.get(2)).clear();
            driver.findElement(elementsIdentifier.get(3)).clear();
            System.out.println("Username has been taken! Retrying with other data...");
        }

        if (isPassthrough) {
            driver.findElement(By.id("first_name")).sendKeys(randomData.get(3).toString());
            driver.findElement(By.id("last_name")).sendKeys(randomData.get(4).toString());
            driver.findElement(By.id("phone")).sendKeys(randomData.get(5).toString());
            driver.findElement(By.id("company_name")).sendKeys(randomData.get(6).toString());
            driver.findElement(By.id("country")).sendKeys(randomData.get(7).toString());
            driver.findElement(By.xpath("//div[@id='couponReload']//input[@type='text' and @name='coupon']")).sendKeys("softwaretesting");
            driver.findElement(By.id("basic-addon2")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='couponReload']//div[@class='alert alert-success']")));
            driver.findElement(By.xpath("//div[@class='nice-select olima_select']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nice-select olima_select open']")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//li[@data-value='Paypal']")).click();
            driver.findElement(By.id("confirmBtn")).click();

            HelperFunctions.waitDomReady(driver, wait);
            driver.findElement(By.linkText("Go to Home")).click();

            return randomData;
        } else {
            System.out.println("All username were registered before! No extra data to register!");

            return null;
        }
    }

    /**
     * Automated user login process to get into user dashboard.
     */
    public static void LoginProcess(WebDriver driver, List<Object> fetchedData) {
        driver.get(TARGET_URL);
        driver.findElements(By.xpath("//input[@name='email']")).get(0).sendKeys(fetchedData.get(1).toString());

        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(fetchedData.get(2).toString());
        passwordField.sendKeys(Keys.RETURN);
    }
}
