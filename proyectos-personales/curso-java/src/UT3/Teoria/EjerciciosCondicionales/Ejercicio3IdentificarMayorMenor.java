package UT2.EjerciciosCondicionales;

import java.util.Scanner;

public class Ejercicio3IdentificarMayorMenor {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int numero1, numero2;

        System.out.println("Dime el primer número: ");
        numero1 = teclado.nextInt();

        System.out.println("dime el segundo número: ");
        numero2 = teclado.nextInt();

        if (numero1 > numero2) {
            System.out.println("El número " + numero1 + " es mayor que el " + numero2);
        }
        else if (numero2 > numero1){
            System.out.println("El número " + numero2 + " es mayor que el " + numero1);
        } else {
            System.out.println("Los dos números son iguales. ");
        }

    }
}
