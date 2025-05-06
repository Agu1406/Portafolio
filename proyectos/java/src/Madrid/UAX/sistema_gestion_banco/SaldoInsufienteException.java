package Madrid.UAX.sistema_gestion_banco;

/**
 * Esta excepión (error) ocurre cuando el usuario intenta
 * retirar de su cuenta bancaria más saldo del que tiene
 * actualmente disponible.
 */
public class SaldoInsufienteException extends java.lang.Exception {
    // Envia al usuario un mensaje de error (String)
    public SaldoInsufienteException(String message) {
        super(message);
    }
}
