package Madrid.UAX.sistema_gestion_empleado;

import java.io.Serializable;
import java.util.*;

public class Empresa implements Iterable<Empleado>, Serializable {
    private Map<String, List<Empleado>> departamentos;

    public Empresa() {
        this.departamentos = new HashMap<>();
    }

    public void agregarEmpleadoADepartamento(String departamento, Empleado empleado) {
        departamentos.computeIfAbsent(departamento, k -> new ArrayList<>()).add(empleado);
    }

    public double calcularSalarioTotalDepartamento(String departamento) {
        return Optional.ofNullable(departamentos.get(departamento))
                .map(empleados -> empleados.stream()
                        .mapToDouble(Empleado::calcularSalario)
                        .sum())
                .orElse(0.0);
    }

    public List<Empleado> obtenerEmpleadosOrdenadosPorNombre(String departamento) {
        List<Empleado> empleados = departamentos.getOrDefault(departamento, new ArrayList<>());
        empleados.sort(Comparator.comparing(Empleado::getNombre));
        return empleados;
    }

    @Override
    public Iterator<Empleado> iterator() {
        List<Empleado> todosLosEmpleados = new ArrayList<>();
        departamentos.values().forEach(todosLosEmpleados::addAll);
        return todosLosEmpleados.iterator();
    }
} 