package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio2UT7ClaseVehiculo;

/**
 * <h1 style="text-align: center">Clase Camión - Extiende a Vehiculo</h1>
 * <br>
 * <p style="text-align: justify">
 * Subclase específica para camiones en un sistema de alquiler de vehículos. Esta clase gestiona
 * particularidades de los camiones, como el cálculo del precio de alquiler basado en días y
 * la presentación de su información específica.
 * </p>
 * <br>
 * <h2 style="text-align: center">Atributos y Métodos</h2>
 * <ul style="text-align: justify">
 *   <li><b>double precioDia:</b> Coste diario de alquiler del camión.</li>
 *   <li><b>int diaAlquiler:</b> Día en el que se alquila el camión.</li>
 *   <li><b>int diaDevolución:</b> Día en el que se devuelve el camión.</li>
 *   <li>{@link #Camion(String, double, int, int)}: Constructor para inicializar un camión con su matrícula y precio por día.</li>
 *   <li>{@link #devolverPrecio()}: Calcula y devuelve el precio total del alquiler basado en la diferencia de días.</li>
 *   <li>{@link #mostrar()}: Presenta la información específica del camión, además de la información heredada de {@link Vehiculo}.</li>
 * </ul>
 *
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 29/03/2024
 */
public class Camion extends Vehiculo {
    private double precioDia;
    private int diaAlquiler;
    private int diaDevolucion;

    /**
     * <h2 style="text-align: center">Constructor de Camión</h2>
     * <br>
     * <p style="text-align: justify">
     * Inicializa un camión con los parámetros especificados, incluyendo la matrícula y
     * el precio por día de alquiler.
     * </p>
     *
     * @param matricula Identificador único del camión.
     * @param precioDia Coste por día de alquiler del camión.
     * @param diaAlquiler Día inicial del alquiler.
     * @param diaDevolucion Día de devolución del camión.
     */
    public Camion(String matricula, double precioDia, int diaAlquiler, int diaDevolucion) {
        super(matricula);
        this.precioDia = precioDia;
        this.diaAlquiler = diaAlquiler;
        this.diaDevolucion = diaDevolucion;
    }

    /**
     * <h2 style="text-align: center">Devolver Precio</h2>
     * <br>
     * <p style="text-align: justify">
     * Calcula el coste total del alquiler basándose en la diferencia de días entre el
     * alquiler y la devolución.
     * </p>
     *
     * @return El precio total del alquiler.
     */
    public double devolverPrecio() {
        double diferenciaDias = (double) this.diaDevolucion - this.diaAlquiler;
        return precioDia * diferenciaDias;
    }

    /**
     * <h2 style="text-align: center">Mostrar Información del Camión</h2>
     * <br>
     * <p style="text-align: justify">
     * Presenta los detalles específicos del camión, incluyendo el precio por día,
     * el día de alquiler y el día de devolución.
     * </p>
     */
    @Override
    public void mostrar() {
        super.mostrar(); // Llama al método mostrar de la clase padre para mostrar información básica.
        System.out.println("Información del camión: \n" +
                "[1] Precio por día: " + precioDia + "\n" +
                "[2] Día de alquiler: " + diaAlquiler + "\n" +
                "[3] Día de devolución: " + diaDevolucion + "\n");
    }

    /**
     * <h2 style="text-align: center">Método Alquilar</h2>
     * <br>
     * <p style="text-align: justify">
     * Marca el camión como alquilado estableciendo el día de inicio del alquiler.
     * Cambia el estado de 'alquilado' a verdadero y registra el día en que se realiza el alquiler.
     * </p>
     *
     * @param dato El día en el que el camión se alquila.
     */
    @Override
    public void alquilar(int dato) {
        this.alquilado = true; // Marca el camión como alquilado.
        this.diaAlquiler = dato; // Establece el día de alquiler con el valor proporcionado.
    }

    /**
     * <h2 style="text-align: center">Método Devolver</h2>
     * <br>
     * <p style="text-align: justify">
     * Marca el camión como no alquilado estableciendo el día de devolución.
     * Cambia el estado de 'alquilado' a falso e indica el día en que se devuelve el camión.
     * Este método calcula automáticamente el coste total del alquiler basado en los días de uso.
     * </p>
     *
     * @param dato El día en el que el camión se devuelve.
     */
    @Override
    public void devolver(int dato) {
        this.alquilado = false; // Marca el camión como no alquilado.
        this.diaDevolucion = dato; // Establece el día de devolución con el valor proporcionado.
    }
}

