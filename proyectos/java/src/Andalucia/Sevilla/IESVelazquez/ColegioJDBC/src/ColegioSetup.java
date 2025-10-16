package Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ColegioSetup {

    public static void main(String[] args) {
        // Crea la BD y la tabla desde Java usando Statement, siguiendo el PDF
        String createDb = "CREATE DATABASE IF NOT EXISTS Colegio" +
                " DEFAULT CHARACTER SET utf8mb4" +
                " DEFAULT COLLATE utf8mb4_general_ci";

        String createTable = "CREATE TABLE IF NOT EXISTS Alumnos (" +
                " id_alumno INT AUTO_INCREMENT PRIMARY KEY," +
                " nombre VARCHAR(100) NOT NULL," +
                " correo VARCHAR(150) NOT NULL UNIQUE," +
                " telefono VARCHAR(20) NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci";

        // Para crear la BD debemos conectar al servidor sin especificar BD
        try (Connection rootConn = ColegioFactoryForServer.getConnectionWithoutDatabase()) {
            try (Statement st = rootConn.createStatement()) {
                st.executeUpdate(createDb);
                System.out.println("BD Colegio creada/verificada");
            }
         } catch (SQLException e) {
             SqlExceptionUtil.printSQLException(e);
            return;
        }

        // Ahora conectar ya a la BD Colegio y crear la tabla
        try (Connection cn = ColegioConnection.getConnection();
             Statement st = cn.createStatement()) {
            st.executeUpdate(createTable);
            System.out.println("Tabla Alumnos creada/verificada");
        } catch (SQLException e) {
            SqlExceptionUtil.printSQLException(e);
        }
    }
}
