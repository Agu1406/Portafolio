package GradoSuperiorDAW.TerceraEvaluacion.UT7ObjetosAvanzados.Ejercicio8UT7GestionEmpleadosVersion3.Coorporacion;
import GradoSuperiorDAW.TerceraEvaluacion.UT7ObjetosAvanzados.Ejercicio8UT7GestionEmpleadosVersion3.ClaseDNI;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class Empleado {
    // Atributos estáticos que son comunes a todos los empleados
    public static final String NOMBRE_EMPRESA = "UMBRELLA CORP";
    public static final LocalDate CREACION_EMPRESA = LocalDate.of(2016, 5, 2);
    private static int numEmpleados = 0; // Atributo para contar el número de empleados
    private static float ayudaComida = 110; // Valor inicial que puede cambiar
    private static float compromiso = 200; // Gratificación anual que podría variar
    private ClaseDNI dniObjeto;
    private final String nombre;
    private final String apellido;
    private final String departamento;
    private Float sueldo;
    private final LocalDate fechaContrato;
    private final LocalDate fechaNacimiento;
    private String codigoEmpleado;

    public Empleado(String DNI, String nombre, String apellido, String departamento, Float sueldo,
                    LocalDate fechaContrato, LocalDate fechaNacimiento) {
        if (DNI == null || DNI.isEmpty()) throw new IllegalArgumentException("El DNI no puede ser nulo o vacío.");
        if (sueldo != null && sueldo < 0) throw new IllegalArgumentException("El sueldo no puede ser negativo.");
        if (fechaNacimiento != null && fechaContrato != null && fechaNacimiento.isAfter(fechaContrato))
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior a la fecha de contrato.");

        this.dniObjeto = new ClaseDNI(Integer.parseInt(DNI.substring(0, DNI.length() - 1)));  // Asumimos que el último carácter es la letra
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        this.sueldo = sueldo;
        this.fechaContrato = fechaContrato;
        this.fechaNacimiento = fechaNacimiento;
        generarCodigoEmpleado();
        numEmpleados++; // Incrementar el contador de empleados
    }

    public String getDNI() {
        return dniObjeto.getNumeroNIF();  // Retorna el DNI completo con letra
    }

    public String getDepartamento() {
        return departamento;
    }

    public Float getSueldo() {
        return sueldo;
    }

    @Override
    public String toString() {
        return "Información del empleado: \n" +
                "DNI: " + getDNI() + "\n" +
                "Nombre: " + this.nombre + "\n" +
                "Apellido: " + this.apellido + "\n" +
                "Departamento: " + this.departamento + "\n" +
                "Sueldo Actual: " + this.sueldo + "\n" +
                "Fecha de Alta: " + this.fechaContrato + "\n" +
                "Fecha de nacimiento: " + this.fechaNacimiento + "\n";
    }

    public void subirSalario(float porcentajeDeseado) {
        float factorDeIncremento = (porcentajeDeseado / 100) + 1;
        this.sueldo = this.sueldo * factorDeIncremento;
    }

    @Override
    public boolean equals(Object estancia) {
        if (this == estancia) return true;
        if (estancia == null || getClass() != estancia.getClass()) return false;
        Empleado empleado = (Empleado) estancia;
        return Objects.equals(this.getDNI(), empleado.getDNI());
    }

    public String toStringReducido() {
        return "DNI: " + getDNI() + ", Nombre: " + nombre + ", Sueldo: " + sueldo;
    }

    public void setSueldo(float nuevoSueldo) {
    }

    private void generarCodigoEmpleado() {
        this.codigoEmpleado = "UMBRE" + String.format("%04d", numEmpleados + 1);
    }

    public static void incrementarAyudaComida(float incremento) {
        ayudaComida += incremento;
    }

    public static void incrementarCompromiso(float incremento) {
        compromiso += incremento;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public LocalDate getFechaContrato() {
        return fechaContrato;
    }

    // Métodos adicionales para la clase Empleado

    // 1. Calcular antigüedad en la empresa
    public int calcularAntiguedad() {
        return Period.between(this.fechaContrato, LocalDate.now()).getYears();
    }

    // 2. Cumpleaños del empleado
    public boolean cumpleAniosEsteMes() {
        return this.fechaNacimiento.getMonth() == LocalDate.now().getMonth();
    }

    // 3. Mostrar todos los datos del empleado
    public String mostrarTodosLosDatos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd de MMMM de yyyy", new Locale("es", "ES"));
        return "Código Empleado: " + this.codigoEmpleado + "\n" +
                "DNI: " + String.format("%08d", Integer.parseInt(this.getDNI().substring(0, this.getDNI().length() - 1))) + this.getDNI().substring(this.getDNI().length() - 1) + "\n" +
                "Nombre: " + this.nombre + "\n" +
                "Apellido: " + this.apellido + "\n" +
                "Departamento: " + this.departamento + "\n" +
                "Antigüedad: " + this.calcularAntiguedad() + "\n" +
                "Edad: " + Period.between(this.fechaNacimiento, LocalDate.now()).getYears() + "\n" +
                "Fecha Contrato: " + this.fechaContrato.format(formatter);
    }

    // 4. Mostrar reducido
    public String mostrarReducido() {
        return "Código Empleado: " + this.codigoEmpleado + "\n" +
                "Nombre: " + this.nombre + "\n" +
                "Departamento: " + this.departamento;
    }

    // 5. Calcular sueldo mensual del empleado
    public float calcularSueldoMensual() {
        float sueldoBase = this.sueldo / 12;
        float bonoAntiguedad = this.calcularAntiguedad() * compromiso;
        float bonoAyudaComida = ayudaComida;
        float bonoCumpleanos = (this.cumpleAniosEsteMes()) ? 50 : 0;
        float sueldoMensual = sueldoBase + bonoAntiguedad + bonoAyudaComida + bonoCumpleanos;

        System.out.println("Detalles del sueldo mensual:");
        System.out.println("Sueldo Base: " + sueldoBase);
        System.out.println("Bono por Antigüedad: " + bonoAntiguedad);
        System.out.println("Ayuda para comida: " + bonoAyudaComida);
        System.out.println("Bono de Cumpleaños: " + bonoCumpleanos);

        return sueldoMensual;
    }
}
