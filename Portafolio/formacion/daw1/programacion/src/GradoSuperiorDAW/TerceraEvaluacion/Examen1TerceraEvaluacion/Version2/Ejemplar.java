package GradoSuperiorDAW.TerceraEvaluacion.Examen1TerceraEvaluacion.Version2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Ejemplar {
    private final String codigo;
    private final String titulo;
    private final String autor;
    private final LocalDate fechaPub;
    public LocalDate fechaAlq;
    public LocalDate fechaDev;
    public boolean alquilado;

    private static int numeroEjemplares = 1;

    Ejemplar(String titulo, String autor, LocalDate fechaPub) {
        this.codigo = generarCodigoEjemplar();
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPub = fechaPub;
        numeroEjemplares++;
        this.fechaDev = null;
        this.fechaAlq = null;
        this.alquilado = false;
    }

    private String generarCodigoEjemplar() {
        String inicioCod = "BB";
        String finalCod = String.format("%05d", numeroEjemplares);
        return inicioCod + finalCod;
    }

    public abstract void alquilar();

    public void mostrar() {
        System.out.println("Información General: " + "\n" +
                "Titulo: " + this.titulo + "\n" +
                "Autor: " + this.autor + "\n");
    }

    public void mostrarAmppliado() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Información del Ejemplar: " +
                "Fecha de publicación: " + this.fechaPub.format(formato) + "\n" +
                "Código de ejemplar: " + this.codigo + "\n" +
                "Titulo: " + this.titulo + "\n" +
                "Autor: " + this.autor + "\n");

        if (!alquilado) {
            System.out.println("El ejemplar esta disponible para alquilar. ");
        } else {
            this.fechaAlq.format(formato);
            this.fechaDev.format(formato);
        }
    }

    public void setFechaAlq(LocalDate fechaAlq) {
        this.fechaAlq = fechaAlq;
    }

    public void setFechaDev(LocalDate fechaDev) {
        this.fechaDev = fechaDev;
    }
}
