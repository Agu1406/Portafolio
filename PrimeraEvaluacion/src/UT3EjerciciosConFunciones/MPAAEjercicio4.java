package UT3EjerciciosConFunciones;

/**
 * <h1 style="text-align: center;">Clase GeneradorAleatorios</h1><br>
 * <p style="text-align: justify;">
 * Este programa incluye una función que genera un número aleatorio entre dos límites especificados.
 * La función 'generarAleatorio' utiliza Math.random() para generar el número y es invocada repetidamente
 * para demostrar su funcionalidad generando 100 números aleatorios entre 1 y 6.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio4 {

    public static void main(String[] args) {
        int numeroInicial = 1;
        int numeroFinal = 6;

        System.out.println("Generando 100 números aleatorios entre " + numeroInicial + " y " + numeroFinal + ":");
        for (int i = 0; i < 100; i++) {
            int aleatorio = generarAleatorio(numeroInicial, numeroFinal);
            System.out.print(aleatorio + " ");
        }
        System.out.println();  // Añade un salto de línea al final para limpiar la salida.
    }

    /**
     * Genera un número aleatorio entre dos límites.
     *
     * @param numeroInicial El límite inferior del rango (incluido).
     * @param numeroFinal El límite superior del rango (incluido).
     * @return Un número aleatorio dentro del rango especificado.
     */
    public static int generarAleatorio(int numeroInicial, int numeroFinal) {
        return (int) (Math.random() * (numeroFinal - numeroInicial + 1)) + numeroInicial;
    }
}
