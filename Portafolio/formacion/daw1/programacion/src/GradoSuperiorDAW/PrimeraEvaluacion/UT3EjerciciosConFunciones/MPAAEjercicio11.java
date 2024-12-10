package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosConFunciones;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase ValidarFecha</h1><br>
 * <p style="text-align: justify;">
 * Este programa permite al usuario ingresar una fecha en formato día, mes y año, y verifica si es una fecha válida.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio11 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el día: ");
        int dia = teclado.nextInt();
        System.out.print("Introduce el mes: ");
        int mes = teclado.nextInt();
        System.out.print("Introduce el año: ");
        int ano = teclado.nextInt();

        if (fechaValida(dia, mes, ano)) {
            System.out.println("La fecha " + dia + "/" + mes + "/" + ano + " es válida.");
        } else {
            System.out.println("La fecha " + dia + "/" + mes + "/" + ano + " no es válida.");
        }
    }

    /**
     * <h2 style="text-align: center;">Método fechaValida</h2><br>
     * <p style="text-align: justify;">
     * Determina si los valores de día, mes y año representan una fecha válida en el calendario gregoriano.
     * </p><br>
     *
     * @param dia El día del mes.
     * @param mes El mes del año.
     * @param ano El año.
     * @return true si es una fecha válida, false si no lo es.
     */
    public static boolean fechaValida(int dia, int mes, int ano) {
        if (mes < 1 || mes > 12) {
            return false; // Mes fuera de rango
        }

        int diasDelMes = diasEnMes(mes, ano);

        return dia > 0 && dia <= diasDelMes;
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
