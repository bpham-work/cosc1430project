import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookCheckoutPanel extends JPanel {
    private JPanel parentPanel;
    private CardLayout parentLayout;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JLabel message;

    private BookService bookService;
    private String[][] bookData;

    private final String[] COLUMN_NAMES = {
            "Book Title",
            "Author",
            "Pub/Rel Date",
            "ISBN-10",
            "Checked Out?"
    };

    public BookCheckoutPanel(JPanel parentPanel, CardLayout parentLayout) {
        super();
        this.parentPanel = parentPanel;
        this.parentLayout = parentLayout;
        this.bookService = BookService.getInstance();
        this.bookData = this.bookService.getBooksForTable();
        setLayout(new GridBagLayout());

        tableModel = new DefaultTableModel(bookData, COLUMN_NAMES) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dataTable = new JTable(tableModel);
        dataTable.setPreferredScrollableViewportSize(new Dimension(650, 500));
        dataTable.setPreferredSize(new Dimension(650, 500));

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);
        gc.gridx = 0;
        gc.gridy = 0;
        add(new JScrollPane(dataTable), gc);

        message = new JLabel();
        gc.gridx = 0;
        gc.gridy = 1;
        add(message, gc);

        JButton checkoutButton = new JButton("Check out");
        checkoutButton.addActionListener(new BookCheckoutActionListener());
        gc.gridx = 1;
        gc.gridy = 1;
        add(checkoutButton, gc);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ReturnBookActionListener());
        gc.gridx = 1;
        gc.gridy = 2;
        add(returnButton, gc);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new RefreshBooksActionListener());
        gc.gridx = 1;
        gc.gridy = 3;
        add(refreshButton, gc);

        JButton logOffButton = new JButton("Log off");
        logOffButton.addActionListener(new LogOffActionListener(parentPanel, parentLayout));
        gc.gridx = 1;
        gc.gridy = 4;
        add(logOffButton, gc);
    }

    public void refreshBooks(String[][] bookData) {
        this.bookData = bookData;
        while (tableModel.getRowCount() > 0) {
            int lastRow = tableModel.getRowCount() - 1;
            tableModel.removeRow(lastRow);
        }
        for (int k = 0; k < this.bookData.length; k++) {
            tableModel.addRow(bookData[k]);
        }
    }

    public boolean isCheckedOut(String[] selectedRow) {
        return selectedRow[4].toLowerCase().equals("yes");
    }

    private class BookCheckoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowIndex = dataTable.getSelectedRow();
            if (selectedRowIndex > -1) {
                String[] selectedRow = bookData[selectedRowIndex];
                boolean isCheckedOut = isCheckedOut(selectedRow);
                if (!isCheckedOut) {
                    String isbn = selectedRow[3];
                    bookService.checkoutBook(isbn);
                    String[][] bookData = bookService.getBooksForTable();
                    refreshBooks(bookData);
                    message.setText("SUCCESS: Book has been checked out");
                    message.setForeground(Color.GREEN);
                } else {
                    message.setText("ERROR: Book is already checked out");
                    message.setForeground(Color.RED);
                }
            } else {
                message.setText("ERROR: Please select a book to check out");
                message.setForeground(Color.RED);
            }
        }
    }

    private class ReturnBookActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowIndex = dataTable.getSelectedRow();
            if (selectedRowIndex > -1) {
                String[] selectedRow = bookData[selectedRowIndex];
                boolean isCheckedOut = isCheckedOut(selectedRow);
                if (isCheckedOut) {
                    String isbn = selectedRow[3];
                    bookService.returnBook(isbn);
                    String[][] bookData = bookService.getBooksForTable();
                    refreshBooks(bookData);
                    message.setText("SUCCESS: Book has been returned");
                    message.setForeground(Color.GREEN);
                } else {
                    message.setText("ERROR: Book has already been returned");
                    message.setForeground(Color.RED);
                }
            } else {
                message.setText("ERROR: Please select a book to return");
                message.setForeground(Color.RED);
            }
        }
    }

    private class RefreshBooksActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshBooks(bookService.getBooksForTable());
        }
    }
}
