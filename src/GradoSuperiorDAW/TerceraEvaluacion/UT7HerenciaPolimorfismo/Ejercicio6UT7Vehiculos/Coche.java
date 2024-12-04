package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio6UT7Vehiculos;

import java.time.LocalDate;

public class Coche extends VehiculoTransporte {
    public Coche(String modelo) {
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
