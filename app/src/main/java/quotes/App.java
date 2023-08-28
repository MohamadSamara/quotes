package quotes;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        quoteFunction();
    }

    public static Quote[] quoteFunction() {
        Quote[] quote = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/recentquotes.json"))) {
            Gson gson = new Gson();
            quote = gson.fromJson(bufferedReader, Quote[].class);
            if (quote.length > 0) {
                int randomIndex = new Random().nextInt(quote.length);
                System.out.println(quote[randomIndex]);
            }
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
        return quote;
    }
}