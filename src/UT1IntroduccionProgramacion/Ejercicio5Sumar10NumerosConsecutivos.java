package UT1IntroduccionProgramacion;

import java.util.Scanner;

public class Ejercicio5Sumar10NumerosConsecutivos {
    public static void main(String[] args) {
        // Creación de un Scanner de nombre teclado para introducir datos.
        Scanner teclado = new Scanner(System.in);
        // Creación de las variables que usare en este programa.
        int numerUsuario; // Es el número que el usuario me proporcionara, 10 veces.
        int contador = 10; // Es quien limita la cantidad de veces que pedire al usuario un número.
        int resultado = 0; // Es la variable que guardara su propio valor + el número proporcionado.

        /* La logíca de este ejercicio es que cada vez que el usuario porporcione un número
        este se sume al resultado, así, en cada vuelta, cada vez que proporcione un número
        este se sumara al resultado de la suma anterior del número más el valor inicial o
        anterior almecenado en "resultado". */
        while (contador > 0) {
            // Imprimo un mensaje solicitando un número.
            System.out.println("¡Introduce un número!");
            // Uso el Scanner para poder introducirlo manualmente.
            numerUsuario = teclado.nextInt();
            // Le sumo al resultado su propio valor + el número dado por el usuario.
            resultado = resultado + numerUsuario;
            // Le digo al usuario en cuanto va la suma total hasta ahora.
            System.out.println("La suma hasta el momento es: "+resultado);
            // al contador le quito "1" para que despues de "X" interacciones, el bucle pare.
            contador = contador - 1;
        }
    }
}
