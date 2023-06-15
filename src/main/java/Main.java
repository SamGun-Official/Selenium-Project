import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    private static final String DRIVER_PATH = System.getenv("DRIVER_PATH");
    private static final String TARGET_URL = "https://gruplm.com/";

    public static void loginProcess(WebDriver driver, List<Object> fetchedData) {
        // REDIRECT TO LOGIN
        driver.get("https://gruplm.com/login");

        // INPUT EMAIL
        driver.findElements(By.xpath("//input[@name='email']")).get(0).sendKeys(fetchedData.get(1).toString());

        // INPUT PASSWORD AND ENTER
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys(fetchedData.get(2).toString());
        password.sendKeys(Keys.RETURN);
    }

    public static void loginProcess(WebDriver driver) {
        // REDIRECT TO LOGIN
        driver.get("https://gruplm.com/login");

        // INPUT EMAIL
        driver.findElements(By.xpath("//input[@name='email']")).get(0).sendKeys("dummy@gmail.com");

        // INPUT PASSWORD AND ENTER
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("dummydummy");
        password.sendKeys(Keys.RETURN);
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        try {
            System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
            driver.manage().window().maximize();
            driver.get(TARGET_URL);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            HelperFunctions.waitDomReady(driver, wait);

            // PricingSelection.navigateToPricing(driver);
            // PricingSelection.clickPurchaseButton(driver);

            // GoogleSpreadsheet googleSpreadsheet = new GoogleSpreadsheet();
            // List<List<Object>> credentialsData = googleSpreadsheet.getData();
            // List<Object> fetchedData = googleSpreadsheet.registrationProcess(driver,
            // wait, credentialsData);
            // if (fetchedData != null) {
            // loginProcess(driver, fetchedData);
            // }

            loginProcess(driver);

            // SHOP MANAGEMENT
            // ShopManagement shop_management = new ShopManagement(driver);
            // shop_management.ChangeSettings();

            // SETTINGS
            // Settings settings = new Settings(driver);
            // settings.ChangeThemes();
            // settings.ChangeGeneralSettings();
            // settings.ChangeColorSettings();

            // HOME
            // Thread.sleep(500);
            // Home home = new Home(driver);
            // home.AddHeroSection();
            // home.AddHomeSection();
            // home.AddVideo();

            // FOOTER
            // Footer footer = new Footer(driver);
            // footer.AddQuickLinks();

            // PORTOFOLIO
            // Portofolio portofolio = new Portofolio(driver);
            // portofolio.AddCategory("Certificate");
            // portofolio.AddCategory("Experience");
            // portofolio.FeaturedCategory();

            // TEAM
            // Team team = new Team(driver);
            // team.UpdateTeamSection();

            // BLOG
            // Blog blog = new Blog(driver);
            // blog.addCategory();

            // CUSTOM PAGE
            // CustomPage custom_page = new CustomPage(driver);
            // custom_page.CreatePage();

            // QUOTES
            // Quotes quotes = new Quotes(driver);
            // quotes.formBuilder();
            // quotes.insertQuotes();
            // quotes.changeQuotesStatus();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("DONE!");
            Thread.sleep(5000);
            driver.quit();
        }
    }
}
