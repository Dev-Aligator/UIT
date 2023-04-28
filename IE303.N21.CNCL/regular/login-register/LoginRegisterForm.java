import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LoginRegisterForm extends JFrame implements ActionListener {
    private JTextField userText;
    private JPasswordField passText;
    private JButton loginButton, registerButton;

    public LoginRegisterForm() {
        super("Login/Register Form");

        // Create the components
        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        userText = new JTextField(20);
        passText = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Set the font
        Font font = new Font("Arial", Font.PLAIN, 14);
        userLabel.setFont(font);
        passLabel.setFont(font);
        userText.setFont(font);
        passText.setFont(font);
        loginButton.setFont(font);
        registerButton.setFont(font);

        // Set the colors
        Color backgroundColor = new Color(242, 242, 242);
        Color foregroundColor = new Color(51, 51, 51);
        getContentPane().setBackground(backgroundColor);
        userLabel.setForeground(foregroundColor);
        passLabel.setForeground(foregroundColor);
        userText.setForeground(foregroundColor);
        passText.setForeground(foregroundColor);
        loginButton.setForeground(backgroundColor);
        loginButton.setBackground(foregroundColor);
        registerButton.setForeground(backgroundColor);
        registerButton.setBackground(foregroundColor);

        // Set the layout
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(backgroundColor);
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passLabel);
        panel.add(passText);
        panel.add(loginButton);
        panel.add(registerButton);
        add(panel);

        // Add the action listeners
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        // Set the size and visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginRegisterForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            if (checkLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        } else if (e.getSource() == registerButton) {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            if (registerUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration successful");
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists");
            }
        }
    }

    private boolean checkLogin(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean registerUser(String username, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
            // Check if the username already exists
            if (checkLogin(username, password)) {
                return false;
            }
            // Write the new user to the file
            bw.write(username + ":" + password);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

