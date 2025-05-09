package Curso.UT1.Teoria;

import java.util.Scanner;
/***
 * Ejercicio N.º4 - Solicitar dos números por teclado usando Scanner
 * dividirlos y mostrar el resultado de la división por pantalla.
 */
public class DividirDosnumeros {
    public static void main(String[] args) {
        // Creamos un Scanner llamado "teclado" para recibir datos.
        Scanner teclado = new Scanner(System.in);
        // Creo las variables para guardar los números del usuario y el resultado de la suma.
        int dividiendo = 125;
        int divisor = 5;
        int resto;
        int cociente;

        cociente = dividiendo / divisor;
        resto = dividiendo % divisor;

        System.out.println("El resto de 125 entre 5 es: " + resto);
        System.out.println("El cociente de 125 entre 5 es: " + cociente);

    }
}
