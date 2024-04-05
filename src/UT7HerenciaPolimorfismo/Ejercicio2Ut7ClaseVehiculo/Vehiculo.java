package UT7HerenciaPolimorfismo.Ejercicio2Ut7ClaseVehiculo;
/**
 * <h1 style="text-align: center">Clase Vehiculo</h1>
 * <br>
 * <p style="text-align: justify">
 * Esta clase representa la estructura y funcionalidades básicas de un vehículo dentro de un sistema de alquiler. Incluye operaciones para alquilar, devolver y verificar el estado de alquiler de un vehículo.
 * </p>
 * <br>
 * <h2 style="text-align: center">Atributos, Constructores y Métodos</h2>
 * <ul style="text-align: justify">
 *   <li><b>String matricula:</b> Identificador único del vehículo.</li>
 *   <li><b>boolean alquilado:</b> Estado actual del alquiler del vehículo (true para alquilado, false para disponible).</li>
 *   <li>{@link #Vehiculo(String)}: Constructor que inicializa un vehículo con una matrícula dada.</li>
 *   <li>{@link #alquilar(int)}: Marca el vehículo como alquilado.</li>
 *   <li>{@link #devolver(int)}: Marca el vehículo como no alquilado, devolviéndolo al estado disponible.</li>
 *   <li>{@link #estadoAlquiler()}: Retorna el estado actual del alquiler del vehículo.</li>
 *   <li>{@link #mostrar()}: Muestra por pantalla la información relevante del vehículo, incluido su estado de alquiler.</li>
 * </ul>
 * <br>
 * <p style="text-align: justify">
 * La clase se diseñó para gestionar los estados de alquiler de vehículos, permitiendo operaciones básicas como el alquiler y la devolución, así como la consulta de su estado actual.
 * </p>
 *
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 29/03/2024
 */
/* La clase es abstracta, no permite instanciar desde sí misma y obliga a las clases que
* heredan / se extiende de él a contener los métodos de la clase padre obligatoriamente,
* controlando mejor como las clases que heredan manipulan y tratan los datos y las
* instancias derivadas de sus atributos, constructores y métodos.*/
public abstract class Vehiculo {
    private String matricula;
    protected boolean alquilado;
    /**
     * <h2 style="text-align: center">Constructor de Vehiculo</h2>
     * <br>
     * <p style="text-align: justify">
     * Inicializa un vehículo con la matrícula especificada. Por defecto, el vehículo se establece como no alquilado.
     * </p>
     *
     * @param matricula Identificador único del vehículo.
     */
    public Vehiculo(String matricula) {
        this.matricula = matricula;
        this.alquilado = false;
    }

    /**
     * <h2 style="text-align: center">Método alquilar</h2>
     * <br>
     * <p style="text-align: justify">
     * Marca el vehículo como alquilado. Este método cambia el estado de 'alquilado' a verdadero.
     * </p>
     */
    public abstract void alquilar(int dato);

    /**
     * <h2 style="text-align: center">Método devolver</h2>
     * <br>
     * <p style="text-align: justify">
     * Marca el vehículo como no alquilado. Este método cambia el estado de 'alquilado' a falso, indicando que el vehículo está disponible nuevamente.
     * </p>
     */
    public abstract void devolver(int dato);

    /**
     * <h2 style="text-align: center">Método estadoAlquiler</h2>
     * <br>
     * <p style="text-align: justify">
     * Verifica el estado de alquiler del vehículo.
     * </p>
     *
     * @return Verdadero si el vehículo está alquilado, falso si está disponible.
     */
    public boolean estadoAlquiler() {
        return alquilado;
    }

    /**
     * <h2 style="text-align: center">Método ostrar</h2>
     * <br>
     * <p style="text-align: justify">
     * Muestra la información del vehículo, incluyendo su matrícula y estado de alquiler.
     * </p>
     */
    public void mostrar() {
        String respuesta = alquilado ? "Sí" : "No";
        System.out.println("Matrícula: " + matricula + ", ¿Alquilado?: " + respuesta);
    }
}