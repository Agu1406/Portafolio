package Curso.UT1.Teoria;

import java.util.Scanner;
/***
 * Ejercicio N.º3 - Solicitar dos números por teclado usando Scanner
 * multiplicarlos y mostrar el resultado de la multiplicación por pantalla.
 */
public class MultiplicarDosNumeros {
    public static void main(String[] args) {
        // Creamos un Scanner llamado "teclado" para recibir datos.
        Scanner teclado = new Scanner(System.in);
        // Creo las variables para guardar los números del usuario y el resultado de la suma.
        int numero1, numero2, resultado;

        // Pido por pantalla y por teclado el primer número.
        System.out.println("Dime un número.");
        numero1 = teclado.nextInt();

        // Pido por pantalla y por teclado el segundo número.
        System.out.println("Dime otro número");
        numero2 = teclado.nextInt();

        // Realizo la operación y muestro el resultado por pantalla.
        resultado = numero1 * numero2;
        System.out.println("El resultado de la suma es: " + resultado);

    }
}
