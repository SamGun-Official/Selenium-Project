import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    private static final String DRIVER_PATH = System.getenv("DRIVER_PATH");
    private static final String TARGET_URL = "https://gruplm.com/";
    private static final boolean DO_REGISTRATION = false;

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
            System.setProperty("spreadsheet.cells.range", "Sheet1!A6:H35");

            driver.manage().window().maximize();
            driver.get(TARGET_URL);

            // DATA FETCH
            GoogleSpreadsheet googleSpreadsheet = new GoogleSpreadsheet();
            List<List<Object>> credentialsData = googleSpreadsheet.getData();

            // PRICING PLAN
            List<Object> fetchedData;
            HelperFunctions.waitDomReady(driver, wait);
            if (DO_REGISTRATION) {
                Registration.ChoosePricingPlan(driver, wait);
                fetchedData = Registration.RegistrationProcess(driver, wait, credentialsData);
            } else {
                fetchedData = Registration.BypassRegistration(driver, wait, credentialsData);
            }
            if (fetchedData != null) {
                Registration.LoginProcess(driver, fetchedData);
            }

            // SETTINGS
            Settings settings = new Settings(driver, wait);
            settings.ChangeThemes();
            settings.ChangeGeneralSettings();
            settings.ChangeColorSettings();

            // SHOP MANAGEMENT
            ShopManagement shop_management = new ShopManagement(driver, wait);
            shop_management.ChangeSettings();

            // HOME
            Home home = new Home(driver, wait);
            home.AddHeroSection();
            home.AddHomeSection();
            home.AddVideo();

            // FOOTER
            Footer footer = new Footer(driver, wait);
            footer.AddQuickLinks();

            // PORTOFOLIO
            Portofolio portofolio = new Portofolio(driver, wait);
            portofolio.AddCategory("Certificate");
            portofolio.AddCategory("Experience");
            portofolio.FeaturedCategory();

            // TEAM
            Team team = new Team(driver, wait);
            team.UpdateTeamSection();

            // BLOG
            Blog blog = new Blog(driver, wait);
            blog.addCategory();

            // CUSTOM PAGE
            CustomPage custom_page = new CustomPage(driver, wait);
            custom_page.CreatePage();

            // MENU BUILDER
            MenuBuilder.ChangeMenuItem(driver, wait);

            // QUOTES
            Quotes quotes = new Quotes(driver, wait);
            quotes.formBuilder();
            quotes.insertQuotes();
            quotes.changeQuotesStatus();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("DONE!");
            Thread.sleep(10000);
            driver.quit();
        }
    }
}
