import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSelectPanel extends JPanel {
    private JPanel parentPanel;
    private CardLayout parentLayout;

    public UserSelectPanel(JPanel parentPanel, CardLayout parentLayout) {
        super();
        this.parentPanel = parentPanel;
        this.parentLayout = parentLayout;
        JButton studentButton = new JButton("Student");
        studentButton.addActionListener(new NavigateToBookCheckoutPanel());
        JButton staffButton = new JButton("Staff");
        staffButton.addActionListener(new NavigateToStaffLoginPanel());
        add(studentButton);
        add(staffButton);
    }

    private class NavigateToBookCheckoutPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parentLayout.show(parentPanel, LayoutNameConstants.BOOK_CHECKOUT);
        }
    }

    private class NavigateToStaffLoginPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parentLayout.show(parentPanel, LayoutNameConstants.STAFF_LOGIN);
        }
    }
}
