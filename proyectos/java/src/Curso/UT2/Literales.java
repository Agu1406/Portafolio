package Curso.UT2;

public class Literales {
    public static void main(String[] args) {

        // Literales de números enteros.
        int decimal = 42;
        int hexadecimal = 0x2A;
        int binario = 0b101010;

        // Literales de punto flotante
        double normal = 3.14;
        double cientifico = 3.14e2;  // 314.0

        // Literales de carácteres.
        char simple = 'A'; // letra 'A'
        char escape = '\n'; // Salto de linea.
        char unicode = '\u0041'; // Letra 'A' en unicode.

        // Literales de cadena (de texto)
        String texto = "Hola \n Mundo";
        String parrafo = "" +
                "Esto es" +
                "una cadena" +
                "multilina" +
                "de texto";

        System.out.println(parrafo);
    }
}
