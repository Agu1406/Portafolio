package UT7ObjetosAvanzados.Ejercicio3UT7ClaseRectangulo3Avance;

import java.util.Scanner;

/**
 * <h1>Ejercicio N.º2 de la Unidad Teórica 7 de programación en Java</h1>
 * Este ejercicio es una continuación del primero, donde modificamos, añadimos y probamos
 * métodos en la clase "Rectángulo" y su Main, siendo esta la modificación y el testeo
 * de los métodos creados en la clase {@link MPAAEjercicio3UT7}.
 *
 * @author Agu1406 (Agustín)
 * @see MPAAEjercicio3UT7
 * @since 09/03/2024
 */
public class MainRectangulo {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int x1, x2, y1, y2; // Coordenadas para la creación de una estancia.
        String nombrePersonalizado; // Nombre personalizado que puedo dar a cada rectángulo.

        // Solicitud por pantalla / teclado de los datos para crear una estancia "rectángulo".
        System.out.println("Dime la coordenada X1");
        x1 = teclado.nextInt();
        System.out.println("Dime la coordenada Y1");
        y1 = teclado.nextInt();
        System.out.println("Dime la coordenada X2");
        x2 = teclado.nextInt();
        System.out.println("Dime la coordenada Y2");
        y2 = teclado.nextInt();

        teclado.nextLine(); // Consumimos un salto de línea para limpiar el teclado.
        System.out.println("¿Como deseas llamar a la figura?");
        nombrePersonalizado = teclado.nextLine();

        // Creación de una estancia con datos proporcionados por teclado.
        MPAAEjercicio3UT7 nuevoRectangulo1 = new MPAAEjercicio3UT7(nombrePersonalizado, x1, x2, y1, y2);

        // Creación de un par de estancias ingresando los datos directamente en el código.
        MPAAEjercicio3UT7 nuevoRectangulo2 = new MPAAEjercicio3UT7("experimento2", 0, 25, 0, 25);
        MPAAEjercicio3UT7 nuevoRectangulo3 = new MPAAEjercicio3UT7("experimento3", 30, 55, 0, 25);

        // Imprimo las tres estancias creadas con el método toString personalizado que he creado.
        System.out.println(nuevoRectangulo1);
        System.out.println(nuevoRectangulo2);
        System.out.println(nuevoRectangulo3);

        /* A partir de este punto empiezan las pruebas y el testeo de los métodos añadidos
         * como extensión y mejoramiento del Ejercicio N.º1 del UT7, por lo tanto: */

        // Probamos el getNombre para obtener el nombre de una estancia en específico y lo imprimo.
        System.out.println(nuevoRectangulo1.getNombre());

        // Probamos el setNombre modificando e imprimiendo el nombre de una estancia.
        System.out.println("Introduce el nuevo nombre del Rectángulo N.º2: ");
        String nuevoNombre = teclado.nextLine();
        nuevoRectangulo2.setNombre(nuevoNombre);
        // Imprimimos el nuevo nombre.
        System.out.println(nuevoRectangulo2.getNombre());

        // Probamos el método de calcularSuperficie con la estancia N.º3.
        System.out.println("La superficie del Rectángulo 3 es: " + nuevoRectangulo3.calcularSuperficie());

        // Probamos el método de calcularPerimetro con la estancia N.º3.
        System.out.println("El perimetro del Rectángulo 3 es: " + nuevoRectangulo3.calcularPerimetro());

        /* Probamos el método que permite desplazar una estancia "rectángulo", para saber
         * si funciona, imprimimos sus coordenadas originales, lo desplazamos y volvemos
         * a imprimir para visualizar si efectivamente han cambiado.*/
        System.out.println(nuevoRectangulo1);
        nuevoRectangulo1.desplazarRectangulo();
        System.out.println(nuevoRectangulo1);

        // Probamos/obtenemos el número exacto de estancias creadas, da igual con que estancia lo invoque.
        int estanciasCreadas = nuevoRectangulo1.numeroDeRectangulosCreados();
        System.out.println("El número exacto de rectángulos creados es: " + estanciasCreadas);

        /* Primer constructor añadido en el ejercicio N.º3, sin parámetros
        * el cual crea una estancia con valores por defecto (0,0) y (1,1) */
        MPAAEjercicio3UT7 nuevoConstructor1 = new MPAAEjercicio3UT7();

        /* Segundo consctructor añadido en el ejercicio N.º3, recibe como
        * parámetros la base y la altura del rectángulo y dibuja un
        * rectángulo empezando en (0,0) */
        MPAAEjercicio3UT7 nuevoConsctructor2 = new MPAAEjercicio3UT7(20, 20);
    }
}
