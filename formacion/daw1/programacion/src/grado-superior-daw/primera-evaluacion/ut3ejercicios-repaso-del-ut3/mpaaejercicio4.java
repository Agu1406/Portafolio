package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosRepasoDelUT3;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase Contador de Monedas</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario el número de monedas que posee de diferentes
 * denominaciones y calcula el total en euros y céntimos.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio4 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);  // Crear una instancia de Scanner para leer la entrada del usuario.

        // Solicitar al usuario el número de monedas de cada denominación.
        int monedas2Euros = solicitarMonedas("monedas de 2 euros", teclado);
        int monedas1Euro = solicitarMonedas("monedas de 1 euro", teclado);
        int monedas50Cent = solicitarMonedas("monedas de 50 céntimos", teclado);
        int monedas20Cent = solicitarMonedas("monedas de 20 céntimos", teclado);
        int monedas10Cent = solicitarMonedas("monedas de 10 céntimos", teclado);

        // Calcular el dinero total.
        double totalEuros = calcularTotalEuros(monedas2Euros, monedas1Euro, monedas50Cent, monedas20Cent, monedas10Cent);

        // Mostrar el dinero total.
        System.out.printf("El dinero total que tienes es: %.2f euros.%n", totalEuros);
    }

    /**
     * <h2 style="text-align: center;">Método solicitarMonedas</h2><br>
     * <p style="text-align: justify;">
     * Solicita al usuario el número de monedas de una denominación específica.
     * Valida que la cantidad no sea negativa, volviendo a pedir en ese caso.
     * </p><br>
     *
     * @param tipoMoneda La denominación de la moneda para la cual se solicita el conteo.
     * @param teclado    El Scanner que se utilizará para leer la entrada del usuario.
     * @return El número de monedas de la denominación especificada.
     */
    private static int solicitarMonedas(String tipoMoneda, Scanner teclado) {
        int cantidad;
        do {
            System.out.printf("Introduce el número de %s: ", tipoMoneda);
            cantidad = teclado.nextInt();
            if (cantidad < 0) {
                System.out.println("No se pueden introducir cantidades negativas. Por favor, intenta de nuevo.");
            }
        } while (cantidad < 0);
        return cantidad;
    }

    /**
     * <h2 style="text-align: center;">Método calcularTotalEuros</h2><br>
     * <p style="text-align: justify;">
     * Calcula el dinero total en euros a partir del número de monedas proporcionadas.
     * </p><br>
     *
     * @param monedas2Euros El número de monedas de 2 euros.
     * @param monedas1Euro  El número de monedas de 1 euro.
     * @param monedas50Cent El número de monedas de 50 céntimos.
     * @param monedas20Cent El número de monedas de 20 céntimos.
     * @param monedas10Cent El número de monedas de 10 céntimos.
     * @return El total de dinero calculado en euros.
     */
    private static double calcularTotalEuros(int monedas2Euros, int monedas1Euro, int monedas50Cent, int monedas20Cent, int monedas10Cent) {
        return monedas2Euros * 2.0 + monedas1Euro * 1.0 + monedas50Cent * 0.50 + monedas20Cent * 0.20 + monedas10Cent * 0.10;
    }
}
