package GradoSuperiorDAW.TerceraEvaluacion.UT7ObjetosAvanzados.Ejercicio6UT7JuegoTresEnRayaGrupal;

import java.util.Scanner;

public class Jugador {

    private String nombre;
    private char ficha;

    /**
     * Constructor que pide tanto la ficha del jugador como su nombre.
     *
     * @param nombre (nombre del jugador)
     * @param ficha (la ficha que usa para jugar)
     */
    public Jugador(String nombre, char ficha) {
        this.nombre = nombre;
        this.ficha = ficha;
    }

    /**
     * Constructor que estancia un jugador solo con su ficha.
     *
     * @param ficha (la ficha que usa para jugar)
     */
    public Jugador(char ficha) {
        this.ficha = ficha;
        this.nombre = null;
    }

    /**
     * Permite pedir por teclado el nombre de un jugador.
     */
    public void putNombre () {
        Scanner teclado = new Scanner(System.in);
        System.out.print("introduce tu nombre" + '\n');
        nombre = teclado.nextLine().trim();
    }

    /**
     * Solicita mediante teclado una fila y una columna, verifica con el metodo
     * esVacia de la clase Tablero si esa Coordenada (Estancia creada con
     * la fila y la columna que hemos solicitado por teclado), si esta
     * vacia, coloca la ficha en esas coordenadas, si no solicita otra
     * vez otras cordenadas, de forma que ingrese si o si unas
     * coordenadas validas.
     *
     * @param tablero trae el tablero actual, para saber que coordenadas están vacias.
     * @since 20/03/2024
     * @author Agutin.
     * @version 1.0
     */
    public void colocaFicha(Tablero tablero) {
        Scanner teclado = new Scanner(System.in);
        boolean continuar = true;
        int fila, columna;
        do { // Inicio de un bucle que sigue pidiendo coordenadas hasta que sean validas.
            System.out.println("Dime la fila (del 0 al 2)");
            fila = teclado.nextInt();
            System.out.println("Dime la columna (del 0 al 2)");
            columna = teclado.nextInt();

            Coordenada nuevaCoordenada = new Coordenada(fila, columna);

            /* Llama a un metodo del tableto, verifica si está vacia esa "casilla
             * y si está vacia, coloca la ficha en esa coordenada. */
            if (tablero.esVacia(nuevaCoordenada)) {
                tablero.ponFicha(ficha, nuevaCoordenada);
                continuar = false;
            } else {
                System.out.println("Esas coordenadas ya están ocupadas, intentalo de nuevo.");
            }
        } while (continuar == true);

    }
    public void mueveFicha(Tablero tablero) {
        Scanner teclado = new Scanner(System.in);
        boolean continuar = true;
        int fila, columna;
        do { // Inicio de un bucle que sigue pidiendo coordenadas hasta que sean validas.
            System.out.println("Dime la fila (del 0 al 2)");
            fila = teclado.nextInt();
            System.out.println("Dime la columna (del 0 al 2)");
            columna = teclado.nextInt();

            Coordenada coordenadaParaBorrar = new Coordenada(fila, columna);

            /* Llama a un metodo del tableto, verifica si está vacia esa "casilla
             * y si está vacia, coloca la ficha en esa coordenada. */
            if (!tablero.esVacia(coordenadaParaBorrar)) {
                tablero.quitaFicha(coordenadaParaBorrar);
                continuar = false;
            } else {
                System.out.println("Esas cordenadas están vacias, intentalo de nuevo.");
            }
        } while (continuar == true);

    }

    /**
     * Es un metodo que verifica si hay 3 en raya, si es así, muestra
     * el tablero e indica que el jugador que hizo el ultimo turno
     * es el ganador.
     *
     * @param tablero (importa el tablero actual y lo usa para revisar)
     */
    public void haGanado(Tablero tablero) {
        boolean ganaste;

        ganaste = tablero.hayTresEnRaya();
        // Si ganaste, se ejecuta el if, si no, no pasa nada.
        if (ganaste == true) {
            System.out.println("¡Felicidades! Has ganado");
            tablero.mostrar();
        }
    }
}
