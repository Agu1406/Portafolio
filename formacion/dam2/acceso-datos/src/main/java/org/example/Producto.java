/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

/**
 *
 * @author Equipo
 */
public class Producto {

    int id_producto;
    String nombre;
    int id_categoria;
    String talla;
    String color;
    int stock;
    double precio;

    /**
     * Constructor de instancias (objetos) del tipo "Categoría.
     */
    public Producto(int id_producto, String nombre, int id_categoria, String talla, String color, int stock, double precio) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.id_categoria = id_categoria;
        this.talla = talla;
        this.color = color;
        this.stock = stock;
        this.precio = precio;
    }

    /**
     * Constructor de instancias (objetos) del tipo "Categoría.
     */
    public Producto(String nombre, int id_categoria, String talla, String color, int stock, double precio) {
        this.nombre = nombre;
        this.id_categoria = id_categoria;
        this.talla = talla;
        this.color = color;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Función que permite descontar del stock existente los
     * productos vendidos, si la cantidad que se desea vender
     * es superior al stock no descuenta nada y avisa con un
     * mensaje.
     * @param cantidad
     */
    public void vender(int cantidad) {
        if (cantidad <= stock) {
            stock -= cantidad;
            System.out.println("Venta realizada: " + cantidad + " unidades de " + nombre);
        } else {
            System.out.println("No hay suficiente stock de " + nombre);
        }
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id_producto=" + id_producto +
                ", nombre='" + nombre + '\'' +
                ", id_categoria=" + id_categoria +
                ", talla='" + talla + '\'' +
                ", color='" + color + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                '}';
    }
}
