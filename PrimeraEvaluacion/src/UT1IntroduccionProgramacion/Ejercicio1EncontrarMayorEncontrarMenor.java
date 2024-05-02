package UT1IntroduccionProgramacion;

import java.util.Scanner;
/**
 *  El primer programa que hice jamás en Java, en su momento, era toda una odisea
 *  para mi, hoy en día me llena de orgullo recordar mis inicios, el ejercicio
 *  era:
 *
 *  - Encontrar el número mayor y menor de dos solicitados por pantalla
 * @author Agu1406 (Agustín)
 * @since 22/09/2023
 */
public class Ejercicio1EncontrarMayorEncontrarMenor {
    public static void main(String[] args) {
        // Activo un Scanner de nombre "teclado", que me permite ingresar datos desde el teclado.
        Scanner teclado = new Scanner(System.in);

        // Creamos las variables, los dos numeros que ingresara el usuario
        int numero1;
        int numero2;

        // Mostramos en pantalla un mensaje solicitando el primer numero
        System.out.println("¡Hola! Introduce el primer número.");
        // Solicitamos usando el teclado el numero2 y establecemos su valor
        numero1 = teclado.nextInt();

        // Mostramos otro mensaje ahora solicitando el siguiente numero
        System.out.println("Hala majete, dime el otro número.");
        // Solicitamos usando el teclado el numero2 y establecemos su valor
        numero2 = teclado.nextInt();

        // ahora ya con ambos valores, procedemos a comparar cual es mayor y cual es menor
        if (numero1 > numero2) { // lo que sucede si el iff se cumple empieza aqui
            System.out.println("El mayor es " + numero1 + " y el menor es " + numero2);
        }                    // lo que sucede si el if se cumple termina aqui
        else { // lo que sucede si el If no se cumple empieza aqui
            System.out.println("El mayor es " + numero2 + " y el menor es" + numero1);
        } // lo que sucede si el If no se cumple empieza aqui
    }
}