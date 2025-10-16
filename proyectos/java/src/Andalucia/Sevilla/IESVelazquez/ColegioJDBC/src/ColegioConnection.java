package Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ColegioConnection {

    // Cambia estos valores según tu entorno local
    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "Colegio"; // se crea vía SQL o desde Java
    private static final String USER = "root";
    private static final String PASSWORD = ""; // pon tu contraseña

    // Construye la URL de conexión MySQL moderna (MySQL 8+)
    private static String buildJdbcUrl(String host, int port, String database) {
        return "jdbc:mysql://" + host + ":" + port + "/" + database +
                "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    }

    public static Connection getConnection() throws SQLException {
        // Carga explícita del driver según PDF (aunque DriverManager lo hace automático
        // con JDBC 4, aquí lo forzamos para seguir la teoría)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver MySQL JDBC en el classpath", e);
        }

        String url = buildJdbcUrl(HOST, PORT, DATABASE);

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        // Ejemplos de propiedades útiles
        props.setProperty("autoReconnect", "true");
        props.setProperty("characterEncoding", "utf8");

        return DriverManager.getConnection(url, props);
    }

    // Getters públicos para evitar reflexión desde otras clases del mismo paquete
    public static String getHost() { return HOST; }
    public static int getPort() { return PORT; }
    public static String getUser() { return USER; }
    public static String getPassword() { return PASSWORD; }

     public static void main(String[] args) {
         try (Connection cn = getConnection()) {
             System.out.println("Conexión OK a MySQL (BD: " + DATABASE + ")");
         } catch (SQLException e) {
             SqlExceptionUtil.printSQLException(e);
         }
     }
}


