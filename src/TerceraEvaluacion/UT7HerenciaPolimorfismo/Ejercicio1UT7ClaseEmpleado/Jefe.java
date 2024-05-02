package TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio1UT7ClaseEmpleado;

/**
 * <h1 style="text-align: center">Clase Jefe - Extiende a Empleado</h1>
 * <br>
 * <p style="text-align: justify">
 * Derivada de {@link Empleado} para añadir la funcionalidad específica de
 * los jefes, como manejar un presupuesto de departamento.
 * </p>
 * <h2 style="text-align: center">Métodos y Constructores:</h2>
 * <ul>
 *   <li>{@link Jefe#Jefe(String, int, int)}: Constructor que incluye presupuesto.</li>
 *   <li>{@link Jefe#asignarPresupuesto(int)}: Asigna un presupuesto a un jefe.</li>
 *   <li>{@link Jefe#mostrar()}: Sobrescribe mostrar() para incluir el presupuesto.</li>
 * </ul>
 *
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 04/04/2024
 * @see Empleado
 */
public class Jefe extends Empleado {
    private int presupuesto;

    /**
     * <h2 style="text-align: center">Constructor de Jefe</h2>
     * <br>
     * <p style="text-align: justify">
     * Crea un nuevo jefe con nombre, sueldo y un presupuesto inicial para el departamento.
     * </p>
     *
     * @param nombre El nombre del jefe.
     * @param sueldo El sueldo inicial del jefe.
     * @param presupuesto Presupuesto para el departamento.
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public Jefe(String nombre, int sueldo, int presupuesto) {
        super(nombre, sueldo);
        this.presupuesto = presupuesto;
    }

    /**
     * <h2 style="text-align: center">Método Asignar Presupuesto</h2>
     * <br>
     * <p style="text-align: justify">
     * Establece o modifica el presupuesto del departamento del jefe.
     * </p>
     *
     * @param presupuestoDeseado Nuevo presupuesto para el departamento.
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public void asignarPresupuesto(int presupuestoDeseado) {
        presupuesto = presupuestoDeseado;
    }

    /**
     * <h2 style="text-align: center">Método Mostrar Información del Jefe</h2>
     * <br>
     * <p style="text-align: justify">
     * Incluye información sobre el jefe junto a su nombre y sueldo, hereda
     * {@link Empleado#mostrar()} y lo sobre-escribe con la información propia del jefe.
     * </p>
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Presupuesto: " + presupuesto);
    }
}
