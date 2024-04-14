package UT3EjerciciosConBucles;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio3</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario dos números y muestra todos los números pares que se encuentran
 * entre ellos, utilizando un bucle while para iterar a través del rango. La lógica para determinar el
 * número menor y mayor se realiza mediante estructuras condicionales if-else, sin utilizar funciones matemáticas.
 * </p><br>
 *
 * <h2 style="text-align: center;">Descripción del Ejercicio</h2><br>
 * <p style="text-align: justify;">
 * 3- Escribir un programa que imprima todos los números pares entre dos números que se le
 * pidan al usuario.
 * </p><br>
 *
 * @version 1.0
 * @since 10/10/2023
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el primer número: ");
        int numero1 = scanner.nextInt();  // Primer número introducido por el usuario.
        System.out.print("Introduce el segundo número: ");
        int numero2 = scanner.nextInt();  // Segundo número introducido por el usuario.

        int inicio;  // Marca el inicio del rango para imprimir números pares.
        int fin;    // Marca el fin del rango para imprimir números pares.

        // Determinando cuál número es menor y cuál es mayor.
        if (numero1 < numero2) {
            inicio = numero1;
            fin = numero2;
        } else {
            inicio = numero2;
            fin = numero1;
        }

        // Ajuste para que el inicio sea un número par.
        if (inicio % 2 != 0) {
            inicio++;
        }

        System.out.println("Números pares entre " + numero1 + " y " + numero2 + ":");
        while (inicio <= fin) {
            System.out.print(inicio + " ");
            inicio += 2;  // Incrementa en 2 para seguir imprimiendo solo números pares.
        }
        System.out.println(); // Salto de línea para finalizar la salida
    }
}
