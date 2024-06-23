package GradoSuperiorDAW.PrimeraEvaluacion.UT2EjerciciosIniciales;

/**
 * Ejercicio N.º 8 - Uso de constantes.
 * Este programa demuestra el uso de constantes en Java.
 * Intentaremos cambiar el valor de una constante para ver qué ocurre.
 * También exploraremos si es necesario asignar un valor a la constante en la línea en que la creamos.
 * Las constantes en Java se definen con la palabra clave 'final'.
 * Intentar reasignar un valor a una constante resultará en un error de compilación.
 * La asignación inicial puede hacerse en la declaración o en el constructor si es un campo de clase.
 *
 * @author Agu1406 (Agustín)
 * @since 06/10/2023
 */
public class MPAAEjercicio8 {

    public static void main(String[] args) {
        // Declaración de una constante y asignación de su valor.
        final int MI_CONSTANTE = 10;
        System.out.println("Valor inicial de la constante: " + MI_CONSTANTE);

        /* Intento de cambiar el valor de la constante, si intento Des-comentar la
        * siguiente línea causará un error de compilación */
        // MI_CONSTANTE = 20;

        /* ¿Es obligatorio asignar un valor a la constante en la línea que la creamos?
        * Sí, en el caso de variables locales. Las siguientes líneas también
        * causarían un error. */
        // final int OTRA_CONSTANTE;
        // OTRA_CONSTANTE = 10;
    }
}
