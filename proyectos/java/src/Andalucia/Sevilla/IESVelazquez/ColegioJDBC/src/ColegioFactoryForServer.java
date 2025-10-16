package Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src;

public class ColegioFactoryForServer {
    // Reutilizamos los mismos HOST/PORT/USER/PASS de ColegioConnection mediante reflexión simple.
    // Aquí reconstruimos la URL sin base de datos.
    public static java.sql.Connection getConnectionWithoutDatabase() throws java.sql.SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new java.sql.SQLException("Driver MySQL no disponible", e);
        }

        String host = ColegioConnection.getHost();
        int port = ColegioConnection.getPort();
        String user = ColegioConnection.getUser();
        String password = ColegioConnection.getPassword();

        String url = "jdbc:mysql://" + host + ":" + port +
                "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        java.util.Properties props = new java.util.Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("autoReconnect", "true");
        props.setProperty("characterEncoding", "utf8");

        return java.sql.DriverManager.getConnection(url, props);
    }

    // Eliminados métodos de reflexión: usamos getters públicos en ColegioConnection
}
