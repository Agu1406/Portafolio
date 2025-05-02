package UT2.EjerciciosCondicionales;

import java.util.Scanner;

/***
 * Ejercicio Condicional N.º6 - Programa que pide al usuario su edad
 * y dependiendo de la misma muestra un mensaje.
 */
public class Ejercicio6MensajeEdad {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int edad;

        System.out.println("Dime tú edad: ");
        edad = teclado.nextInt();

        if (edad < 0) {
            System.out.println("¡Error! Edad invalida.");
        } else if (edad <= 12) {
            System.out.println("Estás en la niñez (entre 0 y 12 años). ");
        } else if (edad <= 18) {
            System.out.println("Estás en la adolecencia (entre 13 y 18 años). ");
        } else if (edad <= 33) {
            System.out.println("Estás en la juventud (entre 19 y 33 años). ");
        } else if (edad <= 65) {
            System.out.println("Estás en la madurez (entre 34 y 65 años). ");
        } else if (edad <= 79) {
            System.out.println("Estás en la jubilación (entre 66 y 79 años). ");
        } else {
            System.out.println("Estás en la tercera edad (80 años o más). ");
        }

    }
}
