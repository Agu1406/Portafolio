package Curso.UT3.EjerciciosCondicionales;

import java.util.Scanner;

public class Ejercicio1Condicional {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int edad, numero;

        System.out.println("¿Cuantos años tienes? ");
        edad = teclado.nextInt();

        if (edad <= 0) {
            System.out.println("No has nacido.");
        } else if (edad <= 5) {
            System.out.println("Eres un niño.");
        } else if (edad <= 10) {
            System.out.println("Eres un niño grande. ");
        } else if (edad <= 15) {
            System.out.println("Eres un adolescente. ");
        } else if (edad <= 20) {
            System.out.println("Eres un adulto joven. ");
        } else if (edad <= 50) {
            System.out.println("Eres un aldulto mayor. ");
        } else {
            System.out.println("Estás jubilado. ");
        }

        numero = teclado.nextInt();

        if (numero == 0) {
            System.out.println("El número es cero. ");
        } else if (numero > 0) {
            System.out.println("El número es positivo.");
        } else {
            System.out.println("El número es negativo.");
        }
    }
}
