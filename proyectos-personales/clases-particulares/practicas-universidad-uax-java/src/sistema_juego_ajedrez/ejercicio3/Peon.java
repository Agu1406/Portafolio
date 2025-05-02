package sistema_juego_ajedrez.ejercicio3;

import java.util.LinkedList;

public class Peon extends Pieza {
    public Peon(String color, String posicion) {
        super(color, posicion);
    }

    @Override
    public boolean esMovimientoValido(String posicionDestino) {
        char columnaOrigen = getPosicion().charAt(0);
        int filaOrigen = Character.getNumericValue(getPosicion().charAt(1));
        char columnaDestino = posicionDestino.charAt(0);
        int filaDestino = Character.getNumericValue(posicionDestino.charAt(1));

        // Movimiento hacia adelante
        if (columnaOrigen == columnaDestino) {
            if (getColor().equals("blancas")) {
                // Primer movimiento puede ser de 2 casillas
                if (filaOrigen == 2) {
                    return filaDestino == filaOrigen + 1 || filaDestino == filaOrigen + 2;
                }
                return filaDestino == filaOrigen + 1;
            } else {
                // Primer movimiento puede ser de 2 casillas
                if (filaOrigen == 7) {
                    return filaDestino == filaOrigen - 1 || filaDestino == filaOrigen - 2;
                }
                return filaDestino == filaOrigen - 1;
            }
        }
        return false;
    }

    @Override
    public LinkedList<String> obtenerMovimientosPosibles() {
        LinkedList<String> movimientos = new LinkedList<>();
        char columna = getPosicion().charAt(0);
        int fila = Character.getNumericValue(getPosicion().charAt(1));

        if (getColor().equals("blancas")) {
            // Movimiento hacia adelante
            if (fila < 8) {
                movimientos.add(columna + String.valueOf(fila + 1));
                // Primer movimiento puede ser de 2 casillas
                if (fila == 2) {
                    movimientos.add(columna + String.valueOf(fila + 2));
                }
            }
        } else {
            // Movimiento hacia adelante
            if (fila > 1) {
                movimientos.add(columna + String.valueOf(fila - 1));
                // Primer movimiento puede ser de 2 casillas
                if (fila == 7) {
                    movimientos.add(columna + String.valueOf(fila - 2));
                }
            }
        }
        return movimientos;
    }
} 