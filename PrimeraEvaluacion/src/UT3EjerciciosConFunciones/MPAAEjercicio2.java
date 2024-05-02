package UT3EjerciciosConFunciones;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase VerificarImparidad</h1><br>
 * <p style="text-align: justify;">
 * Este programa incluye una función que determina si un número entero es impar.
 * La función 'esImpar' recibe un número entero y devuelve un valor booleano, indicando
 * si el número es impar (true) o no (false). Este resultado es luego mostrado al usuario.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio2 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce un número entero: ");
        int numero = teclado.nextInt();

        // Invocación de la función esImpar
        boolean resultado = esImpar(numero);
        System.out.println("El número " + numero + " es " + (resultado ? "impar." : "par."));
    }

    /**
     * Determina si un número entero es impar.
     *
     * @param numero El número entero a verificar.
     * @return true si el número es impar, false si es par.
     */
    public static boolean esImpar(int numero) {
        return numero % 2 != 0;
    }
}