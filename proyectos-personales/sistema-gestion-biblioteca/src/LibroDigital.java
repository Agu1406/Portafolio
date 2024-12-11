public class LibroDigital extends Libro {
    private String formato;

    public LibroDigital(String titulo, String autor, String formato) {
        super(titulo, autor);
        this.formato = formato;
    }

    // Getters y setters
    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }

    @Override
    public void imprimir() {
        System.out.printf("Libro Digital - Título: %s, Autor: %s, Formato: %s%n", 
            getTitulo(), getAutor(), formato);
    }
} 