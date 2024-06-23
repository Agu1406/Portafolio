package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio1UT7ClaseEmpleado;

/**
 * <h1 style="text-align: center">Clase Currito - Extiende Empleado</h1>
 * <br>
 * <p style="text-align: justify">
 * Subclase de {@link Empleado} que representa a un trabajador regular, denominado "Currito",
 * con la posibilidad de asignarle un jefe y mostrar su información básica más el nombre del jefe asignado.
 * </p>
 * <h2 style="text-align: center">Métodos y Constructores:</h2>
 * <ul>
 *   <li>{@link Currito#Currito(String, int, String)}: Constructor que incluye el nombre del jefe.</li>
 *   <li>{@link Currito#asignarJefe(String)}: Asigna un jefe a este Currito.</li>
 *   <li>{@link Currito#mostrar()}: Sobrescribe mostrar() para incluir el nombre del jefe.</li>
 * </ul>
 *
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 04/04/2024
 * @see Empleado
 */
public class Currito extends Empleado {
    private String nombreJefe;

    /**
     * <h2 style="text-align: center">Constructor de Currito</h2>
     * <br>
     * <p style="text-align: justify">
     * Crea un nuevo "Currito" con nombre, sueldo y un jefe específico, hereda el constructor
     * de su clase padre "{@link Empleado}" con el añadido del atributo propio de un "currito"
     * que es el nombre de su jefe.
     * </p>
     *
     * @param nombre El nombre del "Currito".
     * @param sueldo El sueldo inicial del "Currito".
     * @param nombreJefe El nombre del jefe asignado al "Currito".
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public Currito(String nombre, int sueldo, String nombreJefe) {
        super(nombre, sueldo);
        this.nombreJefe = nombreJefe;
    }

    /**
     * <h2 style="text-align: center">Método Asignar Jefe</h2>
     * <br>
     * <p style="text-align: justify">
     * Establece o cambia el nombre del jefe asociado a la instancia "Currito" desde la que
     * se invoca el método..
     * </p>
     *
     * @param nombreJefe El nombre del jefe a asignar.
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public void asignarJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
    }

    /**
     * <h2 style="text-align: center">Método Mostrar Información del Currito</h2>
     * <br>
     * <p style="text-align: justify">
     * Muestra por pantalla la información de la instancia "Currito" desde la que se invoca,
     * esto incluye información sobre el jefe del "Currito" junto a su nombre y sueldo,
     * hereda {@link Empleado#mostrar()} y lo sobre-escribe con la información propia
     * del currito.
     * </p>
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Jefe: " + nombreJefe);
    }
}
