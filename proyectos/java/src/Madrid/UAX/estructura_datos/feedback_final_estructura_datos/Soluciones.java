package feedback_final;

/**
 * Soluciones al Feedback Final de Estructura de Datos
 */
public class Soluciones {
    
    /**
     * Ejercicio 1 - Análisis de Complejidad
     * 
     * Método 1:
     * - Mejor caso (case 0 y case 1): O(log x)
     *   - En case 0: O(1) - retorno inmediato
     *   - En case 1: O(log x) - bucle que multiplica i por 2
     * - Peor caso (case 2): O(∞) - bucle while infinito
     * 
     * Método 2:
     * - Complejidad: O(x * log x)
     *   - Bucle exterior: O(x) - itera x-1 veces
     *   - Bucle interior: O(log x) - j se multiplica por 2
     *   - Total: O(x * log x)
     */
    
    /**
     * Ejercicio 2 - Ordenación por Inserción Directa
     * 
     * Secuencia inicial: 1 0 6 4 8 2 4 7 7
     * 
     * Fases de ordenación:
     * 1. [1] 0 6 4 8 2 4 7 7
     * 2. [0 1] 6 4 8 2 4 7 7
     * 3. [0 1 6] 4 8 2 4 7 7
     * 4. [0 1 4 6] 8 2 4 7 7
     * 5. [0 1 4 6 8] 2 4 7 7
     * 6. [0 1 2 4 6 8] 4 7 7
     * 7. [0 1 2 4 4 6 8] 7 7
     * 8. [0 1 2 4 4 6 7 8] 7
     * 9. [0 1 2 4 4 6 7 7 8]
     * 
     * Sobre el pivote en Quicksort:
     * - El pivote es el elemento que se usa para dividir el array en dos subarrays
     * - Es importante porque afecta directamente al rendimiento del algoritmo
     * - Una mala elección puede llevar a O(n²) en el peor caso
     * - Ejemplo de peor caso: array ordenado [1,2,3,4,5] usando el primer elemento como pivote
     */
    
    /**
     * Ejercicio 3 - Comparador de Alumnos
     */
    public static boolean comparar(Alumno alum1, Alumno alum2) {
        // Primero comparamos por provincia
        int compProvincia = alum1.provincia.compareTo(alum2.provincia);
        if (compProvincia != 0) {
            return compProvincia < 0;
        }
        
        // Si la provincia es igual, comparamos por apellidos
        int compApellidos = alum1.apellidos.compareTo(alum2.apellidos);
        if (compApellidos != 0) {
            return compApellidos < 0;
        }
        
        // Si los apellidos son iguales, comparamos por NP
        return alum1.NP < alum2.NP;
    }
    
    /**
     * Ejercicio 4 - Libros con número de registro par
     */
    public static ArrayList<Libro> librosPares(ArrayList<Libro> libros) {
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Libro libro : libros) {
            // Extraemos la parte numérica del registro (sin las letras)
            String numStr = libro.numRegistro.substring(0, 4);
            int num = Integer.parseInt(numStr);
            if (num % 2 == 0) {
                resultado.add(libro);
            }
        }
        return resultado;
    }
    
    /**
     * Ejercicio 5 - Modificación de Libro para TreeSet
     */
    static class Libro implements Comparable<Libro> {
        String autor;
        String numRegistro;
        int añoEdición;
        
        @Override
        public int compareTo(Libro otro) {
            // Primero comparamos por año
            int compAño = Integer.compare(this.añoEdición, otro.añoEdición);
            if (compAño != 0) {
                return compAño;
            }
            // Si el año es igual, comparamos por número de registro
            return this.numRegistro.compareTo(otro.numRegistro);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Libro)) return false;
            Libro otro = (Libro) obj;
            return this.añoEdición == otro.añoEdición && 
                   this.numRegistro.equals(otro.numRegistro);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(añoEdición, numRegistro);
        }
    }
    
    /**
     * Ejercicio 6 - Autor con más libros
     */
    public static String autorMasLibros(TreeSet<Libro> libros) {
        Map<String, Integer> contadorAutores = new HashMap<>();
        
        // Contamos libros por autor
        for (Libro libro : libros) {
            contadorAutores.merge(libro.autor, 1, Integer::sum);
        }
        
        // Encontramos el autor con más libros
        String autorMasLibros = null;
        int maxLibros = 0;
        
        for (Map.Entry<String, Integer> entry : contadorAutores.entrySet()) {
            if (entry.getValue() > maxLibros) {
                maxLibros = entry.getValue();
                autorMasLibros = entry.getKey();
            }
        }
        
        return autorMasLibros;
    }
    
    /**
     * Ejercicio 7 - Suma total de sueldos
     */
    public static int totalSueldos(ListaJugadores lista) {
        int total = 0;
        NodoJugador actual = lista.cabecera;
        
        while (actual != null) {
            total += actual.jugador.getSueldoTotal();
            actual = actual.siguiente;
        }
        
        return total;
    }
    
    /**
     * Ejercicio 8 - Imprimir jugadores con sueldo alto
     */
    public static void imprimeSueldosAltos(ArbolBusquedaJugador arbol, int sueldoMinimo) {
        imprimeSueldosAltosRecursivo(arbol.raiz, sueldoMinimo);
    }
    
    private static void imprimeSueldosAltosRecursivo(NodoJugador nodo, int sueldoMinimo) {
        if (nodo == null) return;
        
        // Recorremos el árbol en orden
        imprimeSueldosAltosRecursivo(nodo.izq, sueldoMinimo);
        
        if (nodo.jugador.getSueldoTotal() > sueldoMinimo) {
            System.out.println("Nombre: " + nodo.jugador.nombre + 
                             ", Equipo: " + nodo.jugador.equipo);
        }
        
        imprimeSueldosAltosRecursivo(nodo.der, sueldoMinimo);
    }
}

// Clases auxiliares necesarias
class Alumno {
    String nombre;
    String apellidos;
    String provincia;
    int NP;
}

class Libro {
    String autor;
    String numRegistro;
    int añoEdición;
}

class Jugador {
    String nombre;
    String equipo;
    String posición;
    int sueldo;
    int primas;
    
    public int getSueldo() { return sueldo; }
    public int getPrimas() { return primas; }
    public int getSueldoTotal() { return sueldo + primas; }
}

class NodoJugador {
    Jugador jugador;
    NodoJugador siguiente;
    NodoJugador izq;
    NodoJugador der;
}

class ListaJugadores {
    NodoJugador cabecera;
}

class ArbolBusquedaJugador {
    NodoJugador raiz;
} 