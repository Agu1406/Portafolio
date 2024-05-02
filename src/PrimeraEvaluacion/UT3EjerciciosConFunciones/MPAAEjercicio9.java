package PrimeraEvaluacion.UT3EjerciciosConFunciones;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase AnioBisiesto</h1><br>
 * <p style="text-align: justify;">
 * Esta clase proporciona una función que determina si un año es bisiesto o no.
 * Un año es bisiesto si es divisible por 4, pero no por 100, a menos que también sea
 * divisible por 400.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio9 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce un año: ");
        int anio = teclado.nextInt();
        boolean esBisiesto = esBisiesto(anio);
        System.out.println("El año " + anio + (esBisiesto ? " es " : " no es ") + "bisiesto.");
    }

    /**
     * <h2 style="text-align: center;">Método esBisiesto</h2><br>
     * <p style="text-align: justify;">
     * Determina si un año dado es bisiesto. Devuelve un valor booleano.
     * Un año es bisiesto si cumple con las siguientes condiciones:
     * Es divisible por 4 pero no por 100, o es divisible por 400.
     * </p><br>
     *
     * @param anio El año que se quiere verificar.
     * @return true si el año es bisiesto, false en caso contrario.
     */
    public static boolean esBisiesto(int anio) {
        if ((anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
