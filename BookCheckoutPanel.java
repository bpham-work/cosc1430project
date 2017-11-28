import javax.swing.*;
import java.awt.*;

public class BookCheckoutPanel extends JPanel {
    private JPanel parentPanel;
    private CardLayout parentLayout;

    private final String[] COLUMN_NAMES = {
            "Book Title",
            "Author",
            "Pub/Rel Date",
            "ISBN-10",
            "Checked Out?"
    };

    private final String[][] mockData = {
            {"bitch book", "bitch", "ay", "sd", "yes"}
    };

    public BookCheckoutPanel(JPanel parentPanel, CardLayout parentLayout) {
        super();
        this.parentPanel = parentPanel;
        this.parentLayout = parentLayout;
        JTable dataTable = new JTable(mockData, COLUMN_NAMES);
        add(dataTable);
    }
}
