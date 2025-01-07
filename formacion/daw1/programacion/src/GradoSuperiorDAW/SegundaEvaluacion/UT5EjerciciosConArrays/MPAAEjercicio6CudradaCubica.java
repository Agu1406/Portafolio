package GradoSuperiorDAW.SegundaEvaluacion.UT5EjerciciosConArrays;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * El objetivo del programa es solicitar 5 números por pantalla y mostrar
 * su raíz cuadra y su raíz cubica.
 */
public class MPAAEjercicio6CudradaCubica {
    public static void main(String[] args) {
        // Instancia de "Scanner" llamada "teclado".
        Scanner teclado = new Scanner(System.in);
        // Variable que almacenara los números ingresados.
        int numero;
        // Variables que almacenan los resultados del calcúlo.
        int cuadrada, cubica;

        /*
        * Bucle que se repite 5 veces (tiene 5 iteraciones), en cada
        * una de ellas pide un número, lo guarda temporamente y usa
        * las funciones calcularCuadrada y calcularCubida para poder
        * (si es posible) imprimir ambas o al menos una de ellas.
        * */
        for (int iteracion = 0; iteracion < 5; iteracion++) {
            // try-catch que controla que solo se ingresen números validos enteros.
            try {
                numero = teclado.nextInt();

                cuadrada = calcularCuadrada(numero);
                cubica = calcularCubica(numero);
            } catch (InputMismatchException exception) {
                System.out.println("¡Error! Debes ingresar números enteros, intentalo de nuevo.");
            }
        }
    }

    private static int calcularCubica(int numero) {

        return 0;
    }

    private static int calcularCuadrada(int numero) {

        return 0;
    }
}
