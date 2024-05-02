package TerceraEvaluacion.UT7TipoDeColecciones.IntroduccionListas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/**
 * <h1 style="text-align: center;">Introducción a los Tipos de Listas en Java</h1><br>
 * <p style="text-align: justify;">
 * Este programa ofrece una explicación comparativa sobre tres implementaciones de la interfaz List en Java: ArrayList, Vector y LinkedList.
 * Se detallan sus características y diferencias, proporcionando información esencial para elegir la implementación más adecuada según el contexto.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class TiposDeListas {
    public static void main(String[] args) {

        /*
         * ArrayList
         *
         * Los ArrayList almacenan sus elementos de manera contigua en memoria, lo que facilita un
         * acceso rápido a cualquier posición.
         *
         * - Ventajas: Búsqueda eficiente de elementos por índice.
         *
         * - Desventajas: La inserción y eliminación pueden requerir el desplazamiento de elementos,
         * lo que puede ser costoso si la lista es grande.
         *
         * El ArrayList no está sincronizado, lo que implica que no es seguro para el uso en
         * contextos de múltiples hilos sin precauciones adicionales.
         */
        ArrayList <String> lista = new ArrayList<>();

        /*
         * Vector
         *
         * Vector es similar a ArrayList pero con sincronización. Es adecuado para entornos de
         * programación concurrente donde es necesario el acceso de múltiples hilos.
         *
         * - Ventajas: Sincronización incorporada.
         *
         * - Desventajas: La sincronización introduce un sobrecoste
         * que puede no ser necesario en aplicaciones de un solo hilo.
         */
        Vector <String> lista2 = new Vector<>();

        /*
         * LinkedList
         *
         * LinkedList almacena sus elementos en nodos vinculados, lo que permite inserciones y
         * eliminaciones eficientes sin afectar al resto de la estructura.
         *
         * - Ventajas: Inserciones y eliminaciones eficientes, ideales para
         * implementaciones FIFO (First In, First Out).
         *
         * - Desventajas: El acceso a los elementos no es tan rápido como
         * en ArrayList, ya que requiere recorrer la lista desde el principio o el final
         * hasta el elemento deseado.
         *
         * Métodos nuevos útiles:
         *
         * - addFirst / addLast: añade a la lista un elemento al principio / final de la lista.
         * - getFirst / getLast: obtiene / visualiza el elemento al principio / final de la lista.
         * - removeFirst / removeLast: remueve / borra el elemento al principio / final de la lista.
         */
        LinkedList <String> lista3 = new LinkedList<>();

        /*
        * Como desarrolladores debemos ser capaces de identificar cúal lista es más conveniente de usar
        * si deseamos una lista con capacidad de añadir y eliminar elementos con mayor facilidad
        * sacrificando velocidad de búsqueda entonces "LinkedList" parece ser la mejor opción.
        *
        * En cámbio si deseamos una lista más estable en la que no se espera añadir o eliminar datos
        * con demasiada frecuencia, deberíamos considerar usar "ArrayList".
        *
        * Por último los "Vector" al ser algo que no veremos en DAW, en caso de verlo en DAM quedaría
        * más claro como funciona la programación concurrente.
        * */

        // Solo los imprimo para suprimir el "warning" de "not used".
        System.out.println(lista);
        System.out.println(lista2);
        System.out.println(lista3);
    }
}
