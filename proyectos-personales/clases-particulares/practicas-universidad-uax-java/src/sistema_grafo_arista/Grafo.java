package sistema_grafo_arista;
public class Grafo {
    private Nodo[] nodos;
    private Arista[] aristas;
    private int numNodos;
    private int numAristas;

    public Grafo(int numNodos) {
        this.nodos = new Nodo[numNodos];
        this.aristas = new Arista[numNodos * numNodos]; // Máximo número posible de aristas
        this.numNodos = 0;
        this.numAristas = 0;
    }

    public boolean añadirArista(Arista arista) {
        if (numAristas < aristas.length) {
            aristas[numAristas++] = arista;
            return true;
        }
        return false;
    }

    public boolean añadirNodo(Nodo nodo) {
        if (numNodos < nodos.length) {
            nodos[numNodos++] = nodo;
            return true;
        }
        return false;
    }

    public boolean eliminarArista(Arista arista) {
        for (int i = 0; i < numAristas; i++) {
            if (aristas[i].getV1().igual(arista.getV1()) && 
                aristas[i].getV2().igual(arista.getV2())) {
                // Mover la última arista a esta posición
                aristas[i] = aristas[numAristas - 1];
                numAristas--;
                return true;
            }
        }
        return false;
    }

    public boolean eliminarNodo(Nodo nodo) {
        int pos = posicionNodo(nodo);
        if (pos != -1) {
            // Eliminar todas las aristas conectadas a este nodo
            for (int i = 0; i < numAristas; i++) {
                if (aristas[i].getV1().igual(nodo) || aristas[i].getV2().igual(nodo)) {
                    eliminarArista(aristas[i]);
                    i--; // Ajustar el índice ya que eliminamos una arista
                }
            }
            // Mover el último nodo a esta posición
            nodos[pos] = nodos[numNodos - 1];
            numNodos--;
            return true;
        }
        return false;
    }

    private int posicionNodo(Nodo nodo) {
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i].igual(nodo)) {
                return i;
            }
        }
        return -1;
    }

    public Nodo[] listaNodos() {
        Nodo[] resultado = new Nodo[numNodos];
        System.arraycopy(nodos, 0, resultado, 0, numNodos);
        return resultado;
    }

    public Arista[] listaAristas() {
        Arista[] resultado = new Arista[numAristas];
        System.arraycopy(aristas, 0, resultado, 0, numAristas);
        return resultado;
    }

    private int numeroDeAristas() {
        return numAristas;
    }

    private int numeroDeNodos() {
        return numNodos;
    }

    public Grafo limpieza() {
        // Crear un nuevo grafo con el mismo número máximo de nodos
        Grafo grafoLimpio = new Grafo(this.nodos.length);
        
        // 1. Copiar todos los nodos al nuevo grafo
        for (int i = 0; i < numNodos; i++) {
            grafoLimpio.añadirNodo(this.nodos[i]);
        }
        
        // 2. Copiar las aristas, cambiando el signo de las negativas
        for (int i = 0; i < numAristas; i++) {
            Arista arista = this.aristas[i];
            int nuevoPeso = arista.getPeso() < 0 ? -arista.getPeso() : arista.getPeso();
            Arista nuevaArista = new Arista(arista.getV1(), arista.getV2(), nuevoPeso);
            grafoLimpio.añadirArista(nuevaArista);
        }
        
        // 3. Eliminar nodos de grado 0
        boolean hayCambios;
        do {
            hayCambios = false;
            Nodo[] nodosActuales = grafoLimpio.listaNodos();
            for (Nodo nodo : nodosActuales) {
                boolean tieneAristas = false;
                Arista[] aristasActuales = grafoLimpio.listaAristas();
                for (Arista arista : aristasActuales) {
                    if (arista.getV1().igual(nodo) || arista.getV2().igual(nodo)) {
                        tieneAristas = true;
                        break;
                    }
                }
                if (!tieneAristas) {
                    grafoLimpio.eliminarNodo(nodo);
                    hayCambios = true;
                }
            }
        } while (hayCambios);
        
        return grafoLimpio;
    }
} 