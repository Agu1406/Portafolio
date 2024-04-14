package UT3EjerciciosConBucles;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio5</h1><br>
 * <p style="text-align: justify;">
 * Este programa calcula el total de horas trabajadas por un empleado durante cinco días y determina el sueldo
 * correspondiente basado en un precio por hora establecido. Solicita las horas trabajadas cada día y luego
 * realiza los cálculos pertinentes.
 * </p><br>
 *
 * <h2 style="text-align: center;">Descripción del Ejercicio</h2><br>
 * <p style="text-align: justify;">
 * 5- Una empresa tiene el registro de las horas que trabaja diariamente un empleado durante la
 * semana (cinco días) y requiere determinar el total de éstas, así como el sueldo que recibirá
 * por las horas trabajadas. El programa preguntará por las horas trabajadas cada uno de los
 * días de la semana y después mostrará los resultados. El precio/hora es de 15€.
 * </p><br>
 *
 * @version 1.0
 * @since 10/10/2023
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalHoras = 0;  // Acumula el total de horas trabajadas durante la semana.
        int precioHora = 15;  // Precio por hora de trabajo en euros.

        int dia = 1;  // Contador de días, empieza en el primer día de la semana laboral.
        while (dia <= 5) {
            System.out.print("Introduce las horas trabajadas en el día " + dia + ": ");
            int horasDia = scanner.nextInt();  // Horas trabajadas en un día específico.
            totalHoras += horasDia;  // Suma las horas trabajadas del día al total.
            dia++;  // Incrementa el contador de días.
        }

        int sueldo = totalHoras * precioHora;  // Calcula el sueldo multiplicando las horas totales por el precio por hora.

        System.out.println("Total de horas trabajadas en la semana: " + totalHoras);
        System.out.println("Sueldo total por las horas trabajadas: " + sueldo + "€");
    }
}
