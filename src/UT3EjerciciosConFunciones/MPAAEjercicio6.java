package UT3EjerciciosConFunciones;

/**
 * <h1 style="text-align: center;">Clase TablasDeMultiplicar</h1><br>
 * <p style="text-align: justify;">
 * Este programa define una función que muestra todas las tablas de multiplicar del 1 al 10.
 * Utiliza un bucle anidado para generar cada tabla de multiplicar dentro de la función.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio6 {

    public static void main(String[] args) {
        mostrarTodasTablasMultiplicar(); // Llama a la función que muestra todas las tablas de multiplicar
    }

    /**
     * Muestra todas las tablas de multiplicar del 1 al 10 utilizando un bucle anidado.
     * El bucle externo itera a través de los números del 1 al 10, y el bucle interno
     * genera cada línea de la tabla para el número actual del bucle externo.
     */
    public static void mostrarTodasTablasMultiplicar() {
        // Bucle externo que itera sobre cada número cuya tabla de multiplicar necesita ser mostrada
        for (int num = 1; num <= 10; num++) {
            System.out.println("Tabla de multiplicar del " + num + ":");
            // Bucle interno que genera las líneas de la tabla de multiplicar para el número actual
            for (int i = 1; i <= 10; i++) {
                System.out.println(num + " x " + i + " = " + (num * i));
            }
            System.out.println(); // Añade un salto de línea entre tablas para claridad
        }
    }
}
