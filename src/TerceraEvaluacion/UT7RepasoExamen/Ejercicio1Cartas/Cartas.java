package TerceraEvaluacion.UT7RepasoExamen.Ejercicio1Cartas;

import java.util.Objects;

public class Cartas {
    private final int numero; // Almacena el número de la carta (del 1 al 13)
    private final int palo; // Almacena el tipo de carta ("Diamantes", "Picas", "Corazones", "Tréboles")
    private final int color; // Elige automáticamente el color de la carta ("Rojo" o "Negro")
    private final int simbolo; // El tipo de carta, pero con sus símbolos ("♦️", "♠️", "♥️", "♣️")

    /*
    * Son variables del tipo int porque serán indicadores de la posición de un String Array,
    * por ejemplo una carta con los atributos palo = 1, color = 1 y simbolo = 1 sería una
    * carta de Picas, negra, con el emoji "♠️" porque el "1" en cada uno de los siguientes
    * String Arrays ocupa esas posiciones:
    * */
    private static final String[] palos = {"Diamantes", "Picas", "Corazones", "Treboles"};
    private static final String[] colores = {"Rojo", "Negro"};
    private static final String[] simbolos = {"♦️", "♠️", "♥️", "♣️"};

    /*
    * Para controlar que haya siempre un máximo de 13 cartas de cada tipo nos creamos unos
    * contadores de instancias, cada vez que una nueva carta de cualquier tipo sea creada
    * el contador incrementara de valor, alcanzado el número "13" usaremos los contadores
    * para parar la creación de nuevas cartas y lanzar una excepción que especifique que
    * no es posible crear más cartas de ese tipo.
    * */

    private static int cartasD = 0; // Registra cuantas cartas "diamante" han sido creadas.
    private static int cartasP = 0; // Registra cuantas cartas "pica" han sido creadas.
    private static int cartasC = 0; // Registra cuantas cartas "corazón" han sido creadas.
    private static int cartasT = 0; // Registra cuantas cartas "trébol" han sido creadas.

    /*
    * Sabemos por el enunciado que hay 4 tipos de cartas y tiene dos colores, los diamantes y
    * los corazones son rojos y las picas y los tréboles son negros, además, hay "13" cartas
    * por cada palo, sabiendo eso desarrollaremos el programa, constructores, métodos, etc.
    * */

    /**
     * Constructor que recibe como parámetros el palo (números del "0" al "3") y
     * el color (números "0" o "1") y en base a eso le asigna a una nueva
     * carta un palo, un color y un símbolo.
     *
     * @param palo números del "0" al "3".
     */
    public Cartas (int numero, int palo) {
        // Validación del número de la carta
        if (numero < 1 || numero > 13) {
            throw new IllegalArgumentException("¡Número no válido! Debe ser un número entre 1 y 13.");
        }
        // Validación del palo
        if (palo < 0 || palo > 3) {
            throw new IllegalArgumentException("¡Palo no válido! Debe ser un número entre 0 y 3.");
        }

        // Método que verifica que no existan más de 13 cartas de un tipo.
        verificarMaximoCartas(palo);

        // Método que dependiendo del palo asigna automáticamente un color a la carta.
        int color = asignarColor(palo);


        // Superado todos los desafíos, creamos una nueva instancia de carta.
        this.palo = palo; // Puede ser cualquier número del "0" al "3".
        this.color = color; // Puede ser rojo "0" o negro "1".
        this.simbolo = palo; // Sabiendo el palo, sabemos el símbolo también.
        this.numero = numero; // Le asigna un número del "1" al "13".

    }

    private static void verificarMaximoCartas(int palo) {
        // Verificar que no haya más de 13 cartas de cada tipo
        switch (palo) {
            case 0:
                if (cartasD >= 13) throw new IllegalArgumentException("¡Error! Ya existen 13 cartas de Diamantes.");
                cartasD++;
                break;
            case 1:
                if (cartasP >= 13) throw new IllegalArgumentException("¡Error! Ya existen 13 cartas de Picas.");
                cartasP++;
                break;
            case 2:
                if (cartasC >= 13) throw new IllegalArgumentException("¡Error! Ya existen 13 cartas de Corazones.");
                cartasC++;
                break;
            case 3:
                if (cartasT >= 13) throw new IllegalArgumentException("¡Error! Ya existen 13 cartas de Tréboles.");
                cartasT++;
                break;
        }
    }

    private static int asignarColor(int palo) {
        int color;
        if (palo == 1 || palo == 3) {
            color = 1;
        } else {
            color = 0;
        }
        return color;
    }

    /**
     * Método que imprime la información de una carta, lee sus atributos que
     * son números e imprime en los String Arrays la información que esté
     * en la posición de dichos números.
     *
     * @return Información completa de una carta.
     */
    @Override
    public String toString() {
        return numero + " de " + palos[palo] + simbolos[simbolo] + colores[color] + "%n";
    }

    /**
     * Este método permite verificar si dos cartas son la misma carta, se consideran
     * la misma carta si son el mismo número de carta del mismo palo.
     *
     * @param o es la carta con la que se compara desde la que se llama el método.
     * @return true si son la misma carta, false si no.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cartas)) return false;
        Cartas cartas = (Cartas) o;
        return numero == cartas.numero && palo == cartas.palo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, palo);
    }
}
