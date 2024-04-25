package UT3EjerciciosRepasoDelUT3;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase NumerosPrimos</h1><br>
 * <p style="text-align: justify;">
 * Este programa muestra en pantalla los primeros N números primos, donde N es un valor solicitado al usuario.
 * Se utiliza una función auxiliar para determinar si un número es primo.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio13 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("¿Cuántos números primos deseas mostrar? ");
        int cantidad = teclado.nextInt();

        mostrarPrimos(cantidad);
    }

    /**
     * <h2 style="text-align: center;">Función mostrarPrimos</h2><br>
     * <p style="text-align: justify;">
     * Esta función imprime en pantalla los primeros N números primos.
     * Utiliza la función esPrimo para verificar la primalidad de cada número.
     * </p><br>
     *
     * @param cantidad La cantidad de números primos a mostrar.
     */
    private static void mostrarPrimos(int cantidad) {
        int numero = 2; // Empezamos por el primer número primo conocido.
        int contados = 0; // Cuántos números primos hemos encontrado hasta ahora.

        while (contados < cantidad) {
            if (esPrimo(numero)) {
                System.out.println(numero + " es primo.");
                contados++;
            }
            numero++;
        }
    }

    /**
     * <h2 style="text-align: center;">Función esPrimo</h2><br>
     * <p style="text-align: justify;">
     * Esta función determina si un número dado es primo o no.
     * Un número es primo si solo tiene dos divisores: 1 y él mismo.
     * </p><br>
     *
     * @param numero El número a evaluar.
     * @return Verdadero si el número es primo, falso si no lo es.
     */
    private static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false; // 0 y 1 no son primos
        }

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; // Si es divisible por cualquier otro número, no es primo
            }
        }

        return true; // Si no se encontraron divisores, es primo
    }
}
