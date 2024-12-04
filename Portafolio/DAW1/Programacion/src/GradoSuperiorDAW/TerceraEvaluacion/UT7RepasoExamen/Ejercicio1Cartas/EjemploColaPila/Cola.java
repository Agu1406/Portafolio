package GradoSuperiorDAW.TerceraEvaluacion.UT7RepasoExamen.Ejercicio1Cartas.EjemploColaPila;

import java.util.Iterator;
import java.util.LinkedList;

public class Cola {
    public static void main(String[] args) {
        /*
         * - offer(elemento): Añade un elemento al final de la lista, simula una cola.
         * - poll(): Borra y devuelve el primer elemento de la lista.
         * - peek(): Devuelve el primer elemento de la lista sin borrarlo.
         * - isEmpty(): Verifica si la lista está vacía.
         */

        // Cola Starbucks, usamos LinkedList para simular la cola
        LinkedList<String> colaCafes = new LinkedList<>();

        // Añadimos elementos a la cola usando add y offer
        colaCafes.add("Pepe1");   // Añade al final de la lista
        colaCafes.add("Pepe2");   // Añade al final de la lista
        colaCafes.add("Pepe3");   // Añade al final de la lista
        colaCafes.offer("Pepe4"); // Añade al final de la lista
        colaCafes.offer("Pepe5"); // Añade al final de la lista

        // Mostramos todos los elementos de la cola
        System.out.println(colaCafes); // Imprime la lista completa
        System.out.println();

        // Iteramos y mostramos cada elemento de la cola usando un bucle for-each
        for (String cliente : colaCafes) {
            System.out.println("El nombre es: " + cliente);
        }

        System.out.println();

        // Iteramos y mostramos cada elemento de la cola usando un Iterator
        Iterator<String> it = colaCafes.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // Método poll => combina get(0) y remove(0)
        // poll(): Borra y devuelve el primer elemento de la lista
        System.out.println("Poll");
        if (colaCafes.isEmpty()) {
            System.out.println("listaVacia");
        } else {
            String turno = colaCafes.poll(); // Obtiene y elimina el primer elemento
            System.out.println("Turno actual: " + turno); // Imprime el elemento eliminado
            System.out.println("Cola: " + colaCafes); // Imprime la lista restante
        }

        // Método peek => solo get(0)
        // peek(): Devuelve el primer elemento de la lista sin borrarlo
        System.out.println("Peek");
        if (colaCafes.isEmpty()) {
            System.out.println("listaVacia");
        } else {
            String turno = colaCafes.peek(); // Obtiene pero no elimina el primer elemento
            System.out.println("Turno actual: " + turno); // Imprime el primer elemento
            System.out.println("Cola: " + colaCafes); // Imprime la lista completa sin cambios
        }

    }
}
