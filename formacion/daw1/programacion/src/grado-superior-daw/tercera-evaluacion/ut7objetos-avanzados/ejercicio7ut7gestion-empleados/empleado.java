package GradoSuperiorDAW.TerceraEvaluacion.UT7ObjetosAvanzados.Ejercicio7UT7GestionEmpleados;

import java.util.Objects;

/**
 * <h1 style="text-align: center;">Clase Empleado para la gestión de empleados en una empresa de productos químicos</h1>
 * <br>
 * <p style="text-align: justify;">Fase N.º1 del ejercicio - Esta fase representa la información y operaciones
 * asociadas a un empleado dentro de la empresa, incluyendo el manejo de su DNI,
 * nombre, apellido, departamento y sueldo.</p>
 * <br>
 * <p style="text-align: justify;">El DNI actúa como una clave única para cada empleado, asegurando que no
 * existan duplicados. Esta clase provee funcionalidades como:</p>
 * <br>
 * <ul style="text-align: justify;">
 *   <li>Representación textual del empleado.</li>
 *   <li>Incremento del sueldo basado en un porcentaje.</li>
 *   <li>Comparación de igualdad entre empleados.</li>
 *   <li>Visualización reducida de la información del empleado.</li>
 * </ul>
 *
 * <p style="text-align: justify;">Es importante garantizar que el DNI de cada empleado sea único para evitar inconsistencias
 * en la gestión de los empleados.</p>
 * <br>
 * <h2 style="text-align: center;">Constructor(es)</h2>
 * <br>
 * <p style="text-align: justify;">La clase debe contar con los constructores necesarios para inicializar los objetos de tipo Empleado
 * con los datos proporcionados.</p>
 * <br>
 * <h2 style="text-align: center;">Métodos</h2>
 * <br>
 * <ul style="text-align: justify;">
 *   <li><b>toString:</b> Devuelve toda la información del empleado en forma de String.</li>
 *   <li><b>subirSalario(float porcentaje):</b> Sube el salario del empleado en el porcentaje indicado.</li>
 *   <li><b>equals(Object object):</b> Define cuándo dos objetos Empleado son iguales basándose en su DNI.</li>
 *   <li><b>mostrarReducido():</b> Muestra por pantalla el DNI, nombre y sueldo del empleado.</li>
 * </ul>
 * <br>
 * <p style="text-align: justify;">La implementación de estos métodos debe seguir las especificaciones proporcionadas para cada uno,
 * asegurando la correcta gestión de la información del empleado.</p>
 *
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 31/01/2024
 */
public class Empleado {
    /*
     * Primera parte - creación de los atributos, se desea que los atributos de la clase "Empleado"
     * contenga los siguientes atributos:
     * - DNI (String Único, no se puede repetir)
     * - Nombre (String que contiene el primer nombre del empleado)
     * - Apellido (String que contiene el primer apellido del empleado)
     * - Departamento (String, contiene el departamento donde trabaja el empleado)
     * - Sueldo (Double, ya que permite en los incrementos de sueldo, trabajar con decimales)
     * */
    protected String DNI;
    protected String nombre;
    protected String apellido;
    protected String departamento;
    protected Float sueldo;

