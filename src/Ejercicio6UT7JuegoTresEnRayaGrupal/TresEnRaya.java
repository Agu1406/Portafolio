package Ejercicio6UT7JuegoTresEnRayaGrupal;

public class TresEnRaya {
    private Tablero tablero;
    private Jugador[] jugadores;
    private Turno turno;
    private static final char FICHA_JUG_1 = 'X';
    private static final char FICHA_JUG_2 = 'O';

    public TresEnRaya() {
        this.jugadores = new Jugador[2];
        this.jugadores[0] = new Jugador(FICHA_JUG_1);
        this.jugadores[1] = new Jugador(FICHA_JUG_2);
        this.turno = new Turno(0);
        this.tablero = new Tablero();
    }

    public void jugar() throws excepcionTurnoNoValido {
        boolean seguirJugando = true;
        /*
        * La primera vez que empieza el juego, solicito el nombre de
        * los dos jugadores
        * */
        System.out.print("¡Hola Jugador 0! ");
        jugadores[0].putNombre();
        System.out.print("¡Hola Jugador 1! ");
        jugadores[1].putNombre();

        tablero.mostrar();

        // Bucle que sigue pidiendo jugadas hasta que alguien gane.
        do {
            // Indica de quien es el turno de colocar una ficha.
            System.out.println("Es turno del jugador "+turno);
            // llama a la fncion de colocar ficha del jugador en turno.
            jugadores[turno.quienToca()].colocaFicha(tablero);
            // Muestra el tablero actual.
            tablero.mostrar();
            // Cambia el turno para que en la siguiente vuelta, juegue otro.
            turno.cambiar();
            // Comprueba si hay 3 fichas en raya, si las hay, finaliza el bucle.
            seguirJugando = !tablero.hayTresEnRaya();

        } while (seguirJugando);
    }

    public static void main(String[] args) throws excepcionTurnoNoValido {
        TresEnRaya juego = new TresEnRaya();
        juego.jugar();
    }
}

