import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

class PricingSelection {
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

class GoogleSpreadsheet {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SPREADSHEET_ID = "1tbq9V21kLhjGz27Q-iy9aspHGzAernQy54R64fHvARQ";
    private static final String CELL_RANGE = "Sheet1!A2:H5";

    /**
     * Authorizes the installed application to access user's protected data.
     */
    private static Credential authorizeClient(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        File clientSecret = new File(System.getProperty("user.dir") + System.getenv("CLIENT_SECRET"));
        InputStream inputFile = new FileInputStream(clientSecret);

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(inputFile));
        GoogleAuthorizationCodeFlow codeFlow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY))
                        .setDataStoreFactory(new FileDataStoreFactory(new File(System.getProperty("user.dir") + "/GoogleAPIKey")))
                        .setAccessType("offline").build();

        return new AuthorizationCodeInstalledApp(codeFlow, new LocalServerReceiver()).authorize("user");
    }

    public static List<List<Object>> getData() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                Boolean.parseBoolean(System.getenv("ENABLE_AUTHORIZATION")) ? authorizeClient(HTTP_TRANSPORT) : null)
                        .setApplicationName("Businesso Testing")
                        .build();
        ValueRange response = service.spreadsheets().values()
                .get(SPREADSHEET_ID, CELL_RANGE)
                .setKey(System.getenv("API_KEY"))
                .execute();

        return response.getValues();
    }

    public static void registrationProcess(WebDriver driver, WebDriverWait wait, List<List<Object>> credentialsData) {
        // Should add duplicate login found, else retry until counter is equal to data
        // length.
        // Random random = new Random();
        // List<Object> randomData =
        // credentialsData.get(random.nextInt(credentialsData.size()));
        List<Object> randomData = credentialsData.get(2);

        driver.findElement(By.xpath("//input[@type='text' and @name='username']")).sendKeys(randomData.get(0).toString());
        driver.findElement(By.xpath("//input[@type='email' and @name='email']")).sendKeys(randomData.get(1).toString());
        driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys(randomData.get(2).toString());
        driver.findElement(By.xpath("//input[@type='password' and @name='password_confirmation']")).sendKeys(randomData.get(2).toString());
        driver.findElement(By.xpath("//button[@type='submit' and @class='main-btn']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmBtn")));

        driver.findElement(By.id("first_name")).sendKeys(randomData.get(3).toString());
        driver.findElement(By.id("last_name")).sendKeys(randomData.get(4).toString());
        driver.findElement(By.id("phone")).sendKeys(randomData.get(5).toString());
        driver.findElement(By.id("company_name")).sendKeys(randomData.get(6).toString());
        driver.findElement(By.id("country")).sendKeys(randomData.get(7).toString());
        driver.findElement(By.xpath("//div[@id='couponReload']//input[@type='text' and @name='coupon']")).sendKeys("softwaretesting");
    }
}

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
            wait.until(web -> ((JavascriptExecutor) web).executeScript("return document.readyState").toString().equals("complete"));
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("DONE!");
            Thread.sleep(5000);
            driver.quit();
        }
    }
}
