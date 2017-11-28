import javax.swing.*;
import java.awt.*;

public class StaffLoginPanel extends JPanel {
    private JPanel parentPanel;
    private CardLayout parentLayout;

    public StaffLoginPanel(JPanel parentPanel, CardLayout parentLayout) {
        super();
        this.parentPanel = parentPanel;
        this.parentLayout = parentLayout;
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton loginButton = new JButton("Login");
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField("", 20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField("", 20);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
    }
}
