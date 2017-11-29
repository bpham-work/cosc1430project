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
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        JLabel banner = new JLabel("Login as a staff member:");
        gc.gridx = 0;
        gc.gridy = 0;
        add(banner, gc);

        loginErrorMessage = new JLabel("Incorrect login");
        loginErrorMessage.setForeground(Color.RED);
        loginErrorMessage.setVisible(false);
        gc.gridx = 0;
        gc.gridy = 1;
        add(loginErrorMessage);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField("", 20);
        gc.gridx = 0;
        gc.gridy = 2;
        add(usernameLabel, gc);
        gc.gridx = 1;
        add(usernameField, gc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField("", 20);
        gc.gridx = 0;
        gc.gridy = 3;
        add(passwordLabel, gc);
        gc.gridx = 1;
        add(passwordField, gc);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginActionListener());
        gc.gridx = 2;
        gc.gridy = 4;
        add(loginButton, gc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new LogOffActionListener(parentPanel, parentLayout));
        gc.gridx = 3;
        gc.gridy = 4;
        add(backButton, gc);
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
