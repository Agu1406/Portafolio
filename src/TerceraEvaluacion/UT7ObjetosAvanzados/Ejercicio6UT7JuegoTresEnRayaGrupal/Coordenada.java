package TerceraEvaluacion.UT7ObjetosAvanzados.Ejercicio6UT7JuegoTresEnRayaGrupal;

import java.util.Objects;
import java.util.Scanner;

public class Coordenada {
    /*
     * Nuestra clase Coordenada contiene lo siguiente:
     * int fila
     * int columna
     * int getFila( )
     * int getColumna( )
     * static Coordenada pedirPorTeclado( )
     * */
    private int fila; // guarda la fila donde se desea interactuar una ficha.
    private int columna; // guarda la columna donde se desea interactuar con una ficha.

    /**
     * una estancia del tipo "Coordenada" se crean mediante la combinación de dos
     * valores enteros, una fila y una columna, que definen como se interactua
     * con una ficha o casilla del tablero.
     *
     * @param fila (Int que indica la fila con la que se desea interactuar)
     * @param columna (Int que indica la columna con la que se desea interactuar)
     * @author Agustin.
     * @since 20/03/2024
     * @version 1.0
     */
    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Coordenada{" +
                "fila=" + fila +
                ", columna=" + columna +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenada that = (Coordenada) o;
        return fila == that.fila && columna == that.columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }



    /**
     * Permite solicitar por teclado la fila y columna en la que se desea añadir
     * * A.K.A "colocar" una ficha.
     *
     * @return un objeto "Coordenada" que posee internamente fila y columna.
     * @author Agustin.
     * @since 20/03/2024
     */
    public static Coordenada pedirPorTeclado() {
        Scanner teclado = new Scanner(System.in);
        String expRegCordValida = "[0-2]";
        int fila, columna;

        do { // Bucle que sigue pidiendo una fila hasta que sea valida.
            System.out.println("Introduce la fila: ");
            fila = teclado.nextInt();

            // If que envia mensaje de error si la fila introducida no es valida.
            if (!String.valueOf(fila).matches(expRegCordValida)) {
                System.out.println("¡Error! debes introduce una fila valida [0, 1 o 2]");
                teclado.nextLine(); // Consume el salto de linea para evitar errores.
            }
            teclado.nextLine();
        } while (!String.valueOf(fila).matches(expRegCordValida));


        do { // Bucle que sigue pidiendo una columna hasta que sea valida.
            System.out.println("Introduce la columna");
            columna = teclado.nextInt();

            if (!String.valueOf(columna).matches(expRegCordValida)) {
                System.out.println("¡Error! debes introduce una columna valida [0, 1 o 2]");
                teclado.nextLine(); // Consume el salto de linea para evitar errores.
            }
            teclado.nextLine();
        } while (!String.valueOf(columna).matches(expRegCordValida));

        Coordenada coordenadaValida = new Coordenada(fila, columna);
        return coordenadaValida;
    }

    /**
     * Permite visualizar/ver la "fila" estanciada en el objeto "Coordenada".
     * @return el numero de fila.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Permite visualizar/ver la "columna" estanciada en el objeto "Coordenada".
     * @return el numero de columna.
     */
    public int getColumna() {
        return columna;
    }
}

