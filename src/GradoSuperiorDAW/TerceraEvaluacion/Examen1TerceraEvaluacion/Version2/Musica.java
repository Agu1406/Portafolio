package GradoSuperiorDAW.TerceraEvaluacion.Examen1TerceraEvaluacion.Version2;
import java.time.LocalDate;

public class Musica extends Ejemplar {
    private final int numCancionesDisco;
    Musica(String titulo, String autor, LocalDate fechaPub, int numCancionesDisco) {
        super(titulo, autor, fechaPub);
        this.numCancionesDisco = numCancionesDisco;
    }

    @Override
    public void alquilar() {
        // La fecha de alquiler sera la fecha en el momento de invocar el método.
        LocalDate fechaAlqMusica = LocalDate.now();
        // La fecha de devolución sera la misma fecha pero sumando dos semanas.
        LocalDate fechaDevMusica = fechaAlqMusica.plusDays(5);
        // Modificamos el valor de "null" a la nueva fecha de devolución.
        super.setFechaDev(fechaDevMusica);
        // Modificamos el valor de "null" a la nueva fecha de alquiler.
        super.setFechaAlq(fechaAlqMusica);
    }
}
