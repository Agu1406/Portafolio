package UT7ObjetosAvanzados.Ejercicio8UT7GestionEmpleadosVersion3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase ClaseDNI</h1><br>
 * <p style="text-align: justify;">
 * Esta clase gestiona el DNI de un individuo, permitiendo su validación, la generación de la letra
 * correspondiente según el número y la corrección de formatos de entrada para asegurar la coherencia
 * de los datos. Proporciona funcionalidades para calcular la letra de un DNI, validar un NIF, y obtener
 * un DNI formateado adecuadamente con ceros para completar hasta 9 caracteres.
 * </p><br>
 * <h2 style="text-align: center;">Métodos Importantes</h2><br>
 * <ul>
 *   <li>calcularLetraNIF: Calcula la letra del NIF basándose en el número del DNI.</li>
 *   <li>validarNIF: Verifica si un NIF es válido en formato y letra.</li>
 *   <li>aniadirCerosHasta9CharsDNI: Añade ceros al inicio de un DNI hasta completar 9 caracteres.</li>
 *   <li>pedirDniHastaRecibirUnoValido: Solicita al usuario un DNI hasta que se ingrese uno válido.</li>
 * </ul><br>
 *
 * @version 3.0
 * @since 12/04/2024
 * @author Paula Diaz
 */
public class ClaseDNI {
    private int numeroDNI;
    private static final String DIGITOS_DE_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";

    public ClaseDNI(int numeroDNI) {
        this.numeroDNI = numeroDNI;
    }

    /**
     * <h2 style="text-align: center;">Método toString</h2><br>
     * <p style="text-align: justify;">
     * Devuelve una representación de cadena del objeto DNI, incluyendo tanto el número como la letra calculada.
     * </p>
     *
     * @return Cadena que representa el DNI completo.
     */
    @Override
    public String toString() {
        return "Número DNI= " + numeroDNI +
                ", LETRA= " + calcularLetraNIF(numeroDNI);
    }

    /**
     * <h2 style="text-align: center;">calcularLetraNIF</h2><br>
     * <p style="text-align: justify;">
     * Calcula la letra correspondiente al número del DNI según la normativa oficial.
     * </p>
     *
     * @param dni Número de DNI para el cual se calcula la letra.
     * @return Carácter que representa la letra del DNI.
     */
    private static char calcularLetraNIF(int dni) {
        return DIGITOS_DE_CONTROL.charAt(dni % 23);
    }

    /**
     * <h2 style="text-align: center;">validarNIF</h2><br>
     * <p style="text-align: justify;">
     * Valida si un NIF es correcto comprobando tanto el formato como la letra correcta según el número de DNI.
     * </p>
     *
     * @param numeroNIF NIF a validar.
     * @return true si el NIF es válido, false en caso contrario.
     */
    public static boolean validarNIF(String numeroNIF) {
        numeroNIF = numeroNIF.toUpperCase();
        String nifRegex = "[0-9]{8}[A-Z]";

        return (numeroNIF.matches(nifRegex) && numeroNIF.charAt(8) == calcularLetraNIF(Integer.parseInt(numeroNIF.substring(0, 8))));
    }

    /**
     * <h2 style="text-align: center;">aniadirCerosHasta9CharsDNI</h2><br>
     * <p style="text-align: justify;">
     * Formatea un número de DNI añadiendo ceros al inicio si es necesario hasta completar 9 caracteres,
     * asegurando que el último carácter sea una letra.
     * </p>
     *
     * @param dni Número de DNI a formatear.
     * @return DNI formateado con 9 caracteres.
     */
    public static String aniadirCerosHasta9CharsDNI(String dni) {
        if (dni == null) throw new InputMismatchException("El Dni no puede ser nulo");
        if (dni.length() > 9) throw new InputMismatchException("Dni más largo de 9 caracteres");
        if (Character.isDigit(dni.charAt(dni.length() - 1))) throw new InputMismatchException("El último caracter del DNI no es una letra");

        while (dni.length() < 9) {
            dni = "0" + dni;
        }
        return dni;
    }

    /**
     * <h2 style="text-align: center;">pedirDniHastaRecibirUnoValido</h2><br>
     * <p style="text-align: justify;">
     * Solicita repetidamente al usuario que introduzca un DNI hasta que se proporcione uno en formato válido.
     * </p>
     *
     * @return DNI validado.
     */
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
    public int getNumeroDNI() {
        return numeroDNI;
    }
    public String getNumeroNIF() {
        return String.valueOf(getNumeroDNI()) + calcularLetraNIF(numeroDNI);
    }
}
