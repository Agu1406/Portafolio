package ENTREGAS;

import java.util.Scanner;

/***
 * Ejercicio N.5 del UT1 - Programa que pide 5 veces al usuario un número
 * y multiplica consecuentemente esos números.
 */
public class Multiplicar5NumerosConsecutivos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int numero, resultado = 1;

        // Bucle que se repite "5" veces (pide un número)
        for (int iteracion = 0; iteracion < 5; iteracion++) {
            // Pido por pantalla y por teclado un número.
            System.out.print("Dime un número: ");
            numero = teclado.nextInt();

            // Multiplico de forma consecutiva los números.
            resultado = resultado * numero;
        }
        // Imprimo el resultado de la multiplicación consecutiva.
        System.out.println("El resultado es: " + resultado);




    }
}//Fin del programa

