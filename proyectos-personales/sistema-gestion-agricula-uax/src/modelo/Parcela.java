package modelo;

public class Parcela {
    private String nombre;
    private String estado;
    private double superficieMetrosCuadrados;
    private double superficieHectareas;
    private double superficieObradas;

    public Parcela(String nombre, double superficieMetrosCuadrados) {
        this.nombre = nombre;
        this.superficieMetrosCuadrados = superficieMetrosCuadrados;
        this.estado = "sin cultivar";
        this.superficieHectareas = superficieMetrosCuadrados / 10000;
        this.superficieObradas = superficieMetrosCuadrados / 4000;
    }

    public void arar() {
        this.estado = "arada";
    }

    public void sembrar() {
        this.estado = "sembrada";
    }

    public void cultivar() {
        this.estado = "cultivada";
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + ", Estado: " + estado +
                           ", Superficie (m²): " + superficieMetrosCuadrados +
                           ", Superficie (ha): " + superficieHectareas +
                           ", Superficie (obradas): " + superficieObradas);
    }

    public String getEstado() {
        return estado;
    }
} 