package UT3EjerciciosConFunciones;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase CalculadoraDeDiasMes</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario un mes y un año, luego calcula el número de días de ese mes considerando si el año es bisiesto o no.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio10 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Por favor, introduce el mes (1-12): ");
        int mes = teclado.nextInt();
        System.out.print("Por favor, introduce el año: ");
        int ano = teclado.nextInt();

        System.out.println("El mes " + mes + " del año " + ano + " tiene " + diasEnMes(mes, ano) + " días.");
    }

    /**
     * <h2 style="text-align: center;">Método diasEnMes</h2><br>
     * <p style="text-align: justify;">
     * Calcula el número de días de un mes específico de un año dado, utilizando reglas para años bisiestos.
     * </p><br>
     *
     * @param mes El mes del año, donde 1 es enero y 12 es diciembre.
     * @param ano El año en consideración.
     * @return El número de días en el mes especificado.
     */
    public static int diasEnMes(int mes, int ano) {
        if (mes == 2) {
            return esBisiesto(ano) ? 29 : 28;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        } else {
            return 31;
        }
    }

    /**
     * <h2 style="text-align: center;">Método esBisiesto</h2><br>
     * <p style="text-align: justify;">
     * Determina si un año es bisiesto basándose en las reglas del calendario gregoriano.
     * </p><br>
     *
     * @param ano El año que se evalúa.
     * @return true si el año es bisiesto, false en caso contrario.
     */
    public static boolean esBisiesto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }
}
