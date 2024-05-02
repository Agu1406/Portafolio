package UT3EjerciciosConFunciones;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase CalculadoraAvanzada</h1><br>
 * <p style="text-align: justify;">
 * Este programa simula una calculadora avanzada que permite al usuario realizar operaciones
 * básicas, calcular el factorial de un número y realizar la potenciación manualmente.
 * A través de un menú interactivo, el usuario puede elegir la operación deseada.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio8 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        boolean continuar = true;
        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Calcular factorial");
            System.out.println("6. Calcular potencia");
            System.out.println("7. Salir");
            int opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    sumar();
                    break;
                case 2:
                    restar();
                    break;
                case 3:
                    multiplicar();
                    break;
                case 4:
                    dividir();
                    break;
                case 5:
                    calcularFactorial();
                    break;
                case 6:
                    calcularPotencia();
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (continuar);
    }

    /**
     * <h2 style="text-align: center;">Método sumar</h2><br>
     * <p style="text-align: justify;">
     * Solicita al usuario dos números y muestra el resultado de su suma.
     * </p><br>
     */
    private static void sumar() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el primer número: ");
        float numeroUno = teclado.nextFloat();
        System.out.print("Introduce el segundo número: ");
        float numeroDos = teclado.nextFloat();
        float resultado = numeroUno + numeroDos;
        System.out.println("El resultado de la suma es: " + resultado);
    }

    /**
     * <h2 style="text-align: center;">Método restar</h2><br>
     * <p style="text-align: justify;">
     * Solicita al usuario dos números y muestra el resultado de su resta.
     * </p><br>
     */
    private static void restar() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el primer número: ");
        float numeroUno = teclado.nextFloat();
        System.out.print("Introduce el segundo número: ");
        float numeroDos = teclado.nextFloat();
        float resultado = numeroUno - numeroDos;
        System.out.println("El resultado de la resta es: " + resultado);
    }

    /**
     * <h2 style="text-align: center;">Método multiplicar</h2><br>
     * <p style="text-align: justify;">
     * Solicita al usuario dos números y muestra el resultado de su multiplicación.
     * </p><br>
     */
    private static void multiplicar() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el primer número: ");
        float numeroUno = teclado.nextFloat();
        System.out.print("Introduce el segundo número: ");
        float numeroDos = teclado.nextFloat();
        float resultado = numeroUno * numeroDos;
        System.out.println("El resultado de la multiplicación es: " + resultado);
    }

    /**
     * <h2 style="text-align: center;">Método dividir</h2><br>
     * <p style="text-align: justify;">
     * Solicita al usuario dos números y utiliza la función del ejercicio anterior para realizar la división.
     * </p><br>
     */
    private static void dividir() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el dividendo: ");
        float dividendo = teclado.nextFloat();
        float divisor;
        do {
            System.out.print("Introduce el divisor (diferente de cero): ");
            divisor = teclado.nextFloat();
            if (divisor == 0) {
                System.out.println("¡Error! Introduce un divisor que no sea cero.");
            }
        } while (divisor == 0);
        float resultado = dividendo / divisor;
        System.out.println("El resultado de la división es: " + resultado);
    }

    /**
     * <h2 style="text-align: center;">Método calcularFactorial</h2><br>
     * <p style="text-align: justify;">
     * Solicita al usuario un número entero y calcula el factorial de ese número.
     * Muestra el resultado del cálculo del factorial.
     * </p><br>
     */
    private static void calcularFactorial() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce un número entero: ");
        int numero = teclado.nextInt();
        int resultado = 1;
        for (int contador = 2; contador <= numero; contador++) {
            resultado *= contador;
        }
        System.out.println("El factorial de " + numero + " es: " + resultado);
    }

    /**
     * <h2 style="text-align: center;">Método calcularPotencia</h2><br>
     * <p style="text-align: justify;">
     * Solicita al usuario la base y el exponente y calcula la potencia de manera iterativa.
     * Muestra el resultado de elevar la base al exponente proporcionado.
     * </p><br>
     */
    private static void calcularPotencia() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce la base: ");
        float base = teclado.nextFloat();
        System.out.print("Introduce el exponente: ");
        int exponente = teclado.nextInt();
        float resultado = 1;
        for (int contador = 1; contador <= exponente; contador++) {
            resultado *= base;
        }
        System.out.println("El resultado de " + base + " elevado a " + exponente + " es: " + resultado);
    }
}
