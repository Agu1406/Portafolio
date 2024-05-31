package TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio6UT7Vehiculos;
public class Main {

    public static void main(String[] args) {

        Avion v1 = new Avion("Boeing", true);

        // Imprimimos en pantalla la información completa de los vehículos de prueba.
        v1.mostrarTodo();

        // Consultamos la disponibilidad de los tres vehículos de prueba creados.
        v1.disponibilidad();

        // Probamos el método de vender un vehículo con la fecha inmediata.
        v1.venderVehiculo();

        // Probamos el set de la clase Avión para definir si tiene tripulación o no.
        v1.setTieneTripulacion(true);

        // Consultamos el precio de venta del avión V1
        v1.precioVenta();
    }
}
