public class LibroFisico extends Libro {
    private String ubicacion;

    public LibroFisico(String titulo, String autor, String ubicacion) {
        super(titulo, autor);
        this.ubicacion = ubicacion;
    }

    // Getters y setters
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    @Override
    public void imprimir() {
        System.out.printf("Libro Físico - Título: %s, Autor: %s, Ubicación: %s%n", 
            getTitulo(), getAutor(), ubicacion);
    }
} 