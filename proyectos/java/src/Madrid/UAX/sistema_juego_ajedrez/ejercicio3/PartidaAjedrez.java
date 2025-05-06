package Madrid.UAX.sistema_juego_ajedrez.ejercicio3;

import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class PartidaAjedrez {
    private String jugadorBlancas;
    private String jugadorNegras;
    private LinkedList<String> movimientos;
    private Map<String, Pieza> tablero;
    private String turno; // "blancas" o "negras"

    public PartidaAjedrez() {
        this.movimientos = new LinkedList<>();
        this.tablero = new HashMap<>();
    }

    public void iniciarPartida(String jugadorBlancas, String jugadorNegras) {
        this.jugadorBlancas = jugadorBlancas;
        this.jugadorNegras = jugadorNegras;
        this.movimientos.clear();
        this.tablero.clear();
        this.turno = "blancas";

        // Inicializar peones
        for (char c = 'a'; c <= 'h'; c++) {
            tablero.put(c + "2", new Peon("blancas", c + "2"));
            tablero.put(c + "7", new Peon("negras", c + "7"));
        }

        // Inicializar torres
        tablero.put("a1", new Torre("blancas", "a1"));
        tablero.put("h1", new Torre("blancas", "h1"));
        tablero.put("a8", new Torre("negras", "a8"));
        tablero.put("h8", new Torre("negras", "h8"));

        // Inicializar piezas simuladas (sin funcionalidad)
        // Caballos
        tablero.put("b1", new PiezaSimulada("blancas", "b1", "♞"));
        tablero.put("g1", new PiezaSimulada("blancas", "g1", "♞"));
        tablero.put("b8", new PiezaSimulada("negras", "b8", "♞"));
        tablero.put("g8", new PiezaSimulada("negras", "g8", "♞"));

        // Alfiles
        tablero.put("c1", new PiezaSimulada("blancas", "c1", "♝"));
        tablero.put("f1", new PiezaSimulada("blancas", "f1", "♝"));
        tablero.put("c8", new PiezaSimulada("negras", "c8", "♝"));
        tablero.put("f8", new PiezaSimulada("negras", "f8", "♝"));

        // Reinas
        tablero.put("d1", new PiezaSimulada("blancas", "d1", "♛"));
        tablero.put("d8", new PiezaSimulada("negras", "d8", "♛"));

        // Reyes
        tablero.put("e1", new PiezaSimulada("blancas", "e1", "♚"));
        tablero.put("e8", new PiezaSimulada("negras", "e8", "♚"));
    }

    public boolean realizarMovimiento(String movimiento) {
        // Validar formato del movimiento
        if (!movimiento.matches("[a-h][1-8]-[a-h][1-8]")) {
            return false;
        }

        String[] partes = movimiento.split("-");
        String origen = partes[0];
        String destino = partes[1];

        Pieza pieza = obtenerPiezaEnPosicion(origen);
        if (pieza == null) {
            return false;
        }

        // Verificar que la pieza pertenece al jugador que tiene el turno
        if (!pieza.getColor().equals(turno)) {
            return false;
        }

        // Verificar que el movimiento es válido
        if (!pieza.esMovimientoValido(destino)) {
            return false;
        }

        // Realizar el movimiento
        pieza.setPosicion(destino);
        tablero.remove(origen);
        tablero.put(destino, pieza);
        movimientos.add(movimiento);

        // Cambiar el turno
        turno = turno.equals("blancas") ? "negras" : "blancas";
        return true;
    }

    public Pieza obtenerPiezaEnPosicion(String posicion) {
        return tablero.get(posicion);
    }

    public boolean esJaqueMate() {
        // Implementación simplificada: verificar si el rey está en jaque y no puede moverse
        // En una implementación real, se necesitaría verificar todas las piezas y sus movimientos
        return false;
    }

    public boolean esEmpate() {
        // Implementación simplificada: verificar si hay menos de 50 movimientos sin capturas
        // En una implementación real, se necesitaría verificar más condiciones
        return movimientos.size() >= 50;
    }

    public LinkedList<String> obtenerMovimientosPosibles(String posicion) {
        Pieza pieza = obtenerPiezaEnPosicion(posicion);
        if (pieza == null) {
            return new LinkedList<>();
        }
        return pieza.obtenerMovimientosPosibles();
    }

    public LinkedList<String> obtenerMovimientos() {
        return new LinkedList<>(movimientos);
    }

    public void mostrarTablero() {
        String lineaSeparadora = "+---+---+---+---+---+---+---+---+";
        
        // Mostrar letras de columnas alineadas
        System.out.println();
        System.out.print("    "); // Cuatro espacios iniciales para alinear con el borde
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.print(c + "   "); // Tres espacios después de cada letra
        }
        System.out.println();
        
        System.out.println(lineaSeparadora);
        
        for (int fila = 8; fila >= 1; fila--) {
            StringBuilder linea = new StringBuilder();
            linea.append(fila).append("| "); // Número de fila y primer separador con espacio
            
            for (char columna = 'a'; columna <= 'h'; columna++) {
                String posicion = columna + String.valueOf(fila);
                Pieza pieza = tablero.get(posicion);
                if (pieza != null) {
                    if (pieza instanceof PiezaSimulada) {
                        linea.append(((PiezaSimulada) pieza).getSimbolo() + " | ");
                    } else if (pieza instanceof Peon) {
                        linea.append("♟ | ");
                    } else if (pieza instanceof Torre) {
                        linea.append("♜ | ");
                    }
                } else {
                    linea.append("  | "); // Dos espacios para casillas vacías + separador con espacio
                }
            }
            
            linea.append(fila); // Número de fila al final
            System.out.println(linea.toString());
            System.out.println(lineaSeparadora);
        }
        
        // Mostrar letras de columnas alineadas
        System.out.print("    "); // Cuatro espacios iniciales para alinear con el borde
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.print(c + "   "); // Tres espacios después de cada letra
        }
        System.out.println();
    }
} 