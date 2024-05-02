package UT3EjerciciosConFunciones;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MaximoYMinimo</h1><br>
 * <p style="text-align: justify;">
 * Este programa contiene dos funciones que determinan el mayor y el menor de dos números enteros.
 * La función 'maximo' compara dos números y devuelve el mayor, mientras que 'minimo' devuelve el menor.
 * Las comparaciones se realizan utilizando estructuras if-else, reflejando un enfoque más básico y didáctico
 * en la programación.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio3 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el primer número: ");
        int num1 = teclado.nextInt();
        System.out.print("Introduce el segundo número: ");
        int num2 = teclado.nextInt();

        // Invocación de las funciones maximo y minimo
        int mayor = maximo(num1, num2);
        int menor = minimo(num1, num2);
        System.out.println("El mayor número es: " + mayor);
        System.out.println("El menor número es: " + menor);
    }

    /**
     * Calcula el mayor de dos números enteros usando una estructura if-else.
     *
     * @param num1 El primer número entero.
     * @param num2 El segundo número entero.
     * @return El mayor de los dos números.
     */
    public static int maximo(int num1, int num2) {
        if (num1 > num2) {
            return num1;
        } else {
            return num2;
        }
    }

    /**
     * Calcula el menor de dos números enteros usando una estructura if-else.
     *
     * @param num1 El primer número entero.
     * @param num2 El segundo número entero.
     * @return El menor de los dos números.
     */
    public static int minimo(int num1, int num2) {
        if (num1 < num2) {
            return num1;
        } else {
            return num2;
        }
    }
}
