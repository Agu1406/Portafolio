package PrimeraEvaluacion.UT3EjerciciosConFunciones;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase VerificarParidad</h1><br>
 * <p style="text-align: justify;">
 * Este programa incluye una función que determina si un número entero es par.
 * La función 'esPar' recibe un número entero y devuelve un valor booleano, indicando
 * si el número es par (true) o impar (false). Este resultado es luego mostrado al usuario.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio1 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce un número entero: ");
        int numero = teclado.nextInt();

        // Invocación de la función esPar
        boolean resultado = esPar(numero);
        System.out.println("El número " + numero + " es " + (resultado ? "par." : "impar."));
    }

    /**
     * Determina si un número entero es par.
     *
     * @param numero El número entero a verificar.
     * @return true si el número es par, false si es impar.
     */
    public static boolean esPar(int numero) {
        return numero % 2 == 0;
    }
}
