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
        double cuadrada, cubica;

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
                // Mensaje de error personalizado que salta si ocurre la excepcion.
                System.out.println("¡Error! Debes ingresar números enteros, intentalo de nuevo.");
                // Decrementamos el contador para que se repita la misma iteración otra vez.
                iteracion--;
            }
        }
    }

    private static double calcularCubica(int numero) {
        // Si el número es menor a "0", calculamos la raíz cúbica con cuidado, ya que es válida para números negativos.
        boolean esNegativo = numero < 0;

        // Convertimos el número a positivo si es negativo, para calcularlo fácilmente.
        if (esNegativo) {
            numero = -numero;
        }

        /*
         * Para calcular la raíz cúbica usamos algo similar a Newton-Raphson.
         *
         * Sabemos que la raíz cúbica de un número es aquel que elevado al cubo
         * da como resultado el número original. Por lo tanto:
         *
         * 1. Hacemos una estimación inicial, como dividir el número por 3.
         * 2. Establecemos un margen de tolerancia (nivel de precisión).
         * 3. Iteramos mientras la diferencia entre "estimación" elevada al cubo y
         *    el número original sea mayor a la tolerancia.
         * 4. Si el número original era negativo, cambiamos el signo del resultado
         *    para que sea correcto.
         *
         * Notes que como desarrolle el calculador cúbico después del cuadrado
         * supe documentar machismo mejor el código.
         */

        double estimacion = numero / 3.0;
        double tolerancia = 0.00001;

        // Mientras la diferencia entre la estimación al cubo y el número sea mayor que la tolerancia.
        while (diferencia(estimacion * estimacion * estimacion, numero) > tolerancia) {
            // Fórmula para calcular la nueva estimación usando Newton-Raphson.
            estimacion = ((2.0 * estimacion) + (numero / (estimacion * estimacion))) / 3.0;
        }

        // Si el número original era negativo, cambiamos el signo del resultado.
        return esNegativo ? - estimacion : estimacion;
    }

    private static double calcularCuadrada(int numero) {
        // double resultado = Math.sqrt(numero); - Aprendí a hacerlo nativamente.

        // Si el número es menor a "0" no es posible hacer ningun calcúlo, aviso del error.
        if (numero < 0) {
            System.out.println("No se puede calcular la raíz cuadrada de un número negativo.");
            // Devuelvo un "-1" que desde el otro lado intepreto como error y lo imprimo como  tal.
            return -1;
        }
        /*
        * Para hacer el calculo sabemos que la raíz cuadrada de un número es aquel
        * número que multiplicado por si mismo de como resultado el número original
        * entonces, para calcularla manualmente usamos la función de Newton-Raphson
        * primero, el número del que buscamos la raíz cuadrada se divide entre dos
        * esta es la estimación del número que podría ser la raíz cuadrada, luego
        * establecemos un margen de error, cuando la diferencia entre "estimación"
        * multiplicada por si misma y el número original sea inferior a la tolerancia
        * podemos dar por encontrada la raíz cuadrada, sabiendo eso, procedemos a
        * hacer el cálculo.
        * */

        // Estimación inicialmente es el número original partido entre dos.
        double estimacion = numero / 2.0;
        // Dejamos una muy poquita tolerancia para acercarnos lo mejor posible al resultado.
        double tolerancia = 0.00001;

        /*
        * Documentado, en ella usamos Math.abs que obtiene el valor absoluto de algo,
        * esto evita que los números sean negativos y que la condición no se cumpla
        * nunca.
        *
        * while (Math.abs(estimacion * estimacion - numero) > tolerancia) {
        * estimacion = (estimacion + numero / estimacion) / 2.0;
        * }
        * */


        while (diferencia(estimacion * estimacion, numero) > tolerancia) {
            estimacion = (estimacion + numero / estimacion) / 2.0;
        }

        return estimacion;
    }

    // Función para calcular la diferencia absoluta sin Math.abs.
    private static double diferencia(double estimacion, double numero) {
        /*
        * ¿Es "estimación" mayor que el número original? Si la respuesta es si
        * entonces resta el mayor con el menor, ¿No lo es? Hace la resta al
        * revés, otra vez, mayor con el menor, así el resultado siempre sera
        * un número positivo.
        * */
        return (estimacion > numero) ? (estimacion - numero) : (numero - estimacion);
    }
}
