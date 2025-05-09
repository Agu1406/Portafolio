package Curso.UT2;

public class PalabrasReservadas {

    public static void main(String[] args) {


        if (true) {

            for(int i = 0; i < 10; i++) {

                while (i < 5) {
                    break;
                }
            }
        }


    }

    private int variablePrivada;
    protected String variableProtegida;
    public double variablePublica;

    boolean booleano = true;
    char caracter = 'A';

}
