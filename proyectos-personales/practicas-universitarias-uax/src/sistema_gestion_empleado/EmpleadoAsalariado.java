package sistema_gestion_empleado;

public class EmpleadoAsalariado extends Empleado {
    private double salarioAnual;

    public EmpleadoAsalariado(String nombre, double salarioBase, double salarioAnual) {
        super(nombre, salarioBase);
        if (salarioAnual < 0) {
            throw new SalarioInvalidoException("El salario anual no puede ser negativo");
        }
        this.salarioAnual = salarioAnual;
    }

    @Override
    public double calcularSalario() {
        return salarioAnual / 12;
    }
} 