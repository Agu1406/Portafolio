package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio4UT7ClaseGeomtriaV2;
/**
 * <h1 style="text-align: center">Clase FiguraGeométrica</h1>
 * <br>
 * <p style="text-align: justify">
 * Está clase abstracta define los métodos obligatorios para realizar operaciones
 * con diversas figuras geométricas, por lo que sus métodos son abstractos para
 * obligar a los hijos de la case a sobrescribir "@Override" dichos métodos.
 * </p>
 * <br>
 * <h2 style="text-align: center">Métodos</h2>
 * <ul style="text-align: justify">
 *   <li>{@link #mostrarArea()}: Constructor que inicializa un vehículo con una matrícula dada.</li>
 *   <li>{@link #mostrarPerimetro()}: Marca el vehículo como alquilado.</li>
 * </ul>
 * <br>
 * <p style="text-align: justify">
 * La clase se diseñó para gestionar los estados de alquiler de vehículos, permitiendo operaciones básicas como el alquiler y la devolución, así como la consulta de su estado actual.
 * </p>
 *
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 09/04/2024
 */

/* La clase es abstracta, no permite instanciar desde sí misma y obliga a las clases que
 * heredan / se extiende de él a contener los métodos de la clase padre obligatoriamente,
 * controlando mejor como las clases que heredan manipulan y tratan los datos y las
 * instancias derivadas de sus atributos, constructores y métodos.*/
public interface FiguraGeometrica {

    public abstract void mostrarArea();
    public abstract void mostrarPerimetro();
}
