package Madrid.UAX.sistema_gestion_empleado;

/**
 * Clase que representa a un empleado asalariado.
 * Hereda de la clase Empleado y implementa la interfaz Calculable.
 */     
public class EmpleadoAsalariado extends Empleado {
    // variable que guarda el salario anual 
    private double salarioAnual;

    /**
     * Constructor de la clase EmpleadoAsalariado.
     * @param nombre El nombre del empleado.
     * @param salarioBase El salario base del empleado.
     * @param salarioAnual El salario anual del empleado.
     */
    public EmpleadoAsalariado(String nombre, double salarioBase, double salarioAnual) {
        // Llamamos al constructor de la clase padre
        super(nombre, salarioBase);
        // Comprobamos que el salario anual no es negativo
        if (salarioAnual < 0) {
            // Si el salario anual es negativo, lanzamos una excepción
            throw new SalarioInvalidoException("El salario anual no puede ser negativo");
        }
        // Asignamos el salario anual al atributo
        this.salarioAnual = salarioAnual;
    }
    /**
     * Calcula el salario mensual del empleado.
     * @return El salario mensual del empleado.
     */
    @Override
    public double calcularSalario() {
        return salarioAnual / 12;
    }
} 