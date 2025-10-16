/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

/**
 *
 * @author Equipo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String url = "jdbc:mysql://localhost:3306/zaradb";
    private static final String usuario = "root";
    private static final String password = "";

    public static Connection conectar() {
           Connection conexion = null;
        try {
            // Llamamos a la clase/driver que utilizaremos para la conexión.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Intentamos conectarlos a la base de datos con los datos que tenemos.
            conexion = DriverManager.getConnection(url, usuario, password);

            // Avisamos al usuario que todo ha ido bien.
            System.out.println("¡Conexión a la base de datos exitosa!");
        } // Si el "driver" falla salta está excepción.
        catch (ClassNotFoundException e) {
            System.out.println("¡Error! No se pudo cargar la clase/driver.");
        } // Si la conexión falla, por ejemplo, por contrasña erronea, salta está excepción.
        catch (SQLException e) {
            System.out.println("¡Error! No se ha podido conectar a la base de datos.");
            System.out.println("Detalles del error: " + e.getMessage());
        } // Si no es ninguna de las anteriores, salta está excepción.
        catch (Exception e) {
            System.out.println("¡Error! Ha ocurrido la siguiente excepción: " + e.getMessage());
        }

        /**
         * Indifirentemente de si la conexión sale bien o no, hay que
         * devolverla. Si ocurre un error la conexión seguira siendo "null",
         * pero si sale bien devolvera una conexión correcta.
         */
        return conexion;
    }

    /**
     * Función utilizada para cerrar una conexión existente que no sea nulla
     * (null) a la base de datos.
     *
     * @param conexion
     */
    public static void cerrarConexion(Connection conexion) {
        // Solo se puede cerrar una conexión si realmente existe (no es "null").
        if (conexion != null) {
            /**
             * Intentamos cerrar la conexión a la base de datos, si ocurre algún
             * fallo atrapamos la excepción SQL y la imprimimos.
             */
            try {
                conexion.close();
                System.out.println("¡La conexión ha finalizado!");
            } catch (SQLException e) {

                System.out.println("¡Error cerrando la conexión!");
                System.out.println("Detalles del error: " + e.getMessage());
            }
        }
    }
}
