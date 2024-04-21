package UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase CalculadoraSimple</h1><br>
 * <p style="text-align: justify;">
 * Este programa pide al usuario que introduzca dos números y una operación aritmética válida
 * (suma, resta, multiplicación o división) y muestra el resultado de la operación.
 * Se verifica que no se realice una división por cero, mostrando un mensaje de error si este caso ocurre.
 * Además, asegura que se introduzca una operación válida usando un bucle do-while.
 * </p><br>
 *
 * @version 1.0
 * @since 19/10/24
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio11 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el primer número: ");
        double num1 = teclado.nextDouble();

        System.out.print("Introduce el segundo número: ");
        double num2 = teclado.nextDouble();

        char operacion;
        double resultado = 0;
        boolean esValido;

        do {
            System.out.print("Introduce la operación (+, -, *, /): ");
            operacion = teclado.next().charAt(0);
            esValido = true; // Se asume que la operación es válida a menos que se compruebe lo contrario

            switch (operacion) {
                case '+':
                    resultado = num1 + num2;
                    break;
                case '-':
                    resultado = num1 - num2;
                    break;
                case '*':
                    resultado = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        System.out.println("Error: No se puede dividir por cero.");
                        esValido = false;
                    }
                    break;
                default:
                    System.out.println("Operación no reconocida. Usa +, -, * o /.");
                    esValido = false;
                    break;
            }
        } while (!esValido);

            System.out.println("Resultado: " + resultado);
    }
}
