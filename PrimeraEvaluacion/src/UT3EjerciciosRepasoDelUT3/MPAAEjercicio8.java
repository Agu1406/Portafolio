package UT3EjerciciosRepasoDelUT3;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase Elegibilidad para Clases de Conducir</h1><br>
 * <p style="text-align: justify;">
 * Este programa evalúa si un alumno cumple con los requisitos para tomar clases de
 * conducción basado en su nota del examen teórico, edad y si ha pagado las clases.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio8 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int notaTeorico;
        int edad;
        char clasesPagadas;

        System.out.print("Introduce la nota del examen teórico: ");
        notaTeorico = teclado.nextInt();

        System.out.print("Introduce la edad: ");
        edad = teclado.nextInt();

        System.out.print("¿Has pagado las clases? (S/N): ");
        clasesPagadas = teclado.next().charAt(0);

        // Llamada a la función que decide si el alumno puede tomar clases de conducir
        String resultado = decidirElegibilidad(notaTeorico, edad, clasesPagadas);
        System.out.println(resultado);
    }

    /**
     * <h2 style="text-align: center;">Decidir Elegibilidad para Clases de Conducir</h2><br>
     * <p style="text-align: justify;">
     * Determina si el alumno puede tomar clases de conducción basado en su nota del
     * examen teórico, edad y si ha pagado las clases.
     * </p><br>
     *
     * @param notaTeorico   La nota obtenida en el examen teórico.
     * @param edad          La edad del alumno.
     * @param clasesPagadas Indica si las clases han sido pagadas ('S') o no ('N').
     * @return Un mensaje que indica si el alumno puede proceder, necesita pagar o no está permitido.
     */
    private static String decidirElegibilidad(int notaTeorico, int edad, char clasesPagadas) {
        if (notaTeorico >= 8 && edad >= 18) {
            if (clasesPagadas == 'S') {
                return "ADELANTE";
            } else if (clasesPagadas == 'N') {
                return "FALTA PAGO";
            }
        }
        return "NO PERMITIDO";
    }
}
