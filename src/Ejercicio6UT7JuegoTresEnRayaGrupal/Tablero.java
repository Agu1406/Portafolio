package Ejercicio6UT7JuegoTresEnRayaGrupal;

public class Tablero {
    private char[][] tablero;
    private int filas;
    private int columnas;

    Tablero() {
        filas = 3;
        columnas = 3;
        tablero = new char[filas][columnas];
        for (int posFila = 0; posFila < filas; posFila++) {
            for (int posColumna = 0; posColumna < columnas; posColumna++) {
                tablero[posFila][posColumna] = '_';
            }
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public char[][] getTablero() {
        return tablero;
    }

    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void ponFicha(char ficha, Coordenada coord) {
        tablero[coord.getFila()][coord.getColumna()] = ficha;
    }

    public void quitaFicha(Coordenada coord) {
        tablero[coord.getFila()][coord.getColumna()] = '_';
    }

    public boolean esVacia(Coordenada coord) {
        return tablero[coord.getFila()][coord.getColumna()] == '_';
    }

    public boolean hayFicha(char ficha, Coordenada coord) {
        return tablero[coord.getFila()][coord.getColumna()] == ficha;
    }

    public boolean hayTresEnRaya() {
        // Revisar filas
        for (int posFila = 0; posFila < filas; posFila++) {
            if (tablero[posFila][0] != '_' && tablero[posFila][0] == tablero[posFila][1] && tablero[posFila][1] == tablero[posFila][2]) {
                return true;
            }
        }
        // Revisar columnas
        for (int posColumna = 0; posColumna < columnas; posColumna++) {
            if (tablero[0][posColumna] != '_' && tablero[0][posColumna] == tablero[1][posColumna] && tablero[1][posColumna] == tablero[2][posColumna]) {
                return true;
            }
        }
        // Revisar diagonales
        if (tablero[0][0] != '_' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true;
        }
        if (tablero[0][2] != '_' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true;
        }
        return false;
    }

    public void mostrar() {
        for (int posFila = 0; posFila < filas; posFila++) {
            for (int posColumna = 0; posColumna < columnas; posColumna++) {
                System.out.print(tablero[posFila][posColumna] + " ");
            }
            System.out.println();
        }
    }
}
