package UT1IntroduccionProgramacion;
import java.util.Scanner;

/**
 * Mi segundo programa en Java, está vez el ejercicio era:
 * - Encontrar el número mayor, mediano y menor de tres solicitados por pantalla.
 * @author Agu1406 (Agustín)
 * @since 22/09/2023
 */
public class Ejercicio2EncontrarMayorMedioMenor {
    public static void main(String[] args) {
        // Creación de un Scanner teclado para la introducción de datos.
        Scanner teclado = new Scanner(System.in);

        // Creación de las tres varianles que pedire por pantalla.
        int numero1;
        int numero2;
        int numero3;

        // Pedir al usuario el primer número
        System.out.println("Introduce el primer numero:");
        numero1 = teclado.nextInt();

        // Pedir al usuario el segundo número
        System.out.println("Introduce el segundo numero:");
        numero2 = teclado.nextInt();

        // Pedir al usuario el tercer número
        System.out.println("Introduce el tercer numero:");
        numero3 = teclado.nextInt();

        /* Determinar y mostrar el orden de los números: mayor, medio y menor, esto
        * mediante un sistema de if-else que verifica que número es el mayor y que
        * número es el menor, el del medio, por descarte de no ser ni el mayor
        * ni el menor queda siempre como el del medio. */
        if (numero1 > numero2 && numero2 > numero3) {
            System.out.println("El número mayor es " + numero1 + ", el del medio es " + numero2 + ", y el menor es " + numero3);
        } else if (numero1 > numero3 && numero3 > numero2) {
            System.out.println("El número mayor es " + numero1 + ", el del medio es " + numero3 + ", y el menor es " + numero2);
        } else if (numero2 > numero1 && numero1 > numero3) {
            System.out.println("El número mayor es " + numero2 + ", el del medio es " + numero1 + ", y el menor es " + numero3);
        } else if (numero2 > numero3 && numero3 > numero1) {
            System.out.println("El número mayor es " + numero2 + ", el del medio es " + numero3 + ", y el menor es " + numero1);
        } else if (numero3 > numero2 && numero2 > numero1) {
            System.out.println("El número mayor es " + numero3 + ", el del medio es " + numero2 + ", y el menor es " + numero1);
        } else if (numero3 > numero1 && numero1 > numero2) {
            System.out.println("El número mayor es " + numero3 + ", el del medio es " + numero1 + ", y el menor es " + numero2);
        }

        // Mensaje que agregue al final que no aporta nada al programa excepto elegancia.
        System.out.println("¡Gracias por usar nuestro programa, hasta luego!");
    }
}