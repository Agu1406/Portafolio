package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosRepasoDelUT3;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MostrarNumerosPares</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario que introduzca números indefinidamente y muestra aquellos
 * que son pares. El proceso termina cuando el usuario ha introducido cinco números pares.
 * Este programa implementa tres variantes utilizando diferentes tipos de bucles: for, while, y do-while.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio1 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        mostrarParesConFor(teclado);
        mostrarParesConWhile(teclado);
        mostrarParesConDoWhile(teclado);
    }

    /**
     * <h2 style="text-align: center;">Método mostrarParesConFor</h2><br>
     * <p style="text-align: justify;">
     * Utiliza un bucle for para solicitar números al usuario y mostrar aquellos que son pares.
     * Termina después de mostrar cinco números pares.
     * </p><br>
     *
     * @param teclado Scanner utilizado para leer la entrada del usuario.
     */
    private static void mostrarParesConFor(Scanner teclado) {
        int contadorPares = 0;
        System.out.println("Introduce números (bucle for):");

        for (int numero; contadorPares < 5; ) {
            numero = teclado.nextInt();
            if (numero % 2 == 0) {
                System.out.println("Número par: " + numero);
                contadorPares++;
            }
        }
    }

    /**
     * <h2 style="text-align: center;">Método mostrarParesConWhile</h2><br>
     * <p style="text-align: justify;">
     * Utiliza un bucle while para realizar la misma tarea que el método anterior.
     * </p><br>
     *
     * @param teclado Scanner utilizado para leer la entrada del usuario.
     */
    private static void mostrarParesConWhile(Scanner teclado) {
        int contadorPares = 0;
        int numero;
        System.out.println("Introduce números (bucle while):");

        while (contadorPares < 5) {
            numero = teclado.nextInt();
            if (numero % 2 == 0) {
                System.out.println("Número par: " + numero);
                contadorPares++;
            }
        }
    }

    /**
     * <h2 style="text-align: center;">Método mostrarParesConDoWhile</h2><br>
     * <p style="text-align: justify;">
     * Emplea un bucle do-while para la misma funcionalidad de mostrar números pares introducidos por el usuario.
     * </p><br>
     *
     * @param teclado Scanner utilizado para leer la entrada del usuario.
     */
    private static void mostrarParesConDoWhile(Scanner teclado) {
        int contadorPares = 0;
        int numero;
        System.out.println("Introduce números (bucle do-while):");

        do {
            numero = teclado.nextInt();
            if (numero % 2 == 0) {
                System.out.println("Número par: " + numero);
                contadorPares++;
            }
        } while (contadorPares < 5);
    }
}
