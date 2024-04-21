package UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase ClasificacionPorEdad</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario que ingrese su edad y muestra un mensaje que indica en qué etapa de la vida se encuentra,
 * de acuerdo con una clasificación específica que abarca desde la niñez hasta la tercera edad.
 * </p><br>
 *
 * @version 1.0
 * @since 19/10/24
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio6 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce tu edad: ");
        int edad = teclado.nextInt();

        if (edad < 0) {
            System.out.println("Has introducido una edad no válida.");
        } else if (edad <= 12) {
            System.out.println("Niñez (entre 0 y 12 años)");
        } else if (edad <= 18) {
            System.out.println("Adolescencia (entre 13 y 18 años)");
        } else if (edad <= 33) {
            System.out.println("Juventud (entre 19 y 33 años)");
        } else if (edad <= 65) {
            System.out.println("Madurez (entre 34 y 65 años)");
        } else if (edad <= 79) {
            System.out.println("Jubilación (entre 66 y 79 años)");
        } else {
            System.out.println("Tercera Edad (+80 años)");
        }
    }
}
