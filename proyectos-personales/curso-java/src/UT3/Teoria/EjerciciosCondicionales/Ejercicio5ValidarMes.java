package UT3.Teoria.EjerciciosCondicionales;

import java.util.Scanner;

/***
 * Ejercicio Condicional N.º5 - Pide al usuario un mes (número)
 * y le dice si el mes tiene 30 días, 31 días o 28 días.
 */
public class Ejercicio5ValidarMes {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int mes;

        System.out.println("Dime un mes: ");
        mes = teclado.nextInt();

        if (mes < 1 || mes > 12) {
            System.out.println("¡Error! Solo existen meses del 1 al 12, intentelo de nuevo. ");
        } else {

            switch (mes) {
                case 4:
                case 6:
                case 9:
                case 11:
                    System.out.println("El mes " + mes + " tiene 30 días. ");
                    break;
                case 2:
                    System.out.println("El mes 2 tiene 28 días. ");
                    break;
                default:
                    System.out.println("El mes " + mes + " tiene 31 días.");
                    break;
            }
        }
    }
}
