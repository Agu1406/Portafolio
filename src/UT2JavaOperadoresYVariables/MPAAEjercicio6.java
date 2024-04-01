package UT2JavaOperadoresYVariables;

/**
 * Ejercicio N.º 6 - Operadores de asignación.
 * Este programa demuestra el uso de operadores de asignación y asignación combinada.
 * Realiza operaciones de suma, resta, multiplicación y división mediante asignación
 * directa y luego con asignación combinada, mostrando los resultados en consola.
 *
 * @author Agu1406 (Agustín)
 * @since 06/10/2023
 */
public class MPAAEjercicio6 {
    public static void main(String[] args) {
        // Inicialización de variables con valores distintos para la demostración.
        int x = 10; // Usaremos 10 para x, diferente del valor inicial en el ejemplo.
        int y = 2;  // Usaremos 2 para y, diferente del valor inicial en el ejemplo.

        // Operadores de asignación simple
        System.out.println("Valor inicial de x: " + x);
        System.out.println("Valor inicial de y: " + y);

        // Operadores de asignación combinados
        System.out.printf("El valor de x es %d y el valor de y es %d\n", x, y);

        // Suma combinada
        x += y; // Esto es equivalente a x = x + y
        System.out.println("Suma combinada: x += y --> x vale " + x);

        // Resta combinada
        x -= y; // Vuelve a x al valor original con x = x - y
        System.out.println("Resta combinada: x -= y --> x vale " + x);

        // Producto combinado
        x *= y; // Multiplica x por y con x = x * y
        System.out.println("Producto combinado: x *= y --> x vale " + x);

        // División combinada
        x /= y; // Divide x entre y con x = x / y
        System.out.println("División combinada: x /= y --> x vale " + x);

        // Resto combinado
        x %= y; // Obtiene el resto de x dividido entre y con x = x % y
        System.out.println("Resto combinado: x %= y --> x vale " + x);
    }
}
