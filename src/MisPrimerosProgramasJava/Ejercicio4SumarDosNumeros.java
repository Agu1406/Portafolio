package MisPrimerosProgramasJava;

import java.util.Scanner;

/**
 * Cuarto ejercicio de mis primeros programas en Java, en este
 * exploramos la suma de dos numeros enteros y como Java se
 * encarga de "guardar" los resultados de las operaciones
 * matematicas.
 *
 * @author Agu1406 (Agustín)
 * @since 22/09/2023
 */
public class Ejercicio4SumarDosNumeros {
    public static void main(String[] args) {
        // Creación de un Scanner llamado teclado para introducir datos al programa.
        Scanner teclado = new Scanner(System.in);

        // Creación de las variables de tipo "int" que usaremos en el programa.
        int numero1; // El primer número que introduce el usuario.
        int numero2; // El segundo número que introduce el usuario.
        int resultado; // El lugar donde guardamos el resultado de la suma.

        // Solicitud del primer número por pantalla y escritura del mismo en el teclado.
        System.out.println("¡Hola! Introduce un número: ");
        numero1 = teclado.nextInt();

        // Solicitud del segundo número por pantalla y escritura del mismo en el teclado.
        System.out.println("¡Perfecto! Ahora introduce otro número: ");
        numero2 = teclado.nextInt();

        // Hago la suma y guardo el "resultado" de la misma en mi variable "resultado
        resultado = numero1 + numero2;

        // Imprimo en pantalla la operación
        System.out.println(numero1+" + "+numero2+" = "+resultado);
    }
}
