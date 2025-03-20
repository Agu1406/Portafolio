package sistema_gestion_empleado;

/**
 * Clase que representa a un empleado por horas.
 * Hereda de la clase Empleado y implementa la interfaz Calculable.
 */
public class EmpleadoPorHoras extends Empleado {
    // variable que guarda las horas trabajadas
    private int horasTrabajadas;
    // variable que guarda la tarifa por hora
    private double tarifaPorHora;

    /**
     * Constructor de la clase EmpleadoPorHoras.
     * 
     * @param nombre          El nombre del empleado.
     * @param salarioBase     El salario base del empleado.
     * @param horasTrabajadas Las horas trabajadas del empleado.
     * @param tarifaPorHora   La tarifa por hora del empleado.
     */
    public EmpleadoPorHoras(String nombre, double salarioBase, int horasTrabajadas, double tarifaPorHora) {
        // Llamamos al constructor de la clase padre
        super(nombre, salarioBase);
        // Comprobamos que las horas trabajadas no son negativas
        if (horasTrabajadas < 0) {
            throw new IllegalArgumentException("Las horas trabajadas no pueden ser negativas");
        }
        // Comprobamos que la tarifa por hora no es negativa
        if (tarifaPorHora < 0) {
            throw new IllegalArgumentException("La tarifa por hora no puede ser negativa");
        }
        // Comprobamos que la tarifa por hora no es superior a 150€
        if (tarifaPorHora > 150) {
            throw new TarifaExcesivaException("La tarifa por hora no puede superar los 150€");
        }
        // Asignamos los valores a los atributos
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }

    /**
     * Calcula el salario mensual del empleado por horas.
     * Se asume un mes estándar de 4 semanas.
     * 
     * @return El salario mensual del empleado por horas.
     */
    @Override
    public double calcularSalario() {
        /*
         * Es curioso porque si las horas trabajadas se registran por semana,
         * el salario mensual se calcula multiplicando las horas trabajadas por la
         * tarifa por hora y por 4 (semanas que tiene un mes), pero si fues el total
         * de horas trabajadas en un mes, el salario mensual se calcularía simplemente
         * multiplicando el total de horas trabajadas por la tarifa por hora, lo dejare
         * como está por ahora, asumiendo que las horas trabajadas se registran por
         * semana.
         */
        return horasTrabajadas * tarifaPorHora * 4;
    }
}