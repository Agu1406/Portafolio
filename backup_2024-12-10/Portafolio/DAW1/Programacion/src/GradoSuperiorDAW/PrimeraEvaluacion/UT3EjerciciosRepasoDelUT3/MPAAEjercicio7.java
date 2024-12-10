package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosRepasoDelUT3;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase Cálculo de Comisiones</h1><br>
 * <p style="text-align: justify;">
 * Este programa calcula las comisiones de un vendedor basadas en el importe de sus ventas
 * y determina el total a recibir considerando su sueldo base más las comisiones.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio7 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        float sueldoBase;
        float venta;
        float totalComisiones = 0;

        // Pedir al usuario el sueldo base
        System.out.print("Introduce el sueldo base del vendedor: ");
        sueldoBase = teclado.nextFloat();

        // Procesar las tres ventas
        for (int contador = 1; contador <= 3; contador++) {
            System.out.print("Introduce el importe de la venta " + contador + ": ");
            venta = teclado.nextFloat();
            totalComisiones += calcularComision(venta);
        }

        // Mostrar los resultados
        System.out.println("Total de comisiones: " + totalComisiones);
        System.out.println("Total a recibir (sueldo base + comisiones): " + (sueldoBase + totalComisiones));
    }

    /**
     * <h2 style="text-align: center;">Calcular Comisión</h2><br>
     * <p style="text-align: justify;">
     * Calcula la comisión del vendedor basada en el importe de una venta, considerando un
     * 10% de comisión sobre el importe.
     * </p><br>
     *
     * @param venta El importe de la venta realizada.
     * @return La comisión correspondiente a la venta.
     */
    private static float calcularComision(float venta) {
        float porcentajeComision = 0.10f; // 10% de comisión
        return venta * porcentajeComision;
    }
}
