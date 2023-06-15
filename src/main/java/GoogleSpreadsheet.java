import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GoogleSpreadsheet {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SPREADSHEET_ID = "1tbq9V21kLhjGz27Q-iy9aspHGzAernQy54R64fHvARQ";
    private static final String CELL_RANGE = System.getenv("CELL_RANGE");

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

    /**
     * Fetch data asynchronously from the requested spreadsheet.
     */
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

    /**
     * Automated user registration process after selecting a pricing plan.
     */
    public static void registrationProcess(WebDriver driver, WebDriverWait wait, List<List<Object>> credentialsData) {
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
                break;
            }

            driver.findElement(elementsIdentifier.get(0)).clear();
            driver.findElement(elementsIdentifier.get(1)).clear();
            driver.findElement(elementsIdentifier.get(2)).clear();
            driver.findElement(elementsIdentifier.get(3)).clear();
            System.out.println("Username has been taken! Retrying with other data...");
        }

        if (indexFilterList.size() > 0) {
            driver.findElement(By.id("first_name")).sendKeys(randomData.get(3).toString());
            driver.findElement(By.id("last_name")).sendKeys(randomData.get(4).toString());
            driver.findElement(By.id("phone")).sendKeys(randomData.get(5).toString());
            driver.findElement(By.id("company_name")).sendKeys(randomData.get(6).toString());
            driver.findElement(By.id("country")).sendKeys(randomData.get(7).toString());
            driver.findElement(By.xpath("//div[@id='couponReload']//input[@type='text' and @name='coupon']")).sendKeys("softwaretesting");
            driver.findElement(By.id("basic-addon2")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='couponReload']//div[@class='alert alert-success']")));
            driver.findElement(By.xpath("//div[@class='nice-select olima_select']")).click();
            driver.findElement(By.xpath("//li[@data-value='Paypal']")).click();
            driver.findElement(By.id("confirmBtn")).click();

            HelperFunctions.waitDomReady(driver, wait);
            driver.findElement(By.linkText("Go to Home")).click();
        } else {
            System.out.println("All username were registered before! No extra data to register!");
        }
    }
}
