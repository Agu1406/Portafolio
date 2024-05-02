package PrimeraEvaluacion.UT3EjerciciosConBucles;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio11</h1><br>
 * <p style="text-align: justify;">
 * Este programa genera un número entero aleatorio entre 1 y 100 y permite al usuario intentar
 * adivinarlo. Ofrece pistas si el número a adivinar es mayor o menor que el introducido y
 * limita el número de intentos a 10. Si el usuario no acierta el número en los intentos dados,
 * el programa revela el número. Si acierta, informa en cuántos intentos fue logrado.
 * </p><br>
 *
 * <h2 style="text-align: center;">Descripción del Ejercicio</h2><br>
 * <p style="text-align: justify;">
 * 11- Crea una aplicación que permita adivinar un número entero. La aplicación genera un
 * número entero aleatorio del 1 al 100. A continuación, va pidiendo números y va respondiendo si el
 * número a adivinar es mayor o menor que el introducido, además de los intentos que te quedan
 * (tienes 10 intentos para acertarlo). El programa termina cuando se acierta el número (además te dice
 * en cuántos intentos lo has acertado), o si se llega al límite de intentos, te muestra el número que había generado.
 * </p><br>
 *
 * @version 1.0
 * @since 10/10/2023
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numeroAleatorio = (int) (Math.random() * 100) + 1;  // Genera un número aleatorio entre 1 y 100.
        int numeroIntroducido;
        int intentos = 10;  // Total de intentos permitidos.

        System.out.println("Adivina el número entre 1 y 100. Tienes 10 intentos.");

        for (int contador = 1; contador <= intentos; contador++) {
            System.out.print("Introduce un número (" + contador + " de 10 intentos): ");
            numeroIntroducido = scanner.nextInt();

            if (numeroIntroducido == numeroAleatorio) {
                System.out.println("¡Has acertado el número en " + contador + " intentos!");
                break;
            } else if (numeroIntroducido < numeroAleatorio) {
                System.out.println("El número es mayor que " + numeroIntroducido + ".");
            } else {
                System.out.println("El número es menor que " + numeroIntroducido + ".");
            }

            if (contador == intentos) {
                System.out.println("Has alcanzado el límite de intentos. El número era " + numeroAleatorio + ".");
            }
        }
    }
}
