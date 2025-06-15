package Madrid.UAX.sistema_gestion_empleado;

public class Main {
    public static void main(String[] args) {
        // Crear fábricas
        EmpleadoFactory asalariadoFactory = new EmpleadoAsalariadoFactory();
        EmpleadoFactory porHorasFactory = new EmpleadoPorHorasFactory();

        // Crear empresa
        Empresa empresa = new Empresa();

        try {
            // Crear empleados usando las fábricas
            Empleado juan = asalariadoFactory.crearEmpleado("Juan", 2500, 30000.0);
            empresa.agregarEmpleadoADepartamento("Ventas", juan);

            try {
                // Esto debe generar una excepción
                Empleado maria = asalariadoFactory.crearEmpleado("María", 2000, -30000.0);
                empresa.agregarEmpleadoADepartamento("TI", maria);
            } catch (SalarioInvalidoException e) {
                System.out.println("Error al crear empleado María: " + e.getMessage());
            }

            Empleado carlos = porHorasFactory.crearEmpleado("Carlos", 0, 35, 15.0);
            empresa.agregarEmpleadoADepartamento("TI", carlos);

            try {
                // Esto debe generar una excepción
                Empleado laura1 = porHorasFactory.crearEmpleado("Laura", 0, -15, 50.0);
                empresa.agregarEmpleadoADepartamento("Ventas", laura1);
            } catch (IllegalArgumentException e) {
                System.out.println("Error al crear empleado Laura: " + e.getMessage());
            }

            try {
                // Esto debe generar una excepción por tarifa excesiva
                Empleado laura2 = porHorasFactory.crearEmpleado("Laura", 0, 40, 100.0);
                empresa.agregarEmpleadoADepartamento("Ventas", laura2);
            } catch (TarifaExcesivaException e) {
                System.out.println("Error al crear empleado Laura: " + e.getMessage());
            }

            // Mostrar salarios usando Iterator
            System.out.println("\nSalarios de todos los empleados:");
            for (Empleado empleado : empresa) {
                System.out.printf("%s: %.2f€%n", empleado.getNombre(), empleado.calcularSalario());
            }

            // Mostrar salarios por departamento
            System.out.println("\nSalarios totales por departamento:");
            System.out.printf("Ventas: %.2f€%n", empresa.calcularSalarioTotalDepartamento("Ventas"));
            System.out.printf("TI: %.2f€%n", empresa.calcularSalarioTotalDepartamento("TI"));

            // Mostrar empleados ordenados por nombre en cada departamento
            System.out.println("\nEmpleados ordenados por nombre en TI:");
            for (Empleado empleado : empresa.obtenerEmpleadosOrdenadosPorNombre("TI")) {
                System.out.println(empleado.getNombre());
            }

            // Guardar empresa en archivo
            PersistenciaEmpleados.guardarEmpresa(empresa);
            System.out.println("\nEmpresa guardada en archivo.");

            // Cargar empresa desde archivo
            Empresa empresaCargada = PersistenciaEmpleados.cargarEmpresa();
            System.out.println("Empresa cargada desde archivo.");

        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 