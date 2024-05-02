package UT3EjerciciosConBucles;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio9</h1><br>
 * <p style="text-align: justify;">
 * Este programa presenta un menú con cuatro opciones al usuario, incluyendo una opción para "Salir".
 * El usuario puede seleccionar cualquiera de las opciones para ver una respuesta específica en pantalla.
 * El menú se muestra repetidamente hasta que se selecciona la opción "Salir", momento en el cual el programa terminará.
 * </p><br>
 *
 * <h2 style="text-align: center;">Descripción del Ejercicio</h2><br>
 * <p style="text-align: justify;">
 * 9- Realizar un ejemplo de menú, donde podemos escoger entre 4 opciones (puedes poner las
 * opciones que quieras, siempre que una de ellas sea “Salir”). Al seleccionar una opción, se
 * muestra un texto por pantalla indicando la opción seleccionada y se vuelve a mostrar el
 * menú. El programa finaliza cuando seleccionamos la opción de “Salir”.
 * </p><br>
 *
 * @version 1.0
 * @since 10/10/2023
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;  // Variable para almacenar la opción elegida por el usuario.

        do {
            System.out.println("Menú de opciones:");
            System.out.println("1. Opción 1: Mostrar Hola");
            System.out.println("2. Opción 2: Mostrar Adiós");
            System.out.println("3. Opción 3: Mostrar Java");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción (1-4): ");
            opcion = scanner.nextInt();  // Lee la opción del usuario.

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado 'Mostrar Hola'.");
                    break;
                case 2:
                    System.out.println("Has seleccionado 'Mostrar Adiós'.");
                    break;
                case 3:
                    System.out.println("Has seleccionado 'Mostrar Java'.");
                    break;
                case 4:
                    System.out.println("Has seleccionado 'Salir'. Adiós!");
                    break;
                default:
                    System.out.println("Opción no válida, intenta nuevamente.");
            }
        } while (opcion != 4);  // Continúa mostrando el menú hasta que la opción sea 'Salir'.

    }
}
