package quotes;

public class Quote {
    private String author;
    private String text;

    public Quote(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String toString() {
        return "Quote : " + text + "\n"
                + "Author : " + author;
    }
}