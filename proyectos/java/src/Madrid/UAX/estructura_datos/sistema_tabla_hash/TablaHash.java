package Madrid.UAX.sistema_tabla_hash;
import java.util.HashMap;
import java.util.Map;

public class TablaHash {
    // Función hash: fHash(k) = (k² - 1)
    private static int funcionHash(int k) {
        return (k * k - 1) % 7;
    }

    // Función para imprimir el estado de la tabla
    private static void imprimirTabla(Map<Integer, Integer> tabla) {
        System.out.println("\nEstado actual de la tabla:");
        for (int i = 0; i < 7; i++) {
            System.out.print("Posición " + i + ": ");
            if (tabla.containsKey(i)) {
                System.out.println(tabla.get(i));
            } else {
                System.out.println("vacía");
            }
        }
    }

    public static void main(String[] args) {
        // Crear tabla hash de tamaño 7
        Map<Integer, Integer> tabla = new HashMap<>(7);
        
        // Valores a insertar
        int[] valores = {2, 6, 9, 8, 1};
        
        System.out.println("Insertando valores en la tabla hash...");
        for (int valor : valores) {
            int posicion = funcionHash(valor);
            System.out.println("\nInsertando valor: " + valor);
            System.out.println("Posición calculada: " + posicion);
            
            // En HashMap, si hay colisión, se maneja automáticamente
            tabla.put(posicion, valor);
            
            imprimirTabla(tabla);
        }
    }
} 