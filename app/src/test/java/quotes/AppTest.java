/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    public void testIfFileFound() {
        File findPath = new File("src/main/resources/recentquotes.json");

        boolean fileIfFound = findPath.exists();

        assertTrue(fileIfFound);
    }

    @Test
    public void testFetchQuotesFromAPI() throws IOException {
        List<String> apiQuotes = App.fetchQuotesFromAPI();
        assertFalse(apiQuotes.isEmpty(), "API quotes should not be empty");
    }

    @Test
    public void testLoadQuotesFromFile() {
        Quote[] quotes = App.loadQuotesFromFile();
        assertNotNull(quotes, "Loaded quotes should not be null");
    }

    @Test
    public void testSaveAndLoadQuotes() {
        List<Quote> quotes = new ArrayList<>(Arrays.asList(
                new Quote("Author1", new StringBuilder("Quote1")),
                new Quote("Author2", new StringBuilder("Quote2"))
        ));

        App.saveQuotesToFile(quotes);
        Quote[] loadedQuotes = App.loadQuotesFromFile();

        //Number of saved and loaded quotes should match
        assertEquals(quotes.size(), loadedQuotes.length);
        //First quote author should match
        assertEquals( quotes.get(0).getAuthor(), loadedQuotes[0].getAuthor());
        //First quote text should match
        assertEquals( quotes.get(0).getText(), loadedQuotes[0].getText());
        //Second quote author should match
        assertEquals( quotes.get(1).getAuthor(), loadedQuotes[1].getAuthor());
        //Second quote text should match
        assertEquals( quotes.get(1).getText(), loadedQuotes[1].getText());
    }

    @Test
    public void testDisplayRandomLocalQuote() {
        // make sure the method runs without exceptions
        App.displayRandomLocalQuote();
    }

    @Test
    public void testFetchAndUpdateQuotesWithoutDuplicate() {
        // make sure the method runs without exceptions
        App.fetchAndUpdateQuotesWithoutDuplicate();
    }


    @Test
    public void testIsDuplicateQuote() {
        Quote[] quotes = {
                new Quote("Author1", new StringBuilder("Quote1")),
                new Quote("Author2", new StringBuilder("Quote2"))
        };

        Quote newQuote = new Quote("Author1", new StringBuilder("Quote1"));

        assertTrue(App.isDuplicateQuote(quotes, newQuote));
    }

    @Test
    public void testIsNotDuplicateQuote() {
        Quote[] quotes = {
                new Quote("Author1", new StringBuilder("Quote1")),
                new Quote("Author2", new StringBuilder("Quote2"))
        };

        Quote newQuote = new Quote("Author3", new StringBuilder("Quote3"));

        assertFalse(App.isDuplicateQuote(quotes, newQuote));
    }

    @Test
    public void testSaveQuote() {
        Quote[] existingQuotes = {
                new Quote("Author1", new StringBuilder("Quote1")),
                new Quote("Author2", new StringBuilder("Quote2"))
        };

        Quote newQuote = new Quote("Author3", new StringBuilder("Quote3"));

        App.saveQuote(newQuote, existingQuotes);

        Quote[] updatedQuotes = App.loadQuotesFromFile();
        assertEquals(existingQuotes.length + 1, updatedQuotes.length);
        assertEquals(newQuote.getAuthor(), updatedQuotes[updatedQuotes.length - 1].getAuthor());
        assertEquals(newQuote.getText(), updatedQuotes[updatedQuotes.length - 1].getText());
    }

}