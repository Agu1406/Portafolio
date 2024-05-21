package TerceraEvaluacion.UT7TipoDeColecciones.Ejercicio5UT7Umbrella;

import TerceraEvaluacion.UT7TipoDeColecciones.Ejercicio5UT7Umbrella.paqueteDNI.DNI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 */
public class Empleado {
    //Atributos
    private final String codigoEmpleado;
    private DNI dni;
    private String nombre;
    private String apellido;
    private String departamento;
    private double sueldo;
    private LocalDate fechaNacimiento;
    private LocalDate fechaContrato;
    // private static final String NOMBRE_EMPRESA = "UmbrellaCorp";
    // private static final LocalDate CREACION_EMPRESA = LocalDate.of(2016,5,2);
    public static double ayudaComida =110;
    public static double vigencia=20;
    public static int contadorEmpleado = 1;

    //Constructores

    public Empleado(int dni,String nombre, String apellido, String departamento, double sueldo, LocalDate fechaNacimiento, LocalDate fechaContrato) {
        this.codigoEmpleado = "UMBRE" + String.format("%05d",contadorEmpleado++);
        this.dni = new DNI(dni);
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        this.sueldo = sueldo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContrato = fechaContrato;
    }

    public Empleado(int dni) {
        this.codigoEmpleado = null;
    }

    public Empleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    //Getters
    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }
    public String getDepartamento() {
        return departamento;
    }
    //Metodos

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(codigoEmpleado, empleado.codigoEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoEmpleado);
    }

    void mostrarTodo() {
        DateTimeFormatter esDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("codigoEmpleado= " + codigoEmpleado + ","+
                "dni= " + dni.getNIF() + "," +
                "nombre= " + nombre + "," +
                "apellido= " + apellido + "," +
                "departamento= " + departamento + "," +
                "sueldo= " + sueldo + "," +
                "fechaNacimiento= " + fechaNacimiento.format(esDateFormat) + "," +
                "fechaContrato= " + fechaContrato.format(esDateFormat));
    }

    public void mostrarReducido() {
        System.out.println("codigoEmpleado= " + codigoEmpleado + ","+
                "dni= " + dni.getNIF() + "," +
                "nombre= " + nombre + "," +
                "departamento= " + departamento + "," +
                "sueldo= " + sueldo + ",");
    }

    public void VigenciaEmpresa (){
        LocalDate fechaActual = LocalDate.now();
        long anosVigencia =  ChronoUnit.YEARS.between(fechaContrato,fechaActual);
        System.out.println(anosVigencia);
    }

/*
        public boolean cumpleMes2(){
            LocalDate fechaActual = LocalDate.of(2024,02,12);
            LocalDate ultimoDia = fechaActual.with(TemporalAdjusters.lastDayOfMonth());
            System.out.println(ultimoDia);

            long difAno = fechaNacimiento.until(fechaActual,ChronoUnit.YEARS);
            long difMes = fechaNacimiento.until(fechaActual,ChronoUnit.MONTHS);

            long mesActual = Math.abs(difAno*12 - difMes);

            if (mesActual==0){
                System.out.println("Cumples este mes");
                return true;
            }else {
                System.out.println("Cumples en: " + mesActual + " meses.");
                return false;
            }
        }
*/

    public boolean cumpleMes(){
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonth().compareTo(fechaNacimiento.getMonth());
        return mesActual == 0;
    }

    public void sueldoMensual(){
        LocalDate fechaActual = LocalDate.now();
        long anosVigencia =  ChronoUnit.YEARS.between(fechaContrato,fechaActual);
        System.out.println(anosVigencia);
        if (cumpleMes()){
            double sueldoMes = (sueldo + vigencia* (int)anosVigencia)/12 + ayudaComida + 50;
            System.out.println(sueldoMes);
        }else {
            double sueldoMes = (sueldo + vigencia* (int)anosVigencia)/12 + ayudaComida;
            System.out.println(sueldoMes);
        }

    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public String getNombre() {
        return nombre;
    }

}
