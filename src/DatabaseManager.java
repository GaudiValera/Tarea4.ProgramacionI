import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/login_app_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean validateUser(String username, String password) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public static void registerUser(String nombre, String apellido, String username, String telefono, String correo, String password) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO usuarios (nombre, apellido, nombre_usuario, telefono, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, username);
            stmt.setString(4, telefono);
            stmt.setString(5, correo);
            stmt.setString(6, password);
            stmt.executeUpdate();
        }
    }

    public static void deleteUser(String username) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM usuarios WHERE nombre_usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
    }

    public static void updateUser(String username, String nombre, String apellido, String telefono, String correo) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, telefono = ?, correo = ? WHERE nombre_usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, telefono);
            stmt.setString(4, correo);
            stmt.setString(5, username);
            stmt.executeUpdate();
        }
    }

    public static ResultSet getUserByUsername(String username) throws SQLException {
        Connection conn = getConnection();
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        return stmt.executeQuery();
    }

    public static ResultSet getAllUsers() throws SQLException {
        Connection conn = getConnection();
        String sql = "SELECT nombre_usuario, nombre, apellido, telefono, correo FROM usuarios";
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }
}