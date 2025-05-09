package Curso.UT2;

public class Conversiones {
    public static void main(String[] args) {

        int entero1 = 24; // 24

        double decimal1 = entero1; // 24.00;

        double decimal2 = 3.1416; // 3.1416

        int entero2 = (int) decimal2; // 3

        Object objeto = "Agustín"; // Objeto "Agustín".

        String nombre = (String) objeto; // String "Agustín".

        String numerosString = "123456789"; // String con los números "123456789".
        int numerosInt = Integer.parseInt(numerosString); // int con los números 123456789.

    }
}
