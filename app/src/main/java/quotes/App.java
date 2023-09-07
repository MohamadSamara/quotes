package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        fetchAndUpdateQuotesWithoutDuplicate();
    }

    public static void fetchAndUpdateQuotesWithoutDuplicate() {
        try {
            List<String> apiQuotes = fetchQuotesFromAPI();

            if (!apiQuotes.isEmpty()) {
                String randomApiQuote = apiQuotes.get(0);
                System.out.println("Author: " + "Ron Swanson");
                System.out.println("API Quote: " + randomApiQuote);

                Quote newQuote = new Quote("Ron Swanson", randomApiQuote);

                Quote[] existingLocalQuotes = loadQuotesFromFile();

                if (!isDuplicateQuote(existingLocalQuotes, newQuote)) {
                    saveQuote(newQuote, existingLocalQuotes);
                }
            }
        } catch (IOException e) {
            System.out.println("Error fetching from API: " + e.getMessage());
            displayRandomLocalQuote();
        }
    }

    public static List<String> fetchQuotesFromAPI() throws IOException {
        URL apiUrl = new URL("https://ron-swanson-quotes.herokuapp.com/v2/quotes");
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");
        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String apiResponse = bufferedReader.readLine();
        Gson gson = new Gson();
        return gson.fromJson(apiResponse, List.class);
    }

    public static void displayRandomLocalQuote() {
        Quote[] localQuotes = loadQuotesFromFile();
        if (localQuotes.length > 0) {
            int randomIndex = new Random().nextInt(localQuotes.length);
            Quote randomLocalQuote = localQuotes[randomIndex];
            System.out.println("Local Quote: " + randomLocalQuote.getText());
            System.out.println("Author: " + randomLocalQuote.getAuthor());
        } else {
            System.out.println("There are no local quotes.");
        }
    }

    public static Quote[] loadQuotesFromFile() {
        Gson gson = new Gson();
        Quote[] quotes = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("app/src/main/resources/recentquotes2.json"))) {
            quotes = gson.fromJson(reader, Quote[].class);
        } catch (IOException e) {
            System.out.println("Error loading quotes from file: " + e.getMessage());
        }
        return quotes != null ? quotes : new Quote[0];
    }

    public static void saveQuotesToFile(List<Quote> quotes) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("app/src/main/resources/recentquotes2.json")) {
            gson.toJson(quotes, writer);
        } catch (IOException e) {
            System.out.println("Error saving quotes to file: " + e.getMessage());
        }
    }

    public static void saveQuote(Quote newQuote, Quote[] existingQuotes) {
        List<Quote> updatedQuotes = new ArrayList<>(Arrays.asList(existingQuotes));
        updatedQuotes.add(newQuote);
        saveQuotesToFile(updatedQuotes);
    }

    public static boolean isDuplicateQuote(Quote[] quotes, Quote newQuote) {
        for (Quote existingQuote : quotes) {
            if (existingQuote.getText().equals(newQuote.getText())) {
                return true;
            }
        }
        return false;
    }

}