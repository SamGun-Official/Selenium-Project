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
import java.util.Collections;
import java.util.List;

public class GoogleSpreadsheet {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SPREADSHEET_ID = "1tbq9V21kLhjGz27Q-iy9aspHGzAernQy54R64fHvARQ";
    private static final String CELL_RANGE = System.getProperty("spreadsheet.cells.range");

    /**
     * Authorizes the installed application to access user's protected data.
     * Requires client_secret.json from API to start OAuth 2.0 Consent Screen.
     */
    private Credential authorizeClient(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        File clientSecret = new File(System.getProperty("user.dir") + "/GoogleAPIKey/client_secret.json");
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
    public List<List<Object>> getData() throws GeneralSecurityException, IOException {
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
}
