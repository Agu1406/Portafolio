package Examen1TerceraEvaluacion;
import java.time.LocalDate;

public class Pelicula extends Ejemplar {

    private final int duracionPelicula;

    Pelicula (String titulo, String autor, LocalDate fechaPub, int duracionPelicula) {
        super(titulo, autor, fechaPub);
        this.duracionPelicula = duracionPelicula;
    }

    @Override
    public void alquilar() {
        // La fecha de alquiler sera la fecha en el momento de invocar el método.
        LocalDate fechaAlqPelicula = LocalDate.now();
        // La fecha de devolución sera la misma fecha pero sumando dos semanas.
        LocalDate fechaDevPelicula = fechaAlqPelicula.plusWeeks(2);
        // Modificamos el valor de "null" a la nueva fecha de devolución.
        super.setFechaDev(fechaDevPelicula);
        // Modificamos el valor de "null" a la nueva fecha de alquiler.
        super.setFechaAlq(fechaAlqPelicula);
    }

    @Override
    public void mostrar () {
        super.mostrar();
        System.out.println("Duración de la pelicula (en minutos): " + duracionPelicula);
    }
}
