package UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase NombreDia</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario que ingrese un número entre 1 y 7 que representa los días de la semana.
 * Luego, muestra el nombre del día correspondiente. Si el número introducido está fuera del rango permitido,
 * el programa informa de un error y pide de nuevo un número válido.
 * </p><br>
 *
 * @version 1.0
 * @since 19/10/24
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio12 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int diaNumero;
        boolean entradaValida;

        do {
            System.out.println("Introduce el número del día de la semana (1-7): ");
            diaNumero = teclado.nextInt();
            entradaValida = true;  // Se asume que la entrada es válida a menos que se pruebe lo contrario

            switch (diaNumero) {
                case 1:
                    System.out.println("Lunes");
                    break;
                case 2:
                    System.out.println("Martes");
                    break;
                case 3:
                    System.out.println("Miércoles");
                    break;
                case 4:
                    System.out.println("Jueves");
                    break;
                case 5:
                    System.out.println("Viernes");
                    break;
                case 6:
                    System.out.println("Sábado");
                    break;
                case 7:
                    System.out.println("Domingo");
                    break;
                default:
                    System.out.println("Error: número incorrecto. Debe ser entre 1 y 7.");
                    entradaValida = false;  // La entrada no es válida, se repite el bucle
                    break;
            }
        } while (!entradaValida);
    }
}
