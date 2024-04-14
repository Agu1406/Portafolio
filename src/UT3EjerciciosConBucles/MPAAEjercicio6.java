package UT3EjerciciosConBucles;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio6</h1><br>
 * <p style="text-align: justify;">
 * Este programa muestra las tablas de multiplicar para los números 1, 2, 3, 4 y 5.
 * Cada tabla incluye los productos de multiplicar el número base por los números del 1 al 10.
 * Se utiliza un bucle while para iterar a través de los números base y otro bucle while anidado
 * para calcular y mostrar cada multiplicación correspondiente.
 * </p><br>
 *
 * <h2 style="text-align: center;">Descripción del Ejercicio</h2><br>
 * <p style="text-align: justify;">
 * 6- Algoritmo que muestre la tabla de multiplicar de los números 1, 2, 3, 4 y 5.
 * </p><br>
 *
 * @version 1.0
 * @since 10/10/2023
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio6 {
    public static void main(String[] args) {
        int base = 1;  // Base para la tabla de multiplicar, comienza en 1.
        while (base <= 5) {  // Bucle para las bases de las tablas, de 1 a 5.
            System.out.println("Tabla de multiplicar del " + base + ":");
            int multiplicador = 1;  // Multiplicador para la tabla de multiplicar, comienza en 1.
            while (multiplicador <= 10) {  // Bucle para los multiplicadores de la tabla, de 1 a 10.
                System.out.printf("%d x %d = %d%n", base, multiplicador, base * multiplicador);
                multiplicador++;  // Incrementa el multiplicador.
            }
            System.out.println();  // Salto de línea para separar las tablas.
            base++;  // Incrementa la base para la siguiente tabla.
        }
    }
}
