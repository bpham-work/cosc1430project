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
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        JLabel banner1 = new JLabel("Library Management System");
        JLabel banner2 = new JLabel("Select your role to proceed");
        gc.gridx = 1;
        gc.gridy = 0;
        add(banner1, gc);
        gc.gridy = 1;
        add(banner2, gc);

        JButton studentButton = new JButton("Student");
        studentButton.addActionListener(new NavigateToBookCheckoutPanel());
        JButton staffButton = new JButton("Staff");
        staffButton.addActionListener(new NavigateToStaffLoginPanel());
        gc.gridx = 0;
        gc.gridy = 2;
        add(studentButton, gc);
        gc.gridx = 2;
        add(staffButton, gc);
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
