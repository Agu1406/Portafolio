package sistema_gestion_empleado;

/**
 * Interfaz para calcular el salario de un empleado.
 * Obliga a implementar el metodo calcularSalario.
 */
public interface Calculable {
    /**
     * Calcula el salario del empleado, el código debe ser implementado por cada
     * clase que implemente la interfaz.
     * 
     * @return El salario calculado.
     */
    double calcularSalario();
}