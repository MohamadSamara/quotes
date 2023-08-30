package quotes;

    public class Quote {
        private String author;
        private String text;

        public Quote(String author, StringBuilder text) {
            this.author = author;
            this.text = String.valueOf(text);
        }
        public String getAuthor() {
            return author;
        }
        public String getText() {
            return text;
        }
    }
