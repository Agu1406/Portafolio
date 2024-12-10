package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio1UT7ClaseEmpleado;

/**
 * <h1 style="text-align: center">Clase Empleado - Base para jerarquía de empleados</h1>
 * <br>
 * <p style="text-align: justify">
 * Clase base para representar empleados en una empresa. Provee
 * estructura para los empleados incluyendo su nombre y sueldo, sirviendo
 * como superclase para otras derivadas como {@link Jefe} y {@link Currito}.
 * </p>
 * <h2 style="text-align: center">Métodos y Constructores:</h2>
 * <ul>
 *   <li>{@link Empleado#Empleado(String, int)}: Constructor para instanciar empleados.</li>
 *   <li>{@link Empleado#subirSueldo(int)}: Método para aumentar el sueldo.</li>
 *   <li>{@link Empleado#mostrar()}: Método para mostrar información del empleado.</li>
 * </ul>
 *
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 04/04/2024
 */
public class Empleado {
    private final String nombre;
    private int sueldo;

    private static int empleadosCreados = 0;
    private int numeroEmpleado;

    /**
     * <h2 style="text-align: center">Constructor de Empleado</h2>
     * <br>
     * <p style="text-align: justify">
     * Inicializa un nuevo empleado con un nombre y sueldo dados.
     * </p>
     *
     * @param nombre El nombre del empleado.
     * @param sueldo El sueldo inicial del empleado.
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public Empleado(String nombre, int sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.numeroEmpleado = empleadosCreados;
        empleadosCreados++;
    }

    /**
     * <h2 style="text-align: center">Método Subir Sueldo</h2>
     * <br>
     * <p style="text-align: justify">
     * Aumenta el sueldo del empleado por un valor especificado.
     * </p>
     *
     * @param aumento Cantidad a incrementar en el sueldo actual.
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public void subirSueldo(int aumento) {
        sueldo += aumento;
    }

    /**
     * <h2 style="text-align: center">Método Mostrar Información</h2>
     * <br>
     * <p style="text-align: justify">
     * Imprime los detalles del empleado incluyendo su nombre y sueldo actual.
     * </p>
     */
    public void mostrar() {
        System.out.println("Nombre: " + nombre + ", Sueldo: " + sueldo);
    }
}
