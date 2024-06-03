import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton adminLoginButton;
    private JButton userLoginButton;

    public LoginFrame() {
        setTitle("Movie Ticket Booking System - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 160, 25);
        panel.add(passwordField);

        adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setBounds(10, 80, 120, 25);
        panel.add(adminLoginButton);

        userLoginButton = new JButton("User Login");
        userLoginButton.setBounds(140, 80, 120, 25);
        panel.add(userLoginButton);

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (authenticateAdmin(username, password)) {
                    AdminFrame adminFrame = new AdminFrame();
                    adminFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid admin credentials");
                }
            }
        });

        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (authenticateUser(username, password)) {
                    dispose();
                    UserFrame userFrame = new UserFrame();
                    userFrame.setVisible(true);
                    //dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid user credentials");
                }
            }
        });
    }

    private boolean authenticateAdmin(String username, String password) {
        // Placeholder for actual authentication logic
        return username.equals("admin") && password.equals("admin1234");
    }

    private boolean authenticateUser(String username, String password) {
        // Placeholder for actual authentication logic
        return username.equals("user") && password.equals("user123");
    }
}
