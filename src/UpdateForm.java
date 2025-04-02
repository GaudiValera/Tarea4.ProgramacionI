import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UpdateForm extends Form {
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private String username;
    private UserDashboard dashboard;

    public UpdateForm(String username, UserDashboard dashboard) {
        super("Actualizar Usuario", 600, 400);
        this.username = username;
        this.dashboard = dashboard;
    }

    @Override
    protected void initializeComponents() {
        JLabel titleLabel = new JLabel("ACTUALIZAR USUARIO", SwingConstants.CENTER);
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

        JLabel phoneLabel = new JLabel("Número de Teléfono:");
        phoneLabel.setBounds(150, 180, 300, 20);
        phoneField = new JTextField();
        phoneField.setBounds(150, 200, 300, 30);

        JLabel emailLabel = new JLabel("Correo Electrónico:");
        emailLabel.setBounds(150, 240, 300, 20);
        emailField = new JTextField();
        emailField.setBounds(150, 260, 300, 30);

        JButton updateButton = new JButton("Guardar Cambios");
        updateButton.setBounds(150, 300, 300, 30);

        try {
            ResultSet rs = DatabaseManager.getUserByUsername(username);
            if (rs.next()) {
                nameField.setText(rs.getString("nombre"));
                lastNameField.setText(rs.getString("apellido"));
                phoneField.setText(rs.getString("telefono"));
                emailField.setText(rs.getString("correo"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error al cargar datos: " + ex.getMessage());
        }

        updateButton.addActionListener(e -> {
            String nombre = nameField.getText();
            String apellido = lastNameField.getText();
            String telefono = phoneField.getText();
            String correo = emailField.getText();

            if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.");
                return;
            }

            try {
                DatabaseManager.updateUser(username, nombre, apellido, telefono, correo);
                JOptionPane.showMessageDialog(frame, "Usuario actualizado.");
                dashboard.loadUsers();
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error al actualizar: " + ex.getMessage());
            }
        });

        frame.add(titleLabel);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(lastNameLabel);
        frame.add(lastNameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(updateButton);

        show();
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public String getUsername() {
        return username;
    }

    public UserDashboard getDashboard() {
        return dashboard;
    }
}