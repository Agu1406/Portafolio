package UT3EjerciciosRepasoDelUT3;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MenuProgramas</h1><br>
 * <p style="text-align: justify;">
 * Este programa presenta un menú con opciones para ejecutar distintos programas previamente desarrollados.
 * El usuario puede elegir entre los programas disponibles y finalizar la ejecución seleccionando la opción de salir.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio12 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    realizarProgramaComisiones(); // Suponiendo que esta función realiza el ejercicio 7
                    break;
                case 2:
                    calcularCalificacionFinal(); // Suponiendo que esta función realiza el ejercicio 10
                    break;
                case 3:
                    calcularDistanciaPuntos(); // Suponiendo que esta función realiza el ejercicio 11
                    break;
                case 4:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 4);
    }

    public static void realizarProgramaComisiones() {
        // Cuerpo de la función que ejecuta el ejercicio 7
    }

    public static void calcularCalificacionFinal() {
        // Cuerpo de la función que ejecuta el ejercicio 10
    }

    public static void calcularDistanciaPuntos() {
        // Cuerpo de la función que ejecuta el ejercicio 11
    }

    public static void mostrarMenu() {
        System.out.println("\nMenu de Programas");
        System.out.println("1. Programa de comisiones (Ejercicio 7)");
        System.out.println("2. Calificación final (Ejercicio 10)");
        System.out.println("3. Distancia entre puntos (Ejercicio 11)");
        System.out.println("4. Salir");
        System.out.print("Selecciona una opción: ");
    }
}