    /**
     * <h2 style="text-align: center">Constructor de la clase Empleado</h2>
     * <br>
     * <p style="text-align: justify">Permite la creación de una estancia del tipo "empleado" atráves de la introducción
     * de todos los parámetros deseados en el formato establecido, los cuales son: </p>
     *
     * @param DNI          (String único irrepetible que posee cada empleado)
     * @param nombre       (String que contiene el primer nombre de un empleado)
     * @param apellido     (String que contiene el primer apellido de un empleado)
     * @param departamento (String que contiene el departamento en el que trabaja el empleado)
     * @param sueldo       (Double que contiene el sueldo exacto con decimales de un empleado cualquiera)
     * @author Agu1406 (Agustín)
     * @since 31/01/2024
     */
    public Empleado(String DNI, String nombre, String apellido, String departamento, Float sueldo) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        this.sueldo = sueldo;
    }

    /**
     * <h2 style="text-align: center">Getter del String DNI</h2>
     * <br>
     * <p style="text-align: justify">Permite visualizar / ver
     * el atributo DNI (de tipo String) de la instancias desde
     * la que se invoca, para, por ejemplo, con fines de comparación</p>
     *
     * @return String con el DNI de la instancia desde la que se invoca.
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * <h2 style="text-align: center">Getter del String Departamento</h2>
     * <br>
     * <p style="text-align: justify">Permite visualizar / ver
     * el atributo Departamento (de tipo String) de la instancias desde
     * la que se invoca, para, por ejemplo, con fines de comparación</p>
     *
     * @return String con el Departamento de la instancia desde la que se invoca.
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * <h2 style="text-align: center">Getter del float sueldo</h2>
     * <br>
     * <p style="text-align: justify">Permite visualizar / ver
     * el atributo sueldo (de tipo String) de la instancias desde
     * la que se invoca, para, por ejemplo, con fines de comparación</p>
     *
     * @return float con el sueldo de la instancia desde la que se invoca.
     */
    public Float getSueldo() {
        return sueldo;
    }

    /**
     * <h2 style="text-align: center">Método toString personalizado de la clase Empleado</h2>
     * <br>
     * <p style="text-align: justify">Devuelve la información de una estancia del tipo
     * empleado, es decir, de un empleado en un formato de fácil comprensión.</p>
     *
     * @return Imprime por pantalla la información de la estancia desde la que se invoca.
     * @author Agu1406 (Agustín)
     * @since 31/01/2024
     */
    @Override
    public String toString() {
        return "Información del empleado: \n" +
                "DNI: " + this.DNI + "\n" +
                "Nombre: " + this.nombre + "\n" +
                "Apellido: " + this.apellido + "\n" +
                "Departamento: " + this.departamento + "\n" +
                "Sueldo Actual: " + this.sueldo + "\n";
    }

    /**
     * <h2 style="text-align: center">Método que permite subir el sueldo de un empleado</h2>
     * <br>
     * <p style="text-align: justify">Recibe el porcentaje que el usuario desea subir el
     * sueldo y aumenta el sueldo desde la estancia de la clase "empleado" desde la que
     * se invoca.</p>
     *
     * @param porcentajeDeseado (float que el usuario entrega como el porcentaje de aumento deseado)
     * @author Agu1406 (Agustín)
     * @since 31/01/2024
     */
    public void subirSalario(float porcentajeDeseado) {
        /*
         * Para subir el sueldo de un empleado un porcentaje deseado debo entender
         * como el usuario me proporciona ese porcentaje, imaginemos que desea
         * subir el sueldo de "Agustín" un 13,44%, ese número que recibo debo
         * de convertirlo a un factor de incremento, para ello primero debo de
         * dividirlo entre 100 (ejemplo: 13,44 / 100 = 0,1344 y por último el
         * "0" se debe convertir en un "1", por lo que guardo el 0,1344 en
         * una variable temporal y sumarle "1", por último, el sueldo he
         * de multiplicarlo por ese número, ejemplo: 3000 * 1,1344 y ya
         * estaría el sueldo incrementado ese porcentaje deseado, por
         * lo tanto, procedemos: */

        // Convierto el número del usuario en un factor de incremento válido.
        float factorDeIncremento = (porcentajeDeseado / 100) + 1;

        // Actualiza el sueldo actual incrementad su respectivo porcentaje.
        this.sueldo = this.sueldo * factorDeIncremento;
    }

    /**
     * <h2 style="text-align: center">Método "equals" de la clase empleado, compara si dos objetos
     * / estancias son iguales</h2>
     * <br>
     * <p style="text-align: justify"> Para verificar si dos empleados son el mismo empleado
     * o si el objeto que se está comparando es igual / idéntico a otro, se utiliza la clave
     * principal de las estancias que en esté caso es el DNI que debería ser único e
     * irrepetible en cada estancia. </p>
     *
     * @param estancia (se refiere al objeto al que estás intentando comparar)
     * @return "true" o "false", si coinciden "true, si no, lo contrario.
     * @author Agu1406 (Agustín)
     * @since 31/01/2024
     */
    @Override
    public boolean equals(Object estancia) {
        if (this == estancia) return true;
        if (estancia == null || getClass() != estancia.getClass()) return false;
        Empleado empleado = (Empleado) estancia;
        return Objects.equals(DNI, empleado.DNI);
    }

    /**
     * <h2 style="text-align: center">Método toString reducido y personalizado de la clase Empleado</h2>
     * <br>
     * <p style="text-align: justify">Devuelve solo una pequeña parte de la información de un
     * empleado, en este caso: DNI, nombre y sueldo..</p>
     *
     * @return Imprime por pantalla la información de la estancia desde la que se invoca.
     * @author Agu1406 (Agustín)
     * @since 31/01/2024
     */
    public String toStringReducido() {
        return "DNI: " + DNI + ", Nombre: " + nombre + ", Sueldo: " + sueldo;

    }

}
