package TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio6UT7Vehiculos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class VehiculoTransporte
        implements Comparable<VehiculoTransporte> {

    private boolean vendido;
    private final String modelo;
    private LocalDate fechaVenta;
    private final String CODIGO_VEHICULO;
    private static int contadorInstancias = 0;
    private final DateTimeFormatter formatoSpain = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final String PREFIX = "VEH";


    public VehiculoTransporte (String modelo) {
        this.modelo = modelo;
        this.vendido = false;
        this.fechaVenta = null;
        this.CODIGO_VEHICULO = generarCod(contadorInstancias);
    }

    private String generarCod(int contadorInstancias) {
        String fin = String.format("%05d", ++contadorInstancias);
        return PREFIX + fin;
    }

    public String getCodigoVehiculo() {
        return this.CODIGO_VEHICULO;
    }

    @Override
    public String toString() {
        return "Información del Vehiculo:" + '\\' +
                "Código del Vehiculo: " + CODIGO_VEHICULO + '\\' +
                "Modelo del Vehiculo: " + modelo + '\\' +
                "Fecha de venta: " + (this.vendido ? fechaVenta.format(formatoSpain) : "No se ha vendido");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehiculoTransporte)) return false;
        VehiculoTransporte that = (VehiculoTransporte) o;
        return Objects.equals(CODIGO_VEHICULO, that.CODIGO_VEHICULO);
    }
    @Override
    public int hashCode() {
        return Objects.hash(CODIGO_VEHICULO);
    }

    public abstract double venderVehiculo();

    public void disponibilidad () {
        String disponibilidad = this.vendido ? "si" : "no";
        System.out.println("Actualmente el vehículo " + disponibilidad + " está vendido");
    }

    /**
     * Método que muestra toda la información general de un vehículo, para
     * cada clase hija debera de añadir la información específica del tipo
     * de vehículo además de la general que comparten todos los vehículos.
     */
    public void mostrarTodo () {
        String respuesta = vendido ? "Ya esta vendido" : "No esta vendido";

        String vendidoNull = (this.fechaVenta != null) ? this.fechaVenta.toString() : "aún no vendido";


        System.out.printf("Información completa: %n" +
                "Código del vehiculo: %s%n" +
                "Modelo del vehiculo: %s%n" +
                "Disponibilidad: %s%n" +
                "Fecha de venta: %s%n",
                this.CODIGO_VEHICULO,
                this.modelo,
                respuesta,
                this.fechaVenta);
    }

    @Override
    public int compareTo(VehiculoTransporte o) {
        if (this.equals(o)) return 0;
        return this.getCodigoVehiculo().compareTo(o.getCodigoVehiculo());
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public abstract void precioVenta ();
}
