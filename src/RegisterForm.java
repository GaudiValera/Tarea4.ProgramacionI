import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterForm extends Form {
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField userField;
    private JTextField phoneField;
    private JTextField emailField;
    private JPasswordField passField;
    private JPasswordField confirmPassField;

    public RegisterForm() {
        super("Registro de Usuario", 600, 600);
    }

    @Override
    protected void initializeComponents() {
        JLabel titleLabel = new JLabel("REGISTRO", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(200, 20, 200, 30);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(150, 60, 300, 20);
        nameField = new JTextField();
        nameField.setBounds(150, 80, 300, 30);

        JLabel lastNameLabel = new JLabel("Apellido:");
        lastNameLabel.setBounds(150, 120, 300, 20);
        lastNameField = new JTextField();
        lastNameField.setBounds(150, 140, 300, 30);

        JLabel userLabel = new JLabel("Nombre de Usuario:");
        userLabel.setBounds(150, 180, 300, 20);
        userField = new JTextField();
        userField.setBounds(150, 200, 300, 30);

        JLabel phoneLabel = new JLabel("Número de Teléfono:");
        phoneLabel.setBounds(150, 240, 300, 20);
        phoneField = new JTextField();
        phoneField.setBounds(150, 260, 300, 30);

        JLabel emailLabel = new JLabel("Correo Electrónico:");
        emailLabel.setBounds(150, 300, 300, 20);
        emailField = new JTextField();
        emailField.setBounds(150, 320, 300, 30);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(150, 360, 300, 20);
        passField = new JPasswordField();
        passField.setBounds(150, 380, 300, 30);

        JLabel confirmPassLabel = new JLabel("Confirmar Contraseña:");
        confirmPassLabel.setBounds(150, 420, 300, 20);
        confirmPassField = new JPasswordField();
        confirmPassField.setBounds(150, 440, 300, 30);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(150, 480, 300, 30);

        registerButton.addActionListener(e -> {
            String nombre = nameField.getText();
            String apellido = lastNameField.getText();
            String username = userField.getText();
            String telefono = phoneField.getText();
            String correo = emailField.getText();
            String password = new String(passField.getPassword());
            String confirmPassword = new String(confirmPassField.getPassword());

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "El campo 'Nombre' es obligatorio.");
                return;
            }
            if (apellido.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "El campo 'Apellido' es obligatorio.");
                return;
            }
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "El campo 'Nombre de Usuario' es obligatorio.");
                return;
            }
            if (telefono.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "El campo 'Número de Teléfono' es obligatorio.");
                return;
            }
            if (correo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "El campo 'Correo Electrónico' es obligatorio.");
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "El campo 'Contraseña' es obligatorio.");
                return;
            }
            if (confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "El campo 'Confirmar Contraseña' es obligatorio.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden.");
                return;
            }

            try {
                DatabaseManager.registerUser(nombre, apellido, username, telefono, correo, password);
                JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente.");
                dispose();
                new LoginApp();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error al registrar: " + ex.getMessage());
            }
        });

        frame.add(titleLabel);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(lastNameLabel);
        frame.add(lastNameField);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(confirmPassLabel);
        frame.add(confirmPassField);
        frame.add(registerButton);

        show();
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getUserField() {
        return userField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPassField() {
        return passField;
    }

    public JPasswordField getConfirmPassField() {
        return confirmPassField;
    }
}