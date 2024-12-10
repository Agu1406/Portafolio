package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosConFunciones;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase DiasEntreFechas</h1><br>
 * <p style="text-align: justify;">
 * Este programa permite al usuario introducir dos fechas y calcula el número de días entre ellas,
 * verificando primero que ambas fechas sean válidas usando métodos específicos para validar fechas
 * y calcular los días en cada mes.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio12 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce la primera fecha:");
        System.out.print("Día: ");
        int dia1 = teclado.nextInt();
        System.out.print("Mes: ");
        int mes1 = teclado.nextInt();
        System.out.print("Año: ");
        int ano1 = teclado.nextInt();

        System.out.println("Introduce la segunda fecha:");
        System.out.print("Día: ");
        int dia2 = teclado.nextInt();
        System.out.print("Mes: ");
        int mes2 = teclado.nextInt();
        System.out.print("Año: ");
        int ano2 = teclado.nextInt();

        if (fechaValida(dia1, mes1, ano1) && fechaValida(dia2, mes2, ano2)) {
            int dias = diasEntreFechas(dia1, mes1, ano1, dia2, mes2, ano2);
            System.out.println("Hay " + dias + " días entre las dos fechas.");
        } else {
            System.out.println("Una o ambas fechas son incorrectas.");
        }
    }

    private static int diasEntreFechas(int dia1, int mes1, int ano1, int dia2, int mes2, int ano2) {
        int totalDias = 0; // Inicializa el contador de días total
        // Ciclo hasta que las dos fechas sean iguales
        while (!(dia1 == dia2 && mes1 == mes2 && ano1 == ano2)) {
            dia1++;
            if (dia1 > diasEnMes(mes1, ano1)) {
                dia1 = 1; // Reinicia el contador de días al primer día del próximo mes
                mes1++;
                if (mes1 > 12) { // Si el mes supera diciembre, incrementa el año
                    mes1 = 1; // Reinicia el contador de meses al primer mes del próximo año
                    ano1++;
                }
            }
            totalDias++; // Incrementa el contador de días entre las dos fechas
        }
        return totalDias; // Devuelve el total de días calculado
    }

    private static boolean fechaValida(int dia, int mes, int ano) {
        if (mes < 1 || mes > 12) {
            return false; // El mes debe estar entre 1 y 12
        }
        int diasDelMes = diasEnMes(mes, ano); // Obtiene los días del mes considerando si es bisiesto
        return dia > 0 && dia <= diasDelMes; // El día debe ser válido para el mes y año dados
    }

    private static int diasEnMes(int mes, int ano) {
        // Utiliza un switch para determinar la cantidad de días según el mes
        switch (mes) {
            case 4: case 6: case 9: case 11:
                return 30; // Abril, Junio, Septiembre, Noviembre tienen 30 días
            case 2:
                // Febrero tiene 28 días o 29 en años bisiestos
                if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
                    return 29; // Año bisiesto
                } else {
                    return 28; // Año no bisiesto
                }
            default:
                return 31; // Enero, Marzo, Mayo, Julio, Agosto, Octubre, Diciembre tienen 31 días
        }
    }
}
