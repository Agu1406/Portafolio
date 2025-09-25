package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Inventario inventario = new Inventario();
        int opcion;

        do {
            System.out.println("GESTION DE INVENTARIO");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar producto");
            System.out.println("3. Vender producto");
            System.out.println("0. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Nombre del producto: ");
                    String nombre = sc.nextLine();
                    System.out.println("Categoria del producto: ");
                    String categoria = sc.nextLine();
                    System.out.println("Talla del producto: ");
                    String talla = sc.nextLine();
                    System.out.println("Color del producto: ");
                    String color = sc.next();
                    System.out.println("Stock del producto: ");
                    int stock = sc.nextInt();
                    System.out.println("Precio del producto: ");
                    double precio = sc.nextDouble();
                    Producto p = new Producto(nombre, categoria, talla, color, stock, precio);
                    inventario.agregarProducto(p);
                    break;
                case 2:
                    inventario.listarProductos();
                    break;
                case 3:
                    System.out.println("Nombre del producto: ");
                    String buscar = sc.nextLine();
                    Producto producto = inventario.buscarProducto(buscar);
                    if(producto != null){
                        System.out.println("Cantidad vendida correctamente. ");
                        int cantidad = sc.nextInt();
                        producto.vender(cantidad);
                    }else {
                        System.out.println("El producto precio no existe");
                    }
                    break;
                case 0:
                    System.out.println("Salir");
                default:
                    System.out.println("Opcion no valida.");
            }
        }while(opcion!=0);
    }
}