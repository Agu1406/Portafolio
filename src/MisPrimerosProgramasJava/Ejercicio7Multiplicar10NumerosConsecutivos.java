package MisPrimerosProgramasJava;

import java.util.Scanner;

public class Ejercicio7Multiplicar10NumerosConsecutivos {
    public static void main(String[] args) {
        // Creación de un Scanner de nombre teclado para introducir datos.
        Scanner teclado = new Scanner(System.in);
        // Creación de las variables que usáre en este programa.
        int numeroUsuario; // Es el número que el usuario me proporcionara, 10 veces.
        int contador = 10; // Es quien limita la cantidad de veces que solicítare al usuario un número.
        int resultado = 1; // Es la variable que guardara su propio valor * el número proporcionado.

        /* La logíca de este ejercicio es que cada vez que el usuario proporcione un número
        este se multiplique por el resultado, así, en cada vuelta, cada vez que proporcione
        un número este se multiplicara con el resultado y así sucesivamente.*/
        while (contador > 0) {
            // Imprimo un mensaje solicitando un número.
            System.out.println("¡Introduce un número!");
            // Uso el Scanner para poder introducirlo manualmente.
            numeroUsuario = teclado.nextInt();
            // Le sumo al resultado su propio valor + el número dado por el usuario.
            resultado = resultado * numeroUsuario;
            // Le digo al usuario el resultado total de la multiplicación hasta el momento.
            System.out.println("La multiplicación hasta el momento es: " + resultado);
            // al contador le quito "1" para que después de "X" interacciones, el bucle pare.
            contador = contador - 1;
        }
    }
}
