package PrimeraEvaluacion.UT2EjerciciosIniciales;

/**
 * Ejercicio N.º 10 - Conversiones entre caracteres y enteros.
 * Este programa demuestra cómo se pueden guardar caracteres en variables de tipo int
 * y viceversa, explorando la representación numérica de los caracteres según la tabla ASCII.
 * Se espera que el usuario comprenda cómo se representan los caracteres en términos numéricos.
 *
 * @author Agu1406 (Agustín)
 * @since 06/10/2023
 */
public class MPAAEjercicio10 {
    public static void main(String[] args) {
        // Guardar un carácter en una variable int (conversión implícita)
        char miCaracter = 'A';
        int codigoAscii = miCaracter;
        System.out.println("El código ASCII de '" + miCaracter + "' es: " + codigoAscii);

        // Guardar un valor int en una variable char (casting explícito necesario)
        int numeroAscii = 65;
        miCaracter = (char) numeroAscii;
        System.out.println("El carácter correspondiente al código ASCII '" + numeroAscii + "' es: " + miCaracter);
    }
}
