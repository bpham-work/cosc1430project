import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout parentLayout = new CardLayout();
    private final JPanel parentPanel = new JPanel(parentLayout);

    public MainFrame() {
        UserSelectPanel userSelectPanel = new UserSelectPanel(parentPanel, parentLayout);
        parentPanel.add(userSelectPanel, LayoutNameConstants.USER_SELECT);

        StaffLoginPanel staffLoginPanel = new StaffLoginPanel(parentPanel, parentLayout);
        parentPanel.add(staffLoginPanel, LayoutNameConstants.STAFF_LOGIN);

        BookCheckoutPanel bookCheckoutPanel = new BookCheckoutPanel(parentPanel, parentLayout);
        parentPanel.add(bookCheckoutPanel, LayoutNameConstants.BOOK_CHECKOUT);

        add(parentPanel);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}