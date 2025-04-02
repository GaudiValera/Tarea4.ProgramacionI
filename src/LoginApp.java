import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginApp extends Form {
    private JTextField userField;
    private JPasswordField passField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginApp::new);
    }

    public LoginApp() {
        super("Login de Usuario", 600, 400);
    }

    @Override
    protected void initializeComponents() {
        JLabel titleLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(200, 50, 200, 30);

        JLabel userLabel = new JLabel("Nombre de Usuario:");
        userLabel.setBounds(150, 100, 300, 20);
        userField = new JTextField();
        userField.setBounds(150, 120, 300, 30);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(150, 160, 300, 20);
        passField = new JPasswordField();
        passField.setBounds(150, 180, 300, 30);

        JButton loginButton = new JButton("Entrar");
        loginButton.setBounds(150, 220, 300, 30);

        JButton registerButton = new JButton("Registrarse");
        registerButton.setBounds(250, 260, 100, 30);

        registerButton.addActionListener(e -> {
            new RegisterForm();
            dispose();
        });

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse.");
            } else {
                try {
                    if (DatabaseManager.validateUser(username, password)) {
                        new UserDashboard();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error de conexión: " + ex.getMessage());
                }
            }
        });

        frame.add(titleLabel);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginButton);
        frame.add(registerButton);

        show();
    }

    public JTextField getUserField() {
        return userField;
    }

    public JPasswordField getPassField() {
        return passField;
    }
}