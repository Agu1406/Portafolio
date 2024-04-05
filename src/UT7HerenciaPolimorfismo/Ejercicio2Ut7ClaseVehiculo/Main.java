package UT7HerenciaPolimorfismo.Ejercicio2Ut7ClaseVehiculo;
/**
 * <h1 style="text-align: center">Main para la demostración del sistema de alquiler de vehículos</h1>
 * <br>
 * <p style="text-align: justify">
 * Este Main demuestra la creación, alquiler, devolución y visualización de estados de instancias de {@link Turismo} y {@link Camion}. Se realizan pruebas específicas para mostrar la funcionalidad y el polimorfismo de la jerarquía de clases de vehículos.
 * </p>
 * <br>
 * <p style="text-align: justify">
 * Se inicia con la creación de instancias de turismo y camión, seguido por procesos de alquiler y devolución para cada uno. Finalmente, se muestra el estado de los vehículos después de cada operación y el costo asociado al alquiler.
 * </p>
 *
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 05/04/2024
 */
public class Main {
    public static void main(String[] args) {
        // Instanciando un Turismo y un Camion
        Turismo turismo = new Turismo("1234ABC", 0.30, 100, 150);
        Camion camion = new Camion("5678DEF", 50, 1, 2);

        // Mostrando el estado inicial
        System.out.println("Estado inicial de los vehículos:");
        turismo.mostrar();
        camion.mostrar();

        // Proceso de alquilar
        System.out.println("\nAlquilando vehículos...");
        turismo.alquilar(100);
        camion.alquilar(1);

        // Mostrando el estado después del alquiler
        System.out.println("\nEstado de los vehículos después del alquiler:");
        turismo.mostrar();
        camion.mostrar();

        // Proceso de devolución
        System.out.println("\nDevolviendo vehículos...");
        turismo.devolver(150);
        camion.devolver(2);

        // Mostrando el estado después de la devolución
        System.out.println("\nEstado de los vehículos después de la devolución:");
        turismo.mostrar();
        camion.mostrar();

        // Mostrando el precio del alquiler
        System.out.println("\nCosto del alquiler:");
        System.out.println("Turismo: " + turismo.devolverPrecio() + " EUR");
        System.out.println("Camion: " + camion.devolverPrecio() + " EUR");
    }
}