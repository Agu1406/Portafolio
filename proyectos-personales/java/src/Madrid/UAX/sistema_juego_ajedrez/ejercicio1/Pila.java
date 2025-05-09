package Madrid.UAX.sistema_juego_ajedrez.ejercicio1;

public class Pila {
    private static final int TAMANO_POR_DEFECTO = 10;
    private int[] elementos;
    private int tope;

    // Constructor con tamaño determinado
    public Pila(int tamano) {
        if (tamano <= 0) {
            throw new IllegalArgumentException("El tamaño debe ser mayor que 0");
        }
        this.elementos = new int[tamano];
        this.tope = -1;
    }

    // Constructor con tamaño por defecto
    public Pila() {
        this(TAMANO_POR_DEFECTO);
    }

    // Método para obtener el tamaño de la pila
    public int getTamano() {
        return elementos.length;
    }

    // Método para verificar si la pila está vacía
    public boolean estaVacia() {
        return tope == -1;
    }

    // Método para obtener el elemento en la cima de la pila
    public int getCima() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return elementos[tope];
    }

    // Método para insertar un elemento en la cima de la pila
    public void push(int elemento) {
        if (tope == elementos.length - 1) {
            throw new IllegalStateException("La pila está llena");
        }
        elementos[++tope] = elemento;
    }

    // Método para eliminar el elemento en la cima de la pila
    public int pop() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return elementos[tope--];
    }

    /***
     * Función que verifica si la pila esta vacía, si no lo está, muestra por consola
     * uno por uno todos los elementos de la pila.
     */
    public void mostrar() {
        // Verifica si la pila esta vacía, si lo está, no se pueden sacar elementos, lanza una excepción.
        if(estaVacia()) {
            throw new IllegalArgumentException("¡Error! La pila esta vacía, no se puede sacar más elementos de ella.");
        }

        System.out.println("Mostrando los elementos de la pila");
        for (int posicion = tope; posicion >= 0; posicion--) {
            System.out.println(elementos[posicion]);
        }
    }
} 