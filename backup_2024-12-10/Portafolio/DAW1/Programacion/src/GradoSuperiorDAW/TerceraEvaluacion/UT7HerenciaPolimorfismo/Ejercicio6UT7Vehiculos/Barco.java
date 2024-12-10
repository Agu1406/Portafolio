package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio6UT7Vehiculos;

import java.time.LocalDate;

public class Barco extends VehiculoTransporte {
    public Barco(String modelo) {
        super(modelo);
    }

    @Override
    public double venderVehiculo() {
        super.setFechaVenta(LocalDate.now());
        super.setVendido(true);
        return 0;    }

    @Override
    public void precioVenta() {

    }
}
