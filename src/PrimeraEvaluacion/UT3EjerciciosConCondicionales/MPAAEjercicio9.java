package PrimeraEvaluacion.UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase AnioBisiesto</h1><br>
 * <p style="text-align: justify;">
 * Este programa determina si un año introducido por el usuario es bisiesto o no.
 * Un año es considerado bisiesto si cumple con las siguientes condiciones:
 * es divisible por 4 pero no por 100, excepto si también es divisible por 400.
 * </p><br>
 *
 * @version 1.0
 * @since 19/10/24
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio9 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce un año para verificar si es bisiesto: ");
        int anio = teclado.nextInt();

        // Lógica para determinar si un año es bisiesto
        if ((anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0)) {
            System.out.println("El año " + anio + " es bisiesto.");
        } else {
            System.out.println("El año " + anio + " no es bisiesto.");
        }
    }
}
