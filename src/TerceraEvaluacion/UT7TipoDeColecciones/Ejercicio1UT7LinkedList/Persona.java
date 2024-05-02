package TerceraEvaluacion.UT7TipoDeColecciones.Ejercicio1UT7LinkedList;
/**
 * <h1 style="text-align: center;">Persona</h1>
 * <br>
 * <p style="text-align: justify;">
 * Esta clase representa a una persona que puede ser añadida a una cola de clientes.
 * Cada instancia de Persona tiene un código único de cliente generado automáticamente.
 * </p>
 *
 * @since 26/04/24
 * @version 1.0
 * @author Agu1406 (Agustín)
 */
public class Persona {
    private final String CODCLIENTE;
    private static int clientes = 1;

    /**
     * <h2 style="text-align: center;">Constructor</h2>
     * <br>
     * <p style="text-align: justify;">
     * Constructor de la clase Persona. Asigna un código único de cliente a cada instancia.
     * </p>
     */
    Persona() {
        this.CODCLIENTE = generarCodCliente();
        clientes++;
    }

    /**
     * <h2 style="text-align: center;">generarCodCliente</h2>
     * <br>
     * <p style="text-align: justify;">
     * Método privado que genera el código de cliente único para cada instancia de Persona.
     * </p>
     *
     * @return Un string que representa el código final del cliente.
     */
    private String generarCodCliente() {
        if (clientes >= 100) {
            clientes = 1;
        }
        String inicioCod = "CLI";
        String finalCod = String.format("%05d", clientes);

        return inicioCod + finalCod;
    }

    /**
     * <h2 style="text-align: center;">getCODCLIENTE</h2>
     * <br>
     * <p style="text-align: justify;">
     * Método getter para obtener el código de cliente de la instancia.
     * </p>
     *
     * @return El código de cliente de la instancia.
     */
    public String getCODCLIENTE() {
        return CODCLIENTE;
    }
}
