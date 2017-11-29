import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffLoginPanel extends JPanel {
    private JPanel parentPanel;
    private CardLayout parentLayout;

    private JTextField usernameField;
    private JLabel loginErrorMessage;
    private JPasswordField passwordField;

    private StaffAuthenticator staffAuthenticator;

    public StaffLoginPanel(JPanel parentPanel, CardLayout parentLayout) {
        super();
        this.parentPanel = parentPanel;
        this.parentLayout = parentLayout;
        this.staffAuthenticator = StaffAuthenticator.getInstance();

        loginErrorMessage = new JLabel("Incorrect login");
        loginErrorMessage.setBackground(Color.RED);
        loginErrorMessage.setVisible(false);
        add(loginErrorMessage);

//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField("", 20);
        add(usernameLabel);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField("", 20);
        add(passwordLabel);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginActionListener());
        add(loginButton);
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText().toLowerCase();
            String password = new String(passwordField.getPassword());
            boolean isAuthenticated = staffAuthenticator.authenticate(username, password);
            if (isAuthenticated) {
                parentLayout.show(parentPanel, LayoutNameConstants.ADD_BOOK);
            } else {
                loginErrorMessage.setVisible(true);
            }
        }
    }
}
