import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    private static final String DRIVER_PATH = System.getenv("DRIVER_PATH");
    private static final String TARGET_URL = "https://gruplm.com/";

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        try {
            System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
            driver.manage().window().maximize();
            driver.get(TARGET_URL);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            HelperFunctions.waitDomReady(driver, wait);

            // login (air)
//            driver.get("https://gruplm.com/login");
//            driver.findElement(By.xpath("//div[@class='form_group']//input[@name='email']"))
//                    .sendKeys("dummy@gmail.com");
//            driver.findElement(By.xpath("//input[@name='password']"))
//                    .sendKeys("dummydummy");
//            driver.findElement(By.xpath("//button[@class='main-btn']")).click();

            PricingSelection.navigateToPricing(driver);
            PricingSelection.clickPurchaseButton(driver);

            List<List<Object>> credentialsData = GoogleSpreadsheet.getData();
            GoogleSpreadsheet.registrationProcess(driver, wait, credentialsData);

            // new Login(driver);

            // SHOP MANAGEMENT
            // ShopManagement shop_management = new ShopManagement(driver);
            // shop_management.ChangeSettings();

            // SETTINGS
            // Settings settings = new Settings(driver);
            // settings.ChangeThemes();
            // settings.ChangeGeneralSettings();
            // settings.ChangeColorSettings();

            //HOME
//            Thread.sleep(500);
//             Home home = new Home(driver);
//             home.AddHeroSection();
//             home.AddHomeSection();
//             home.AddVideo();

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
