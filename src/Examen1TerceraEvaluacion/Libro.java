package Examen1TerceraEvaluacion;

import java.time.LocalDate;

public class Libro extends Ejemplar {
    private final int numPaginasLibro;
    Libro (String titulo, String autor, LocalDate fechaPub, int numPaginasLibro) {
        super(titulo, autor, fechaPub);
        this.numPaginasLibro = numPaginasLibro;
    }


    @Override
    public void alquilar () {
        // La fecha de alquiler sera la fecha en el momento de invocar el método.
        LocalDate fechaAlquilerLibro = LocalDate.now();
        // La fecha de devolución sera la misma fecha pero sumandole un mes.
        LocalDate fechaDevolucionLibro = fechaAlquilerLibro.plusMonths(1);
        // Modificamos el valor de "null" a la nueva fecha de devolución.
        super.setFechaDev(fechaDevolucionLibro);
        // Modificamos el valor de "null" a la nueva fecha de alquiler.
        super.setFechaAlq(fechaAlquilerLibro);
    }

    @Override
    public void mostrar () {
        super.mostrar();
        System.out.println("N.º de paginas: " + numPaginasLibro);
    }
}
