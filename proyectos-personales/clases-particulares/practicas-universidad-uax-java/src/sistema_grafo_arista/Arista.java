package sistema_grafo_arista;
public class Arista {
    private Nodo v1;
    private Nodo v2;
    private int peso;

    public Arista(Nodo v1, Nodo v2, int peso) {
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
    }

    public Nodo getV1() {
        return v1;
    }

    public Nodo getV2() {
        return v2;
    }

    public int getPeso() {
        return peso;
    }
} 