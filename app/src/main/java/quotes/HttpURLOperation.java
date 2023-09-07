package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLOperation {

    private String url;
    private String method;
    private String data;

    public HttpURLOperation(String url, String method) {
        this.url = url;
        this.method = method.toUpperCase();
    }

    public String fetchFromAPI() {
        try {
            if (startConnection()) {
                URL url = new URL(this.url);
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.setRequestMethod(this.method);
                int responseCode = request.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStreamReader response = new InputStreamReader(request.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(response);
                    String responseData = bufferedReader.readLine();
                    this.data = responseData;
                } else {
                    data = "API response code: " + responseCode;
                    throw new IOException("API response code: " + responseCode);
                }
            }
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());

            // If an exception occurs, get a random quote from the local file
            FileOperation fileOperation = new FileOperation();
            fileOperation.readAllFile("app/src/main/resources/recentquotes2.json");
            Quote randomQuote = fileOperation.getRandomQuote("app/src/main/resources/recentquotes2.json");
            this.data = randomQuote.toString();
        }

        return data;
    }


    public boolean startConnection () {
        boolean checkConnection = false ;
        try {
            URL url = new URL(this.url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod(this.method);
            checkConnection = request.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
        return checkConnection;
    }

}
