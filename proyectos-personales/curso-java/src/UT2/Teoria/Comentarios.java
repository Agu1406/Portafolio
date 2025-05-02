package UT2.Teoria;

import java.util.Scanner;

/***
 * La clase "Comentarios" se utiliza para explicar los diferentes tipos de comentarios
 * que existen en Java.
 */
public class Comentarios {
    /***
     * Utilizamos la función "main" para dar forma y ejemplos al código.
     */
    public static void main(String[] args) {
        // Creamos un Scanner llamado "teclado" para recibir datos.
        Scanner teclado = new Scanner(System.in);
        // Creamos una variable para guardar el primer número.
        int numero1;
        // Creamos una variable para guardar el segundo número.
        int numero2;
        // Creamos una variable para guardar el resultado.
        int resultado;

        /*
        * Solicito al usuario un número por consola, imprimo
        * un mensaje agradable y luego uso "Scanner" para
        * guardar el número que escriba.
        * */
        System.out.println("¡Hola! Dime un número.");
        numero1 = teclado.nextInt();
    }
}
