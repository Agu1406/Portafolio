package UT3EjerciciosRepasoDelUT3;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase Contador de Números Positivos, Negativos y Ceros</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario que introduzca una cantidad de números y
 * luego recibe esos números, contabilizando cuántos son positivos, negativos y ceros.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio5 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("¿Cuántos números deseas introducir? ");
        int cantidadNumeros = teclado.nextInt();

        int contadorPositivos = 0;
        int contadorNegativos = 0;
        int contadorCeros = 0;

        /* Si cuesta mucho usar for, para novatos recomiendo un while, aunque
        * no lo he subido aquí, en su momento lo hice con los tres bucles y
        * solo subí este, con while sería de la siguiente forma:
        *
        * While (cantidadNúmeros > 0) {
        * System.out.print("Introduce un número:");
        + int numero = teclado.nextInt();
        * // Si el número es mayor a "0", es positivo, se cuenta +1 positivo.
        + if (numero > 0) {
        * contadorPositivos++;
        * // Si, en cámbio es menor a "0", es negativo, se cuenta +1 negativo.
        * } else if (número < 0) {
        * contadorNegativos++;
        * // Cualquier otra opción por descarte es un "0", se cuenta +1 cero.
        * } else {
        * contadorCeros++;
        * }
        * // Indiferentemente de que ocurra, restamos "1" al control de flujo.
        * CantidadNúmeros--; }
        *
        * Cuidado copiando-pegando el código de arriba, me daba "toc" ver los errores
        * por ortografía y le puse acentos a algunas cosas (también para incentivar
        * a que en lugar de copiar, lo hagas tú mismo y aprendas a hacerlo)
        * */

        for (int contador = 0; contador < cantidadNumeros; contador++) {
            System.out.print("Introduce un número: ");
            int numero = teclado.nextInt();

            if (numero > 0) {
                contadorPositivos++;
            } else if (numero < 0) {
                contadorNegativos++;
            } else {
                contadorCeros++;
            }
        }

        System.out.println("Has introducido " + contadorPositivos + " números mayores que 0.");
        System.out.println("Has introducido " + contadorNegativos + " números menores que 0.");
        System.out.println("Has introducido " + contadorCeros + " números iguales a 0.");
    }
}
