package Madrid.UAX.sistema_grafo_arista;
public class Nodo {
    private String nombre;

    public Nodo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean igual(Nodo v) {
        return this.nombre.equals(v.getNombre());
    }
} 