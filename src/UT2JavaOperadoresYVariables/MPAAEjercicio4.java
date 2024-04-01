package UT2JavaOperadoresYVariables;

/**
 * Ejercício N.º4 - Operadores aritméticos. Escribe un programa que genere dos variables
 * numéricas y las sume, reste, multiplique y divida; mostrando los valores por consola.
 *
 * @author Agu1406 (Agustín)
 * @since 06/10/2023
 */
public class MPAAEjercicio4 {
    public static void main(String[] args) {
        // Creamos dos variables del tipo int con dos números elegidos por mí.
        int num1 = 4;
        int num2 = 6;

        // Realizamos y mostramos la suma sin guardar el resultado de la misma en ningún sitio.
        System.out.println("La suma de " + num1 + " y " + num2 + " es: " + (num1 + num2));

        // Realizamos y mostramos la resta sin guardar el resultado de la misma en ningún sitio.
        System.out.println("La resta de " + num1 + " y " + num2 + " es: " + (num1 - num2));

        // Realizamos y mostramos la multiplicación sin guardar el resultado de la misma en ningún sitio.
        System.out.println("La multiplicación de " + num1 + " por " + num2 + " es: " + (num1 * num2));

        // Realizamos y mostramos la división sin guardar el resultado de la misma en ningún sitio.
        System.out.println("La división de " + num1 + " entre " + num2 + " es: " + (num1 / num2));

        // Realizamos y mostramos el resto de la división sin guardar el resultado en ningún sitio.
        System.out.println("El resto de "+ num1 + " entre " + num2 + " es: " + (num1 % num2));
    }
}
