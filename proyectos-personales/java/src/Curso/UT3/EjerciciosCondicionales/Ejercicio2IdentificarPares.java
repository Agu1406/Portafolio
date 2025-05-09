package Curso.UT3.EjerciciosCondicionales;

import java.util.Scanner;

public class Ejercicio2IdentificarPares {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int numero;

        numero = teclado.nextInt();

        if (numero % 2 == 0 ) {
            System.out.println("El número es par.");
        } else {
            System.out.println("El número impar.");
        }
    }
}
