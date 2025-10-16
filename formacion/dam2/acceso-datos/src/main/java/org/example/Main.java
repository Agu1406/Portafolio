/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.example;

/**
 *
 * @author Equipo
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Inventario inventario = new Inventario();
        ConexionBD conexion = new ConexionBD();
        int opcion;

        do {
            System.out.println("GESTION DE INVENTARIO");
            System.out.println("1. Agregar producto.");
            System.out.println("2. Listar producto.");
            System.out.println("3. Vender producto.");
            System.out.println("4. Buscar producto.");
            System.out.println("0. Salir");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    // Consumir el salto de línea pendiente antes de leer Strings.
                    teclado.nextLine();
                    agregarProducto(teclado, inventario);
                    break;
                case 2:
                    // Consumir el salto de linea pendiente antes de leer Strings.
                    mostrarProducto();
                    break;
                case 3:
                    System.out.println("Nombre del producto: ");
                    teclado.nextLine(); // consumir salto pendiente
                    String vender = teclado.nextLine();
                    Producto producto = inventario.buscarProducto(vender);
                        if(producto != null){
                            System.out.println("Cantidad vendida correctamente. ");
                            int cantidad = teclado.nextInt();
                            producto.vender(cantidad);
                        }else {
                            System.out.println("!Error! no se ha podido vender el producto.");
                        }
                        break;
                case 4:
                    System.out.println("Nombre del producto: ");
                    teclado.nextLine(); // consumir salto pendiente
                    String buscar = teclado.nextLine();
                    Producto producto1 = inventario.buscarProducto(buscar);
                    if(producto1 != null){
                        System.out.println("Aquí le muestro el producto: " +  producto1.getNombre());
                    }else {
                        System.out.println("El producto no existe");
                    }
                case 0:
                    System.out.println("Salir");
                default:
                    System.out.println("Opcion no valida.");
            }
        }while(opcion!=0);
    }

    private static void agregarProducto(Scanner teclado, Inventario inventario) {
        System.out.println("Nombre del producto: ");
        String nombre = teclado.nextLine();
        while (nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacío. Introdúcelo de nuevo: ");
            nombre = teclado.nextLine();
        }
        System.out.println("ID de categoría: ");
        int idCategoria =  teclado.nextInt();

        /**
         * Limpiamos el buffer para eliminar el número que escribimos antes
         * y que no de error en el siguiente paso de escribír texto.
         */
        teclado.nextLine();

        System.out.println("Talla del producto: ");
        String talla = teclado.nextLine();

        System.out.println("Color del producto: ");
        String color = teclado.nextLine();

        System.out.println("Stock del producto: ");
        int stock = teclado.nextInt();

        System.out.println("Precio del producto: ");
        double precio = teclado.nextDouble();

        Producto nuevoProducto = new Producto(nombre, idCategoria, talla, color, stock, precio);

        inventario.agregarProducto(nuevoProducto);
    }

    private static void mostrarProducto() {

        
    }
}