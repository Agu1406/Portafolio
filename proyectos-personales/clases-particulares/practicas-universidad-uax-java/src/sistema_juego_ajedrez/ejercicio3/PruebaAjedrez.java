package sistema_juego_ajedrez.ejercicio3;

public class PruebaAjedrez {
    public static void main(String[] args) {
        PartidaAjedrez partida = new PartidaAjedrez();
        
        // Iniciar una nueva partida
        partida.iniciarPartida("Jugador1", "Jugador2");
        System.out.println("Tablero inicial:");
        partida.mostrarTablero();
        
        // Realizar algunos movimientos
        System.out.println("\nRealizando movimientos...");
        
        // Movimiento válido de peón blanco
        if (partida.realizarMovimiento("e2-e4")) {
            System.out.println("Movimiento e2-e4 realizado correctamente");
        } else {
            System.out.println("Movimiento e2-e4 no válido");
        }
        
        // Movimiento válido de peón negro
        if (partida.realizarMovimiento("e7-e5")) {
            System.out.println("Movimiento e7-e5 realizado correctamente");
        } else {
            System.out.println("Movimiento e7-e5 no válido");
        }
        
        // Movimiento inválido (intentar mover pieza del oponente)
        if (partida.realizarMovimiento("e5-e6")) {
            System.out.println("Movimiento e5-e6 realizado correctamente");
        } else {
            System.out.println("Movimiento e5-e6 no válido (no es tu turno)");
        }
        
        // Mostrar estado actual del tablero
        System.out.println("\nEstado actual del tablero:");
        partida.mostrarTablero();
        
        // Mostrar movimientos realizados
        System.out.println("\nMovimientos realizados:");
        for (String movimiento : partida.obtenerMovimientos()) {
            System.out.println(movimiento);
        }
    }
} 