package TerceraEvaluacion.UT7ObjetosAvanzados.Ejercicio4UT7ClaseDNI;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ClaseDNI {
    private int numeroDNI;
    private static final String DIGITOS_DE_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";

    public ClaseDNI(int numeroDNI) {
        this.numeroDNI = numeroDNI;
    }

    @Override
    public String toString() {
        return "Número DNI= " + numeroDNI +
                ", LETRA= " + calcularLetraNIF(numeroDNI);
    }

    private static char calcularLetraNIF(int dni) {
        return DIGITOS_DE_CONTROL.charAt(dni % 23);
    }

    public static boolean validarNIF(String numeroNIF) {
        numeroNIF = numeroNIF.toUpperCase();
        String nifRegex = "[0-9]{8}[A-Z]";

        return (numeroNIF.matches(nifRegex) && numeroNIF.charAt(8) == calcularLetraNIF(Integer.parseInt(numeroNIF.substring(0, 8))));
    }

    public static String aniadirCerosHasta9CharsDNI(String dni) {
        if (dni == null) throw new InputMismatchException("El Dni no puede ser nulo");
        if (dni.length() > 9) throw new InputMismatchException("Dni más largo de 9 caracteres");
        if (Character.isDigit(dni.charAt(dni.length() - 1))) throw new InputMismatchException("El último caracter del DNI no es una letra");

        while (dni.length() < 9) {
            dni = "0" + dni;
        }
        return dni;
    }

    public static String pedirDniHastaRecibirUnoValido() {
        Scanner scanner = new Scanner(System.in);
        String dni = null;
        do {
            try {
                System.out.print("Introduce un DNI: ");
                String dniTemporal = scanner.nextLine().toUpperCase();
                dniTemporal = aniadirCerosHasta9CharsDNI(dniTemporal);
                if (validarNIF(dniTemporal)) {
                    dni = dniTemporal;
                } else {
                    throw new InputMismatchException("El DNI no es válido");
                }
            } catch (InputMismatchException exc) {
                System.out.println(exc.getMessage());
            }
        } while (dni == null);
        return dni;
    }

    // GETTERS Y SETTERS

    public int getNumeroDNI() {
        return numeroDNI;
    }

    public String getNumeroNIF() {
        return String.valueOf(getNumeroDNI()) + calcularLetraNIF(numeroDNI);
    }

    public void setDNI(String nif) throws StringIndexOutOfBoundsException {
        if (!validarNIF(nif)) {
            throw new StringIndexOutOfBoundsException("FORMATO DE NIF NO VALIDO");
        } else {
            this.numeroDNI = Integer.parseInt(nif.substring(0, nif.length() - 1));
        }
    }

    public void setDNI(int numeroDNI) throws StringIndexOutOfBoundsException {
        if (String.valueOf(numeroDNI).length() < 2 || String.valueOf(numeroDNI).length() > 8) {
            throw new StringIndexOutOfBoundsException("FORMATO DE NÚMERO NO VALIDO. RECUERDA QUE TIENE QUE TENER UNA LONGITUD DE 8 CARACTERES");
        } else {
            this.numeroDNI = numeroDNI;
        }
    }
}
