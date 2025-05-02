package UT3.Teoria.EjerciciosCondicionales;

import java.util.Scanner;

public class Ejercicio4DividirEntreCero {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int diviendo, divisor, resultado;

        System.out.println("Dime el dividiendo: ");
        diviendo = teclado.nextInt();

        do {
            System.out.println("Dime el divisor: ");
            divisor = teclado.nextInt();

            if (divisor == 0) {
                System.out.println("¡Error! El divisor no puede ser cero");
            } else {
                resultado = diviendo / divisor;
                System.out.println("El resultado es: " + resultado);
            }

        } while (divisor == 0);

        System.out.println("¡Hasta luego!");

    }
}
