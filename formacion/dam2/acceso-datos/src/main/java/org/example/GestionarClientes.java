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
import java.util.List;

public class GestionarClientes {
    private Connection conexion;

    public GestionarClientes(Connection conexion) {
        this.conexion = ConexionBD.conectar();
    }

    //Metodo para insertar clientes en la base de datos.
    public boolean agregarCliente(Cliente cliente){
        String sql = "INSERT INTO clientes (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = conexion.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, cliente.getNombre());
            ps.setString(1, cliente.getEmail());
            ps.setString(1, cliente.getTelefono());
            ps.setString(1, cliente.getDireccion());

            int filas =  ps.executeUpdate();
            if(filas > 0){
                ResultSet rs = ps.getGeneratedKeys();

                if(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Cliente agregado con id = " + id);
                }
                return true;
            }
        }catch (SQLException e){
            System.out.println("Error al insertar el cliente" + e.getMessage());
        }
        return false;
    }
    public Cliente buscarClienteEmail(String email){
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";

        try(PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                );
            }
        }catch (SQLException e){
            System.out.println("Error al buscar el cliente" + e.getMessage());
        }
        return null;
    }

    //Metodo para listar todos los clientes
    public List<Cliente> listarClientes(){
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try(Statement st = conexion.createStatement()){
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                );
                listaClientes.add(cliente);
            }
        }catch (SQLException e){
            System.out.println("Error al listar todos los clientes" + e.getMessage());
        }
        return listaClientes;
    }
}
