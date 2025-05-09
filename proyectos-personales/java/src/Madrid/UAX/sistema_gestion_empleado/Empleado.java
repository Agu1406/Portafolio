package Madrid.UAX.sistema_gestion_empleado;

/**
 * Clase abstracta que representa a un empleado.
 * Implementa la interfaz Calculable para calcular el salario del empleado.
 */
public abstract class Empleado implements Calculable {
    protected String nombre;
    protected double salarioBase;

    /**
     * Constructor de la clase Empleado.
     * @param nombre El nombre del empleado.
     * @param salarioBase El salario base del empleado.
     * @throws IllegalArgumentException Si el salario base es negativo o el nombre está vacío.
     */
    public Empleado(String nombre, double salarioBase) {
        // Comprobamos que el salario base no es negativo
        if (salarioBase < 0) {
            // Si el salario base es negativo, lanzamos una excepción
            throw new IllegalArgumentException("El salario base no puede ser negativo");
        }
        // Comprobamos que el nombre no está vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            // Si el nombre está vacío, lanzamos una excepción
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        // Asignamos los valores a los atributos si no hay excepciones
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }
    /**
     * Devuelve el nombre del empleado.
     * @return El nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Devuelve el salario base del empleado.
     * @return El salario base del empleado.
     */ 
    public double getSalarioBase() {
        return salarioBase;
    }
}