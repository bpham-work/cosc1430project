public class Book {
    private String title;
    private String author;
    private String releaseDate;
    private String isbn;
    private boolean isCheckedOut;

    public Book(String title, String author, String releaseDate, String isbn, String isCheckedOut) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.isbn = isbn;
        this.isCheckedOut = isCheckedOut.equals("Yes");
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public String getCheckedOutStatusAsString() {
        return isCheckedOut ? "Yes" : "No";
    }

    public void checkout() {
        this.isCheckedOut = true;
    }

    public void returnBook() {
        this.isCheckedOut = false;
    }
}
