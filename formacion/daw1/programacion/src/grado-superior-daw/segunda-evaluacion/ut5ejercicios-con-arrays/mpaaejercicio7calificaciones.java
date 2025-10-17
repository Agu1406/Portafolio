package GradoSuperiorDAW.SegundaEvaluacion.UT5EjerciciosConArrays;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MPAAEjercicio7Calificaciones {
    public static void main(String[] args) {
        // Instancia de la clase Scanner para pedir números por consola
        Scanner teclado = new Scanner(System.in);
        // Arraylist que almacenara las 5 calificaciones
        ArrayList<Integer> calificaciones = new ArrayList<>();
        // Variable que cambiara de valor cada vez que se solicite una calificación.
        int calificacion;
        // Variables para guardar la maxima nota, la minima y el promedio.
        int max, min, promedio = 0;

        // Aviso de lo que se va a solicitar.
        System.out.println("¡Por favor! Ingresa tús calificaciones.");

        // Bucle que solicita 5 veces una calificación y la pushea al array.
        for (int iteracion = 0; iteracion < 5; iteracion++) {
            // Try-Catch que evita introducir algo que no sea un número.
            try {
                System.out.println("Ingresa una calificación. ");
                // Intento pedir una calificación por teclado.
                calificacion = teclado.nextInt();

                // Pushea la calificación dentro del array.
                calificaciones.add(calificacion);
            } catch (InputMismatchException e) {
                System.out.println("¡Error! Solo puedes ingresar números enteros.");
                // Limpia el buffer del teclado.
                teclado.next();
                // Decrementa la iteración para repetirla.
                iteracion--;
            }
        }

        // En "max" y "min" guardamos la primera posición del array para ir comparando
        max = calificaciones.get(0);
        min = calificaciones.get(0);

        // Bucle que reccore todas las posibles posiciones del array.
        for (int posicion = 0; posicion < calificaciones.size(); posicion++) {
            // Si el valor en "posición" es mayor que "max" actualiza el valor.
            if (calificaciones.get(posicion) > max) {
                max = calificaciones.get(posicion);
            }
            // Si el valor en "posición" es menor que "min" actualiza el valor.
            if (calificaciones.get(posicion) < min) {
                min = calificaciones.get(posicion);
            }
            // Suma una por una todas las calificaciones para luego calcular el promedio.
            promedio += calificaciones.get(posicion);
        }

        // Al final del bucle, calculamos el promedio.
        promedio = promedio / calificaciones.size();

        // Imprimimos por consola.
        System.out.println("Nota más alta: " + max);
        System.out.println("Nota más baja: " + min);
        System.out.println("Promedio de notas: " + promedio);
    }
}
