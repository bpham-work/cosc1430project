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

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        add(new JScrollPane(dataTable), gc);

        JButton borrowButton = new JButton("Borrow");
        borrowButton.addActionListener(new BookCheckoutActionListener());
        gc.gridx = 1;
        gc.gridy = 0;
        add(borrowButton, gc);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ReturnBookActionListener());
        gc.gridx = 1;
        gc.gridy = 1;
        add(returnButton, gc);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new RefreshBooksActionListener());
        gc.gridx = 1;
        gc.gridy = 2;
        add(refreshButton, gc);

        JButton logOffButton = new JButton("Log off");
        logOffButton.addActionListener(new LogOffActionListener(parentPanel, parentLayout));
        gc.gridx = 1;
        gc.gridy = 3;
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

    private class BookCheckoutActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowIndex = dataTable.getSelectedRow();
            String[] selectedRow = bookData[selectedRowIndex];
            String isbn = selectedRow[3];
            bookService.checkoutBook(isbn);
            String[][] bookData = bookService.getBooksForTable();
            refreshBooks(bookData);
        }
    }

    private class ReturnBookActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowIndex = dataTable.getSelectedRow();
            String[] selectedRow = bookData[selectedRowIndex];
            String isbn = selectedRow[3];
            bookService.returnBook(isbn);
            String[][] bookData = bookService.getBooksForTable();
            refreshBooks(bookData);
        }
    }

    private class RefreshBooksActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshBooks(bookService.getBooksForTable());
        }
    }
}
