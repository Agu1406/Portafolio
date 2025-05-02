package sistema_juego_ajedrez.ejercicio3;

import java.util.LinkedList;

public abstract class Pieza {
    private String color;
    private String posicion;

    public Pieza(String color, String posicion) {
        this.color = color;
        this.posicion = posicion;
    }

    public String getColor() {
        return color;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public abstract boolean esMovimientoValido(String posicionDestino);
    public abstract LinkedList<String> obtenerMovimientosPosibles();
} 