package org.example;

public class Producto {

    String nombre;
    String Categoria;
    String talla;
    String color;
    int stock;
    double precio;

    public Producto(String nombre, String categoria, String talla, String color, int stock, double precio) {
        this.nombre = nombre;
        Categoria = categoria;
        this.talla = talla;
        this.color = color;
        this.stock = stock;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int  getStock() {
        return stock;
    }

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
        return "Producto: " +
                "nombre: " + nombre + '\'' +
                ", Categoria: " + Categoria + '\'' +
                ", talla: " + talla + '\'' +
                ", color: " + color + '\'' +
                ", stock: " + stock + '\'' +
                ", precio: " + precio ;
    }
}
