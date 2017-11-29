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
        GridBagConstraints dataTableConstraints = new GridBagConstraints();
        dataTableConstraints.fill = GridBagConstraints.HORIZONTAL;
        dataTableConstraints.gridx = 0;
        dataTableConstraints.gridy = 0;
        add(new JScrollPane(dataTable), dataTableConstraints);

        JButton borrowButton = new JButton("Borrow");
        GridBagConstraints borrowButtonConstraints = new GridBagConstraints();
        borrowButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        borrowButtonConstraints.gridx = 0;
        borrowButtonConstraints.gridwidth = 1;
        borrowButtonConstraints.anchor = GridBagConstraints.PAGE_END;
        borrowButtonConstraints.gridy = 2;
        borrowButton.addActionListener(new BookCheckoutActionListener());
        add(borrowButton, borrowButtonConstraints);

        JButton logOffButton = new JButton("Log off");
        logOffButton.addActionListener(new LogOffActionListener(parentPanel, parentLayout));
        add(logOffButton);
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
}
