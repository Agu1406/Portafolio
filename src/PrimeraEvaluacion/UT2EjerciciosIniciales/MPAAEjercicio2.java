package PrimeraEvaluacion.UT2EjerciciosIniciales;

/**
 * Ejercicio N.º 2- Prueba la diferencia entre el operador ++, antes o después de una variable,
 * utilizando como referencia las siguientes operaciones:
 * a. System.out.println(i++);
 * b. System.out.println(++i);
 *
 * @author Agu1406 (Agustín)
 * @since 06/10/2023
 */
public class MPAAEjercicio2 {
    public static void main(String[] args) {
        /* Cada vez que experimentamos, como el "++" o "--" cambian el valor de la variable
        * debemos re-asignárle su valor otra vez a modo de poder apreciar realmente los
        * cambios que ocurren cuando los operadores se agregan antes de declarar la
        * variable o después.*/

        // En el primer caso, se imprime un 3 y luego es que el "++" hace efecto, por eso si se imprime otra vez su valor aumentó.
        int probandoOperadores = 3;
        System.out.println("Con el ++ después de declarar la variable se ve así: " + probandoOperadores++
                + " y ahora que ya fue declarada el ++ hace efecto y su nuevo valor es: " + probandoOperadores);

        // En el primer caso, se imprime un 4 directamente, porque el valor de la variable cambia antes de ser impresa.
        probandoOperadores = 3;
        System.out.println("Con el ++ antés de declarar la variable hace efecto antes de imprimir su " +
                "valor y por eso se ve así: " + ++probandoOperadores);

        // En el primer caso, se imprime un 3 y luego es que el "--" hace efecto, por eso si se imprime otra vez su valor disminuyó.
        probandoOperadores = 3;
        System.out.println("Con el -- después de declarar la variable: " + probandoOperadores--
                + " y ahora que ya fue declarada el -- hace efecto y su nuevo valor es: " + probandoOperadores);

        // En el primer caso, se imprime un 2 directamente, porque el valor de la variable cambia antes de ser impresa.
        probandoOperadores = 3;
        System.out.println("Con el -- antés de declarar la variable hace efecto antes de imprimir su " +
                "valor y por eso se ve así: " + ++probandoOperadores);
    }
}
