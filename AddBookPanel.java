import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookPanel extends JPanel {
    private JPanel parentPanel;
    private CardLayout parentLayout;

    private BookService bookService;

    private JLabel actionMessage;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField releaseDateField;
    private JTextField isbnField;

    public AddBookPanel(JPanel parentPanel, CardLayout parentLayout) {
        super();
        this.parentPanel = parentPanel;
        this.parentLayout = parentLayout;
        this.bookService = BookService.getInstance();
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        JLabel banner = new JLabel("Add a book with the form below");
        gc.gridx = 0;
        gc.gridy = 0;
        add(banner, gc);

        actionMessage = new JLabel();
        actionMessage.setForeground(Color.RED);
        gc.gridx = 0;
        gc.gridy = 1;
        add(actionMessage, gc);

        JLabel titleLabel = new JLabel("Book Title:");
        titleField = new JTextField("", 20);
        gc.gridx = 0;
        gc.gridy = 2;
        add(titleLabel, gc);
        gc.gridx = 1;
        add(titleField, gc);

        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField("", 20);
        gc.gridx = 0;
        gc.gridy = 3;
        add(authorLabel, gc);
        gc.gridx = 1;
        add(authorField, gc);

        JLabel releaseDateLabel = new JLabel("Pub/Rel Date:");
        releaseDateField = new JTextField("", 20);
        gc.gridx = 0;
        gc.gridy = 4;
        add(releaseDateLabel, gc);
        gc.gridx = 1;
        add(releaseDateField, gc);

        JLabel isbnLabel = new JLabel("ISBN-10:");
        isbnField = new JTextField("", 20);
        gc.gridx = 0;
        gc.gridy = 5;
        add(isbnLabel, gc);
        gc.gridx = 1;
        add(isbnField, gc);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveBookActionListener());
        gc.gridx = 2;
        gc.gridy = 6;
        add(saveButton, gc);

        JButton logOffButton = new JButton("Log off");
        logOffButton.addActionListener(new LogOffActionListener(parentPanel, parentLayout));
        gc.gridx = 5;
        gc.gridy = 7;
        add(logOffButton, gc);
    }

    private class SaveBookActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String author = authorField.getText();
            String releaseDate = releaseDateField.getText();
            String isbn = isbnField.getText();
            if (isValidEntry(title, author, releaseDate, isbn)) {
                bookService.addBook(new Book(title, author, releaseDate, isbn, "no"));
            }
        }

        private boolean isValidEntry(String title, String author, String releaseDate, String isbn) {
            if (bookService.bookExists(isbn)) {
                actionMessage.setText("Book with ISBN-10 number exists");
                return false;
            } else if (!title.isEmpty() && !author.isEmpty() && !releaseDate.isEmpty() && !isbn.isEmpty()) {
                return true;
            } else {
                actionMessage.setText("You must fill in all fields");
                return false;
            }
        }
    }
}
