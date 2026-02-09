package steps;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


import javax.net.ssl.SSLContext;

public class ImportExportResultToXraySteps {

    private static final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI3MTIwMjA6OWYwYzgwZGQtY2I4ZC00NTAwLTk4NzItYTQ5MmEzOWU3MTRkIiwiaXNYZWEiOmZhbHNlLCJpYXQiOjE3NzAyODI3OTQsImV4cCI6MTc3MDM2OTE5NCwiYXVkIjoiQzRCRTk4MUExNUMzNEU4OEI5NDVDMTY3RDNGNTA5MDYiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiJDNEJFOTgxQTE1QzM0RTg4Qjk0NUMxNjdEM0Y1MDkwNiJ9.n572zOHkJQv_pth9fQrz8lVgcYPEm-ZsWOWUjRx8tZw";


    public static String getToken() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        String clientID = "C4BE981A15C34E88B945C167D3F50906";
        String secretClient = "7aca388c64b6023a5b250f2a36e0f741fdf789d510885c8cc6c5f39eaa4ef05c";
        //Token généré à l'aide de la méthode getToken
        // Méthode correcte pour HttpClient 4.x
        //System.out.println("LE TOKEN EST GENERER: ");
        SSLContext sslContext = SSLContexts.custom()
                .useProtocol("TLSv1.2")  // useProtocol, PAS setProtocol
                .build();

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TLSv1.2", "TLSv1.3"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier()
        );

        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build()) {

            HttpPost request = new HttpPost("https://xray.cloud.getxray.app/api/v1/authenticate");
            request.addHeader("Content-Type", "application/json");

            String input = "{ \"client_id\": \"" + clientID  + "\", \"client_secret\": \"" + secretClient + "\"}";
            request.setEntity(new StringEntity(input, StandardCharsets.UTF_8));

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String result = EntityUtils.toString(response.getEntity());
                System.out.println(result);
                return result.replace("\"", "").trim();
            }
        }
    }


    public static void remonteeXray() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, InterruptedException {
        HttpURLConnection conn;
        URL url;
        String result;
        String  URL ="https://xray.cloud.getxray.app/api/v1/import/execution/cucumber";
        //Call the openConnection method on the URL to create a connection object
        url = new URL(URL);
        conn = (HttpURLConnection) url.openConnection();

        //Various settings of HttpURLConnection
        //Set HTTP method to POST
        conn.setRequestMethod("POST");
        //Allow body submission of request
        conn.setDoInput(true);
        //Allow body reception of response
        conn.setDoOutput(true);
        //Specify Json format
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setRequestProperty("Authorization", "Bearer " + token);
// 2.Establish a connection
        conn.connect();
        // 3.Write to request and body
        //Get OutputStream from HttpURLConnection and write json string
        Thread.sleep(10000);
        PrintStream ps = new PrintStream(conn.getOutputStream());

        Path filePath = Path.of(System.getProperty("user.dir")+"/target/cucumber.json");
        String content = Files.readString(filePath);
        System.out.println("mon fichier  = "+content);
        ps.print(content);
        ps.close();

        // 4.Receive a response
        //HttpStatusCode 200 is returned at the end of normal operation
        if (conn.getResponseCode() != 200) {
            //Error handling
        }
        //Get InputStream from HttpURLConnection and read
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        result = sb.toString();
        // 5.Disconnect
        conn.disconnect();
        //Return the result to the caller
        System.out.println(result);
    }
    public static void downloadFeatureFiles(String testKeys) {
        try {
            System.out.println("EXPORT JSON TEST FEATURE IMPORTE :");
            URL url = new URL("https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=" + testKeys);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Content-Type", "application/json");

            InputStream inputStream = conn.getInputStream();
            FileOutputStream outputStream = new FileOutputStream("features.zip");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.err.println("Erreur lors du téléchargement des fichiers feature: " + e.getMessage());
        }
    }


}
