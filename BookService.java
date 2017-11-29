import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BookService {
    private final String FILE_NAME = "records.txt";
    private static final BookService bookServiceSingleton = new BookService();

    private Map<String, Book> allBooks = new HashMap<>();

    public static BookService getInstance() {
        return bookServiceSingleton;
    }

    private BookService() {
        loadBooks();
    }

    public String[][] getBooksForTable() {
        String[][] data = new String[allBooks.size()][5];
        int counter = 0;
        for (Book book : allBooks.values()) {
            data[counter][0] = book.getTitle();
            data[counter][1] = book.getAuthor();
            data[counter][2] = book.getReleaseDate();
            data[counter][3] = book.getIsbn();
            data[counter][4] = book.getCheckedOutStatusAsString();
            counter++;
        }
        return data;
    }

    public Map<String, Book> checkoutBook(String isbn) {
        Book book = allBooks.get(isbn);
        book.checkout();
        allBooks.put(isbn, book);
        writeBooksToFile();
        return allBooks;
    }

    public Map<String, Book> returnBook(String isbn) {
        Book book = allBooks.get(isbn);
        book.returnBook();
        allBooks.put(isbn, book);
        writeBooksToFile();
        return allBooks;
    }

    public Map<String, Book> addBook(Book bookToAdd) {
        allBooks.put(bookToAdd.getIsbn(), bookToAdd);
        writeBooksToFile();
        return allBooks;
    }

    private void loadBooks() {
        try {
            File f = new File(FILE_NAME);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String title;
            String author;
            String releaseDate;
            String isbn;
            String isCheckedOut;
            b.readLine();
            while ((title = b.readLine()) != null) {
                author = b.readLine();
                releaseDate = b.readLine();
                isbn = b.readLine();
                isCheckedOut = b.readLine();
                b.readLine();
                Book book = new Book(title, author, releaseDate, isbn, isCheckedOut);
                allBooks.put(book.getIsbn(), book);
            }
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeBooksToFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(allBooks.size()));
            bufferedWriter.newLine();
            for (Book book : allBooks.values()) {
                bufferedWriter.write(book.getTitle());
                bufferedWriter.newLine();

                bufferedWriter.write(book.getAuthor());
                bufferedWriter.newLine();

                bufferedWriter.write(book.getReleaseDate());
                bufferedWriter.newLine();

                bufferedWriter.write(book.getIsbn());
                bufferedWriter.newLine();

                bufferedWriter.write(book.getCheckedOutStatusAsString());
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
