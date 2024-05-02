package PrimeraEvaluacion.UT3EjerciciosConBucles;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio7</h1><br>
 * <p style="text-align: justify;">
 * Este programa calcula la potencia de un número dado (base) elevado a un exponente entero positivo,
 * sin utilizar el operador de potencia incorporado en Java. Se solicita al usuario que introduzca ambos,
 * la base real y el exponente entero, y el programa calcula el resultado utilizando un bucle while.
 * </p><br>
 *
 * <h2 style="text-align: center;">Descripción del Ejercicio</h2><br>
 * <p style="text-align: justify;">
 * 7- Escribe un programa que dados dos números, uno real (base) y un entero positivo (exponente),
 * saque por pantalla el resultado de la potencia. No se puede utilizar el operador de potencia.
 * </p><br>
 *
 * @version 1.0
 * @since 10/10/2023
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la base (número real): ");
        double base = scanner.nextDouble();  // Base de la potencia, número real.
        System.out.print("Introduce el exponente (número entero positivo): ");
        int exponente = scanner.nextInt();  // Exponente de la potencia, entero positivo.

        double resultado = 1;  // Resultado de la potencia, inicializado en 1 para el cálculo.
        int contador = 0;  // Contador para controlar el número de multiplicaciones.

        while (contador < exponente) {  // Bucle que se repite tantas veces como el valor del exponente.
            resultado *= base;  // Multiplica la base por sí misma.
            contador++;  // Incrementa el contador.
        }

        System.out.printf("El resultado de %f elevado a %d es %f.%n", base, exponente, resultado);
    }
}
