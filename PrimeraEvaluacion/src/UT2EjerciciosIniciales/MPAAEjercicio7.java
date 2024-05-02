package UT2EjerciciosIniciales;

import java.util.Scanner;

/**
 * Ejercicio N.º 7 - Operadores relacionales.
 * Este programa pide al usuario que introduzca dos números y luego compara estos números
 * utilizando operadores relacionales, mostrando los resultados de las comparaciones.
 *
 * @author Agu1406 (Agustín)
 * @since 06/10/2023
 */
public class MPAAEjercicio7 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int x, y; // Variables para almacenar los números introducidos por el usuario.
        boolean resultado; // Variable para almacenar el resultado de las comparaciones.

        // Solicita al usuario el primer número y lo almacena en "x".
        System.out.print("Introducir primer número: ");
        x = teclado.nextInt();

        // Solicita al usuario el segundo número y lo almacena en "y".
        System.out.print("Introducir segundo número: ");
        y = teclado.nextInt();

        // Compara si "x" es diferente de "y" y muestra el resultado.
        resultado = (x != y);
        System.out.println("x != y es " + resultado);

        // Compara si "x" es menor que "y" y muestra el resultado.
        resultado = (x < y);
        System.out.println("x < y es " + resultado);

        // Compara si "x" es menor o igual que "y" y muestra el resultado.
        resultado = (x <= y);
        System.out.println("x <= y es " + resultado);

        // Compara si "x" es mayor que "y" y muestra el resultado.
        resultado = (x > y);
        System.out.println("x > y es " + resultado);

        // Compara si "x" es mayor o igual que "y" y muestra el resultado.
        resultado = (x >= y);
        System.out.println("x >= y es " + resultado);
    }
}
