package GradoSuperiorDAW.PrimeraEvaluacion.UT2EjerciciosIniciales;

/**
 * Ejercicio N.º5 - Operadores aritméticos, usando println y printf. En el ejemplo vemos un
 * programa básico que utiliza operadores aritméticos. Observa que usamos tanto
 * System.out.println como System.out.printf para mostrar por pantalla un texto
 * formateado. Primero indicamos cómo queremos que salga el texto, y después
 * las variables que se mostrarán con el texto. Con %f nos referimos a una
 * variable de tipo float, con %d un entero en base decimal, etc.
 *
 * @author Agu1406 (Agustín)
 * @since 06/10/2023
 */
public class MPAAEjercicio5 {
    public static void main(String[] args) {
        short x = 7;
        int y = 5;
        float f1 = 13.5f;
        float f2 = 8f;

        System.out.println("El valor de x es " + x + " y el valor de y es " + y);
        System.out.println("El resultado de x + y es " + (x + y));
        System.out.println("El resultado de x - y es " + (x - y));
        System.out.println("El resto de la división entera: x % y = " + (x % y));
        System.out.printf("El valor de f1 es %.2f y el de f2 es %.2f\n", f1, f2);
        System.out.println("El resultado de f1 + f2 es " + (f1 + f2));
    }
}
