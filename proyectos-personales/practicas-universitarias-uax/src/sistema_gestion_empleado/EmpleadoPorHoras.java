package sistema_gestion_empleado;

public class EmpleadoPorHoras extends Empleado {
    private int horasTrabajadas;
    private double tarifaPorHora;

    public EmpleadoPorHoras(String nombre, double salarioBase, int horasTrabajadas, double tarifaPorHora) {
        super(nombre, salarioBase);
        if (horasTrabajadas < 0) {
            throw new IllegalArgumentException("Las horas trabajadas no pueden ser negativas");
        }
        if (tarifaPorHora < 0) {
            throw new IllegalArgumentException("La tarifa por hora no puede ser negativa");
        }
        if (tarifaPorHora > 150) {
            throw new TarifaExcesivaException("La tarifa por hora no puede superar los 150€");
        }
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }

    @Override
    public double calcularSalario() {
        return horasTrabajadas * tarifaPorHora;
    }
} 