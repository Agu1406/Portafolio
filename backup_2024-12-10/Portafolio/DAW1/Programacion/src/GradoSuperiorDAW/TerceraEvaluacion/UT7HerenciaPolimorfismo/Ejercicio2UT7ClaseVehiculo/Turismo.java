package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio2UT7ClaseVehiculo;

/**
 * <h1 style="text-align: center">Clase Turismo</h1>
 * <p style="text-align: justify">
 * Subclase de {@link Vehiculo} especializada en vehículos de turismo. Incluye características específicas como el precio por kilómetro, kilómetros al inicio del alquiler y al finalizarlo.
 * </p>
 *
 * @author Agu1406 (Agustín)
 * @since 05/04/2024
 */
public class Turismo extends Vehiculo {
    private double precioKm;
    private int kmAlquiler;
    private int kmDevolucion;

    /**
     * <h2 style="text-align: center">Constructor de Turismo</h2>
     * <br>
     * <p style="text-align: justify">
     * Inicializa un vehículo de turismo con su matrícula, precio por kilómetro, kilómetros al inicio del alquiler y al finalizarlo.
     * </p>
     *
     * @param matricula Matrícula del vehículo.
     * @param precioKm Precio por kilómetro recorrido.
     * @param kmAlquiler Kilómetros al inicio del alquiler.
     * @param kmDevolucion Kilómetros al finalizar el alquiler.
     */
    public Turismo(String matricula, double precioKm, int kmAlquiler, int kmDevolucion) {
        super(matricula);
        this.precioKm = precioKm;
        this.kmAlquiler = kmAlquiler;
        this.kmDevolucion = kmDevolucion;
    }

    /**
     * <h2 style="text-align: center">devolverPrecio</h2>
     * <br>
     * <p style="text-align: justify">
     * Calcula y devuelve el costo total del alquiler basado en el precio por kilómetro y la diferencia entre los kilómetros al inicio y al final del alquiler.
     * </p>
     *
     * @return El precio total del alquiler del turismo.
     */
    public double devolverPrecio() {
        double diferenciaKm = (double) this.kmDevolucion - this.kmAlquiler;
        return precioKm * diferenciaKm;
    }

    /**
     * <h2 style="text-align: center">mostrar</h2>
     * <br>
     * <p style="text-align: justify">
     * Extiende el método mostrar de la clase {@link Vehiculo} para incluir información específica del turismo como precio por kilómetro, kilómetros al alquilar y al devolver.
     * </p>
     */
    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println(
                "Información del turismo: \n" +
                        "[1] Precio por kilometro: " + precioKm + "\n" +
                        "[2] Kilometro de alquiler: " + kmAlquiler + "\n" +
                        "[3] Kilometro de devolución: " + kmDevolucion + "\n");
    }

    /**
     * <h2 style="text-align: center">Método Alquilar</h2>
     * <br>
     * <p style="text-align: justify">
     * Marca el turismo como alquilado estableciendo el kilometro de inicio del alquiler.
     * Cambia el estado de 'alquilado' a verdadero y registra el día en que se realiza el alquiler.
     * </p>
     *
     * @param dato El kilometraje en el que el turismo se alquila.
     */
    @Override
    public void alquilar(int dato) {
        this.alquilado = true; // Marca el camión como alquilado.
        this.kmAlquiler = dato; // Establece el día de alquiler con el valor proporcionado.
    }

    /**
     * <h2 style="text-align: center">Método Devolver</h2>
     * <br>
     * <p style="text-align: justify">
     * Marca el turismo como no alquilado estableciendo el kilometro de devolución.
     * Cambia el estado de 'alquilado' a falso e indica el día en que se devuelve el camión.
     * Este método calcula automáticamente el coste total del alquiler basado en los días de uso.
     * </p>
     *
     * @param dato El kilometraje en el que el turismo se devuelve.
     */
    @Override
    public void devolver(int dato) {
        this.alquilado = false; // Marca el camión como no alquilado.
        this.kmDevolucion = dato; // Establece el día de devolución con el valor proporcionado.
    }
}