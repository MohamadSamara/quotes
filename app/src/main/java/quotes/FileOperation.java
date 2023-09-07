    package quotes;

    import com.google.gson.Gson;
    import com.google.gson.GsonBuilder;

    import java.io.*;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.Random;

    public class FileOperation {

        private List<Quote> quotesList;

        public FileOperation() {
            quotesList = new ArrayList<>();
        }

        public List<Quote> readAllFile(String filePath) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Quote[] quotesArray = new Quote[0];
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                quotesArray = gson.fromJson(bufferedReader, Quote[].class);
                if (quotesArray != null) {
                    quotesList.addAll(Arrays.asList(quotesArray));
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            return quotesList;
        }

        public void addQuote(Quote newQuote) {
            quotesList.add(newQuote);
        }

        public void writeToFile(String filePath) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(quotesList, writer);
                System.out.println("Quotes written to file successfully.");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }


        public Quote getRandomQuote(String filePath) {
            if (quotesList.isEmpty()) {
                return null;
            }

            readAllFile(filePath); // Read quotes from the file into the list

            Random random = new Random();
            int randomIndex = random.nextInt(quotesList.size());

            Quote randomQuote = quotesList.get(randomIndex);

            // Print the random quote
            System.out.println("Random Quote:");
            System.out.println("Text: " + randomQuote.getText());
            System.out.println("Author: " + randomQuote.getAuthor());

            return randomQuote;
        }

        public void printFile() {
            for (Quote quote : quotesList) {
                System.out.println("Text: " + quote.getText());
                System.out.println("Author: " + quote.getAuthor());
                System.out.println();
            }
        }
    }
