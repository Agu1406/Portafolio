package sistema_gestion_empleado;

public class EmpleadoPorHorasFactory implements EmpleadoFactory {
    @Override
    public Empleado crearEmpleado(String nombre, double salarioBase, Object... params) {
        if (params.length < 2) {
            throw new IllegalArgumentException("Se requieren horas trabajadas y tarifa por hora");
        }
        int horasTrabajadas = (int) params[0];
        double tarifaPorHora = (double) params[1];
        return new EmpleadoPorHoras(nombre, salarioBase, horasTrabajadas, tarifaPorHora);
    }
} 