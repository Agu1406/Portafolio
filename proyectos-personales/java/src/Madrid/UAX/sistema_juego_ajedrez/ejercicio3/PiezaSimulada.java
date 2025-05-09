package Madrid.UAX.sistema_juego_ajedrez.ejercicio3;

import java.util.LinkedList;

public class PiezaSimulada extends Pieza {
    private String simbolo;

    public PiezaSimulada(String color, String posicion, String simbolo) {
        super(color, posicion);
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    @Override
    public boolean esMovimientoValido(String posicionDestino) {
        // Las piezas simuladas no pueden moverse
        return false;
    }

    @Override
    public LinkedList<String> obtenerMovimientosPosibles() {
        // Las piezas simuladas no tienen movimientos posibles
        return new LinkedList<>();
    }
} 