package sistema_gestion_empleado;

public abstract class Empleado implements Calculable {
    protected String nombre;
    protected double salarioBase;

    public Empleado(String nombre, double salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
} 