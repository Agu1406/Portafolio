package GradoSuperiorDAW.TerceraEvaluacion.UT7ObjetosAvanzados.Ejercicio8UT7GestionEmpleadosVersion2;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <h1 style="text-align: center;">Clase Empleado para la gestión de empleados en una empresa de productos químicos - Versión 2.0</h1>
 * <br>
 * <p style="text-align: justify;">Versión 2.0 - Fase N.º1 - Partimos de la versión anterior donde
 * teníamos la gestión de nuestros empleados, almacenando Dni, Nombre, Apellido, Departamento y
 * Sueldo. Ahora se quiere añadir más información a nuestros empleados. En concreto:
 * </p>
 * <ul style="text-align: justify">
 * <li><b>DNI:</b> El DNI seguirá siendo nuestra clave. La letra debe cumplir la normativa actual. Ahora se
 * permiten DNIs que tengan entre 1 y 8 dígitos.</li>
 * <li><b>FechaContrato:</b> Se refiere a la fecha en la que se contrata a un empleado.</li>
 * <li><b>FechaNacimiento:</b> Se refiere a la fecha de nacimiento del empleado.</li>
 * </ul>
 * <br>
 * <p style="text-align: center"><b>Además, se espera que se cumplan las siguientes normas y/o requisitos
 * en el programa, los cuales son:</b></p>
 * <br>
 * <ul style="text-align: justify">
 *      <li><b>DNI: </b>El DNI debe cumplir la normativa actual y pueden introducirse entre 1 y 8 dígitos. En
 * caso contrario, se volverá a pedir el DNI indicando un mensaje de error</li>
 *      <li><b>Fechas: </b>Si una fecha introducida es incorrecta, se volverá a pedir la fecha indicando un
 * mensaje de error.</li>
 *      <li><b>Atributos: </b>Los campos nombre, apellido y departamento; solo pueden contener letras o
 * guiones. Si estos campos contienen otros caracteres, se volverá a pedir el dato
 * indicando un mensaje de error. La inicial debe estar en mayúscula, en caso de que el
 * usuario se equivoque, el programa escribirá esa inicial en mayúscula.</li>
 *
 * <p style="text-align: justify;">Es importante garantizar que el DNI de cada empleado sea único para evitar inconsistencias
 * en la gestión de los empleados.</p>
 * <br>
 * <h2 style="text-align: center;">Métodos y Constructores</h2>
 * <br>
 * <ul style="text-align: justify;">
 *   <li><b>Empleado: </b>Constructor que permite crear instancias de la clase.</li>
 *   <li><b>toString:</b> Devuelve toda la información del empleado en forma de String.</li>
 *   <li><b>subirSalario(float porcentaje):</b> Sube el salario del empleado en el porcentaje indicado.</li>
 *   <li><b>equals(Object object):</b> Define cuándo dos objetos Empleado son iguales basándose en su DNI.</li>
 *   <li><b>mostrarReducido():</b> Muestra por pantalla el DNI, nombre y sueldo del empleado.</li>
 * </ul>
 *
 * @author Agu1406 (Agustín)
 * @version 2.0
 * @since 02/04/2024
 */
public class Empleado {
    /*
     * En la primera versión del programa se habían creado los siguientes atributos:
     *
     * - DNI (String Único, no se puede repetir)
     * - Nombre (String que contiene el primer nombre del empleado)
     * - Apellido (String que contiene el primer apellido del empleado)
     * - Departamento (String, contiene el departamento donde trabaja el empleado)
     * - Sueldo (Double, ya que permite en los incrementos de sueldo, trabajar con decimales)
     *
     * En esta segunda versión del programa añadimos los siguientes atributos:
     *
     * - FechaContrato: La fecha en la que se ha dado de alta / contratado un empleado.
     * - FechaNacimiento: La fecha de nacimiento de un empleado.
     * - FechaEmpresa: La fecha de creación de la empresa.
     * */
    private final String DNI;
    private final String nombre;
    private final String apellido;
    private final String departamento;
    private Float sueldo;
    // Creación de los nuevos atributos de esta clase:
    private final LocalDate fechaContrato;
    private final LocalDate fechaNacimiento;

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
    public Empleado(String DNI, String nombre, String apellido, String departamento, Float sueldo,
                    LocalDate fechaContrato, LocalDate fechaNacimiento) {
        // Lógica del constructor que imposibilita instanciar si, por ejemplo, el DNI es nulo o el sueldo negativo.
        if (DNI == null || DNI.isEmpty()) throw new IllegalArgumentException("El DNI no puede ser nulo o vacío.");
        if (sueldo != null && sueldo < 0) throw new IllegalArgumentException("El sueldo no puede ser negativo.");
        if (fechaNacimiento != null && fechaContrato != null && fechaNacimiento.isAfter(fechaContrato))
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior a la fecha de contrato.");

        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        this.sueldo = sueldo;
        this.fechaContrato = fechaContrato;
        this.fechaNacimiento = fechaNacimiento;
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
                "Sueldo Actual: " + this.sueldo + "\n" +
                "Fecha de Alta: " + this.fechaContrato + "\n" +
                "Fecha de nacimiento: " + this.fechaNacimiento + "\n";
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
