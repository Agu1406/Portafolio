package UT1IntroduccionProgramacion;

import java.util.Scanner;

public class Ejercicio3EncontrarMayorMenorEntre10Numeros {
    public static void main(String[] args) {
        // Creación de un teclado para introducción de datos en mi programa
        Scanner teclado = new Scanner(System.in);

        // Creo las variables de tipo entero que usaré en el programa.
        int contador = 10; // Defino cuantas veces pido un número por pantalla.
        int numeroUsuario; // El número que el usuario introducirá 10 veces
        int numeroMayor; // El número donde almacenaré el mayor de todos.
        int numeroMenor; // El número donde almacenaré el menor de todos.

        // Solicito por pantalla un numero
        System.out.println("¡Hola! Introduce un numero.");
        numeroUsuario = teclado.nextInt();

        // La primera vez que introduce un número, este sera el mayor y el menor.
        numeroMayor = numeroUsuario;
        numeroMenor = numeroUsuario;

        /* Bucle while que solicitara otro número otras nueve veces, en cada
        * repetición del bucle, se le irá restando "1" al contador, para
        * asegurarse de que el bucle no sea infinito. */
        while (contador > 0) {
            // Solicito el siguiente número por pantalla en cada repetición
            System.out.println("¡Perfecto! Introduce otro número.");
            numeroUsuario = teclado.nextInt();

            // Si el número que introduce el usuario es mayor al mayor actual, lo remplaza.
            if (numeroUsuario > numeroMayor) {
                numeroMayor = numeroUsuario;
            }

            // Si el número que introduce el usuario es menor al menor actual, lo remplaza.
            if (numeroUsuario < numeroMayor) {
                numeroMenor = numeroUsuario;
            }

            // se le resta "1" al contador en cada repetición para que no sea infinito.
            contador = contador - 1;
        }

        /* Para cuando el programa llega aquí ya el bucle se ha ejecutado 9 veces y muestra por
        * pantalla ambos, el número mayor de todos los introducidos y el menor también de todos
        * los introducidos. */
        System.out.println("El número mayor de todos los introducidos es: "+numeroMayor+" y el menor de todos es: "+numeroMenor);
    }
}
