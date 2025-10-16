/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

/**
 *
 * @author Equipo
 */
import java.sql.*;
import java.util.ArrayList;

public class Inventario {

    private final Connection conexion;

    public Inventario() {
        // Utiliza la función que hemos creado para conectarse.
        this.conexion = ConexionBD.conectar();
    }


    private ArrayList<Producto> productos;


    /**
     * Función "C" de CRUD (Create): Permite dar de alta
     * y/o crear nuevos productos.
     *
     * @param producto
     */
    public void agregarProducto(Producto producto) {

        String SQL = "INSERT INTO productos (nombre, id_categoria, talla, color, stock, precio) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // Preparo la declaración que voy a ejecutar en la base de datos.
            PreparedStatement declaracion = conexion.prepareStatement(SQL);

            // Defino los valores que serán utilizados en la consulta.
            declaracion.setString(1, producto.getNombre());
            declaracion.setInt(2, producto.getId_categoria());
            declaracion.setString(3, producto.getTalla());
            declaracion.setString(4, producto.getColor());
            declaracion.setInt(5, producto.getStock());
            declaracion.setDouble(6, producto.getPrecio());

            // Con la declaración y sus valores definidos y listo, se ejecuta.
            int filasAfectadas = declaracion.executeUpdate();

            // Si al menos 1 fila ha sido afectada entonces ha salido bien.
            if (filasAfectadas > 0) {
                System.out.println("¡Producto agregado correctamente!");
            } else {
                System.out.println("¡Cero filas afectas! Algo no ha ido bien.");
            }

        } catch (SQLException e) {

            System.out.println("¡Error intentado agregar un producto!");
            System.out.println("Detalles del error: " + e.getMessage());
        }
    }

    /**
     * Función "R" de CRUD (Read): Permite leer u obtener
     * información de todos los productos disponibles.
     */
    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.* FROM productos p INNER JOIN categorias c ON p.id_categoria = c.id_categoria";

        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getInt("id_categoria"),
                        rs.getString("talla"),
                        rs.getString("color"),
                        rs.getInt("stock"),
                        rs.getDouble("precio")
                );
                lista.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error inesperado al listar los productos!");
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Función "R" de CRUD (Read): Permite leer u obtener
     * la información de un producto en especifico.
     *
     * @param nombre
     * @return
     */
    public Producto buscarProducto(String nombre) {
        String sql = "SELECT * FROM productos WHERE nombre = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getInt("id_categoria"),
                        rs.getString("talla"),
                        rs.getString("color"),
                        rs.getInt("stock"),
                        rs.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
        return null;
    }

    /**
     * Función D de CRUD permite: Eliminar o restar del stock la cantidad del
     * producto que hemos vendido.
     * @param nombre
     * @param cantidad
     */
    public void venderProducto(String nombre, int cantidad, Cliente cliente) {
        String sql = "UPDATE productos SET stock = stock - ? WHERE nombre = ? AND stock >= ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setString(2, nombre);
            ps.setInt(3, cantidad);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                registrarVenta(nombre, cantidad, cliente);
                System.out.println("Venta registrada de " + cantidad + " unidades de " + nombre);
            } else {
                System.out.println("Stock insuficiente o producto no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al vender: " + e.getMessage());
        }
    }

    /**
     * Función U de CRUD permite: Actualizar e insertar la venta que hemos realizado
     * de los productos.
     * @param nombre
     * @param cantidad
     * @throws SQLException
     */
    private void registrarVenta(String nombre, int cantidad, Cliente cliente) throws SQLException {
        String sql = "INSERT INTO ventas (id_producto, id_cliente, cantidad) " +
                "SELECT p.id_producto, ?, ? FROM productos p WHERE p.nombre = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cliente.getId_cliente());
            ps.setInt(2, cantidad);
            ps.setString(3, nombre);
            ps.executeUpdate();
        }
    }

    //Método auxiliar para obtener id_categoria desde el nombre.
    private int obtenerIdCategoria(String nombreCategoria) {
        String sql = "SELECT id_categoria FROM categorias WHERE nombre = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreCategoria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_categoria");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // por defecto asignar categoría 1 si no existe
    }
}
