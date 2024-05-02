package PrimeraEvaluacion.UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase DiasDelMes</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario que ingrese un mes en formato numérico y muestra el número de días de ese mes,
 * asumiendo un año no bisiesto. Además, verifica que el número ingresado esté dentro del rango válido de 1 a 12,
 * mostrando un mensaje de error si no lo está.
 * </p><br>
 *
 * @version 1.0
 * @since 19/10/24
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio5 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el número del mes (1-12): ");
        int mes = teclado.nextInt();

        // Verificación de la validez del mes ingresado
        if (mes < 1 || mes > 12) {
            System.out.println("Número de mes inválido. Por favor, introduce un número entre 1 y 12.");
        } else {
            // Uso de switch para determinar el número de días según el mes
            switch (mes) {
                case 4:
                case 6:
                case 9:
                case 11:
                    System.out.println("El mes " + mes + " tiene 30 días.");
                    break;
                case 2:
                    System.out.println("El mes " + mes + " tiene 28 días.");
                    break;
                default:
                    System.out.println("El mes " + mes + " tiene 31 días.");
                    break;
            }
        }
    }
}
