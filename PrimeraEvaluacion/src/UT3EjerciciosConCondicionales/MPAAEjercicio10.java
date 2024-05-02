package UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase DadoOpuesto</h1><br>
 * <p style="text-align: justify;">
 * Este programa pide al usuario el resultado de lanzar un dado de seis caras y muestra el número
 * en letras de la cara opuesta. Las caras opuestas de un dado son 1-6, 2-5, y 3-4.
 * Utiliza estructuras switch-case y if-else para determinar el resultado.
 * </p><br>
 *
 * @version 1.0
 * @since 19/10/24
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio10 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduzca número del dado (1-6): ");
        int numero = teclado.nextInt();

        // Uso de switch-case para determinar el número opuesto
        System.out.println("Usando switch-case:");
        switch (numero) {
            case 1:
                System.out.println("En la cara opuesta está el seis.");
                break;
            case 2:
                System.out.println("En la cara opuesta está el cinco.");
                break;
            case 3:
                System.out.println("En la cara opuesta está el cuatro.");
                break;
            case 4:
                System.out.println("En la cara opuesta está el tres.");
                break;
            case 5:
                System.out.println("En la cara opuesta está el dos.");
                break;
            case 6:
                System.out.println("En la cara opuesta está el uno.");
                break;
            default:
                System.out.println("ERROR: número incorrecto.");
                break;
        }

        // Uso de if-else para determinar el número opuesto
        System.out.println("Usando if-else:");
        if (numero < 1 || numero > 6) {
            System.out.println("ERROR: número incorrecto.");
        } else {
            if (numero == 1) {
                System.out.println("En la cara opuesta está el seis.");
            } else if (numero == 2) {
                System.out.println("En la cara opuesta está el cinco.");
            } else if (numero == 3) {
                System.out.println("En la cara opuesta está el cuatro.");
            } else if (numero == 4) {
                System.out.println("En la cara opuesta está el tres.");
            } else if (numero == 5) {
                System.out.println("En la cara opuesta está el dos.");
            } else { // numero == 6
                System.out.println("En la cara opuesta está el uno.");
            }
        }
    }
}
