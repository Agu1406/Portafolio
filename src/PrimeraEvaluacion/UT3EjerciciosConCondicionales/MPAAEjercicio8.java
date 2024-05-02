package PrimeraEvaluacion.UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase ValidarFecha</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario que introduzca una fecha (día, mes y año) y verifica si es una fecha válida dentro del siglo XXI,
 * sin tener en cuenta los años bisiestos. La fecha debe estar entre el 1 de enero de 2001 y el 31 de diciembre de 2100.
 * </p><br>
 *
 * @version 1.0
 * @since 19/10/24
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio8 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el día (1-31): ");
        int dia = teclado.nextInt();
        System.out.print("Introduce el mes (1-12): ");
        int mes = teclado.nextInt();
        System.out.print("Introduce el año (2001-2100): ");
        int ano = teclado.nextInt();

        // Verifica si la fecha está en el rango del siglo XXI y si los días son válidos para el mes dado.
        if (ano >= 2001 && ano <= 2100) {
            if (mes >= 1 && mes <= 12) {
                boolean esFechaValida = false;
                switch (mes) {
                    case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                        esFechaValida = (dia >= 1 && dia <= 31);
                        break;
                    case 4: case 6: case 9: case 11:
                        esFechaValida = (dia >= 1 && dia <= 30);
                        break;
                    case 2:
                        esFechaValida = (dia >= 1 && dia <= 28); // No se consideran años bisiestos.
                        break;
                }
                if (esFechaValida) {
                    System.out.println("La fecha introducida es correcta.");
                } else {
                    System.out.println("Día inválido para el mes y año dados.");
                }
            } else {
                System.out.println("Mes inválido. Debe ser entre 1 y 12.");
            }
        } else {
            System.out.println("Año inválido. Debe ser entre 2001 y 2100.");
        }
    }
}
