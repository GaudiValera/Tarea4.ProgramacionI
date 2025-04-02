import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class UserDashboard {
    private JFrame frame;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserDashboard() {
        frame = new JFrame("Lista de Usuarios");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"Usuario", "Nombre", "Apellido", "Teléfono", "Correo"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);

        JButton logoutButton = new JButton("Cerrar Sesión");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Eliminar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        loadUsers();

        logoutButton.addActionListener(e -> {
            frame.dispose();
            new LoginApp();
        });

        updateButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                String username = (String) tableModel.getValueAt(selectedRow, 0);
                new UpdateForm(username, this);
            } else {
                JOptionPane.showMessageDialog(frame, "Seleccione un usuario para actualizar.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                String username = (String) tableModel.getValueAt(selectedRow, 0);
                try {
                    DatabaseManager.deleteUser(username);
                    loadUsers();
                    JOptionPane.showMessageDialog(frame, "Usuario eliminado.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al eliminar: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Seleccione un usuario para eliminar.");
            }
        });

        frame.setVisible(true);
    }

    public void loadUsers() {
        tableModel.setRowCount(0);
        try {
            ResultSet rs = DatabaseManager.getAllUsers();
            while (rs.next()) {
                Object[] row = {
                        rs.getString("nombre_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error al cargar usuarios: " + ex.getMessage());
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTable getUserTable() {
        return userTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}