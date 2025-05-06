package Madrid.UAX.sistema_juego_ajedrez.ejercicio3;

import java.util.LinkedList;

public class Torre extends Pieza {
    public Torre(String color, String posicion) {
        super(color, posicion);
    }

    @Override
    public boolean esMovimientoValido(String posicionDestino) {
        char columnaOrigen = getPosicion().charAt(0);
        int filaOrigen = Character.getNumericValue(getPosicion().charAt(1));
        char columnaDestino = posicionDestino.charAt(0);
        int filaDestino = Character.getNumericValue(posicionDestino.charAt(1));

        // La torre se mueve en línea recta (misma columna o misma fila)
        return columnaOrigen == columnaDestino || filaOrigen == filaDestino;
    }

    @Override
    public LinkedList<String> obtenerMovimientosPosibles() {
        LinkedList<String> movimientos = new LinkedList<>();
        char columna = getPosicion().charAt(0);
        int fila = Character.getNumericValue(getPosicion().charAt(1));

        // Movimientos horizontales
        for (char c = 'a'; c <= 'h'; c++) {
            if (c != columna) {
                movimientos.add(c + String.valueOf(fila));
            }
        }

        // Movimientos verticales
        for (int f = 1; f <= 8; f++) {
            if (f != fila) {
                movimientos.add(columna + String.valueOf(f));
            }
        }

        return movimientos;
    }
} 