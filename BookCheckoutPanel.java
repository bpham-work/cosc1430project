import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookCheckoutPanel extends JPanel {
    private JPanel parentPanel;
    private CardLayout parentLayout;
    private JTable dataTable;
    private BookService bookService;

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

        setLayout(new GridBagLayout());
        DefaultTableModel nonEditableTableModel = new DefaultTableModel(getBookData(), COLUMN_NAMES) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dataTable = new JTable(nonEditableTableModel);
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
        add(borrowButton, borrowButtonConstraints);

        JButton logOffButton = new JButton("Log off");
        logOffButton.addActionListener(new LogOffActionListener(parentPanel, parentLayout));
        add(logOffButton);
    }

    private Object[][] getBookData() {
        return bookService.getBooksForTable();
    }
}
