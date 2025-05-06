package Curso.UT2;

public class Operadores {
    public static void main(String[] args) {

        // Operadores aritméticos
        int suma = 5 + 3;
        int resta = 5 - 3;
        int multiplicacion = 5 * 3;
        int division = 5 / 3; // 1
        int modulo = 5 % 3; // 0.6666666

        // Operadores de comparación.
        boolean sonIguales1 = (5 == 3); // False, no son iguales.
        boolean sonIguales2 = (5 == 5); // True, son iguales.

        boolean sonDiferentes1 = (5 != 3); // True, son diferentes.
        boolean sonDiferentes2 = (5 != 5); // False, no son diferentes.

        boolean esMayor = (5 > 3); // True, "5" es mayor que "3".
        boolean esMenor = (5 < 3); // False, "5" no es menor que "3".

        // Operadores logicos (viene de puertas logicas).
        boolean and = (true && false && true);
        boolean or = (true || false);
        boolean not = !true;

        // Operadores de asignación.
        int vidasDeMario = 3;

        vidasDeMario++; // "++" significa "sumar 1".
        vidasDeMario--; // "--" significa "restar 1".

        int ejemplo1 = 10;

        ejemplo1 += 5; // ejemplo = 10 + 5;
        ejemplo1 -=5; // ejemplo = 15 - 5;

    }
}
