package Madrid.UAX.sistema_juego_ajedrez.ejercicio1;

public class PruebaPila {
    public static void main(String[] args) {
        // Crear una pila con tamaño por defecto
        Pila pila1 = new Pila();
        System.out.println("Pila 1 creada con tamaño por defecto: " + pila1.getTamano());
        
        // Crear una pila con tamaño específico
        Pila pila2 = new Pila(5);
        System.out.println("Pila 2 creada con tamaño 5: " + pila2.getTamano());
        
        // Probar operaciones con pila2
        System.out.println("\nOperaciones con pila2:");
        
        // Verificar si está vacía
        System.out.println("¿La pila está vacía? " + pila2.estaVacia());
        
        // Insertar elementos
        System.out.println("\nInsertando elementos...");
        pila2.push(10);
        pila2.push(20);
        pila2.push(30);
        
        // Mostrar elementos
        pila2.mostrar();
        
        // Ver cima
        System.out.println("\nElemento en la cima: " + pila2.getCima());
        
        // Eliminar elemento
        System.out.println("\nEliminando elemento de la cima: " + pila2.pop());
        pila2.mostrar();
        
        // Verificar si está vacía después de operaciones
        System.out.println("\n¿La pila está vacía? " + pila2.estaVacia());
    }
} 