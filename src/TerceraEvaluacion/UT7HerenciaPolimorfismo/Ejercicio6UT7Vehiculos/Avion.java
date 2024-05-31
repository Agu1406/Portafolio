package TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio6UT7Vehiculos;

import java.time.LocalDate;

public class Avion extends VehiculoTransporte {
    private final double precioBaseAvion = 200000;
    private boolean tieneTripulacion;

    public Avion(String modelo, boolean tieneTripulacion) {
        super(modelo);
        this.tieneTripulacion = tieneTripulacion;
    }

    @Override
    public double venderVehiculo() {
        super.setFechaVenta(LocalDate.now());
        super.setVendido(true);
        return 0;
    }

    /**
     * Permite cambiar el estado de un Avión determinando si este
     * posee tripulación o no, es un elemento cambiante y puede
     * variar en el tiempo de estado en cada Avión.
     *
     * @param tieneTripulacion boolean, puede ser true o false.
     */
    public void setTieneTripulacion(boolean tieneTripulacion) {
        this.tieneTripulacion = tieneTripulacion;
    }



    /**
     *  Método que muestra toda la información de un Avión, utilizando el
     *  método de la clase más el apartado de información propia del
     *  avión que puede ser, si tiene tripulación o no lo tiene.
     */
    @Override
    public void mostrarTodo() {
        String respuesta = tieneTripulacion ? "Si, tiene tripulación" : "No tiene tripulación";
        super.mostrarTodo();
        System.out.printf("Información del avión: %n" +
                "¿Tiene tripulación? %s%n", respuesta);
    }

    @Override
    public void precioVenta() {
        System.out.println("El precio de venta de este avión es: " + precioBaseAvion);
    }
}
