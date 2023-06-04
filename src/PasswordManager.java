import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PasswordManager extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createButton;
    private JLabel userCountLabel;

    private Map<String, String> passwords;

    public PasswordManager() {
        setTitle("Password Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        add(usernameLabel);

        usernameField = new JTextField();
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        add(passwordLabel);

        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        add(loginButton);

        createButton = new JButton("Create Account");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });
        add(createButton);

        JLabel userCountTextLabel = new JLabel("Total Users:");
        add(userCountTextLabel);

        userCountLabel = new JLabel("0");
        add(userCountLabel);

        passwords = new HashMap<>();
        passwords.put("admin", "admin");

        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (passwords.containsKey(username)) {
            if (passwords.get(username).equals(password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                // Open password manager interface or perform any desired actions
            } else {
                JOptionPane.showMessageDialog(this, "Invalid password!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Username not found!");
        }
    }

    private void createAccount() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (!username.isEmpty() && !password.isEmpty()) {
            if (!passwords.containsKey(username)) {
                passwords.put(username, password);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                updateTotalUsers();
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a username and password!");
        }
    }

    private void updateTotalUsers() {
        int totalUsers = passwords.size() - 1; // Subtract 1 to exclude the "admin" account
        userCountLabel.setText(String.valueOf(totalUsers));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PasswordManager();
            }
        });
    }
}
