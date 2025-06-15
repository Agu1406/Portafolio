package Madrid.UAX.sistema_gestion_empleado;

public class EmpleadoAsalariadoFactory implements EmpleadoFactory {
    @Override
    public Empleado crearEmpleado(String nombre, double salarioBase, Object... params) {
        if (params.length < 1) {
            throw new IllegalArgumentException("Se requiere el salario anual");
        }
        double salarioAnual = (double) params[0];
        return new EmpleadoAsalariado(nombre, salarioBase, salarioAnual);
    }
} 