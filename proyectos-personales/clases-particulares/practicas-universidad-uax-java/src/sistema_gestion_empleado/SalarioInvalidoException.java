package sistema_gestion_empleado;

public class SalarioInvalidoException extends RuntimeException {
    public SalarioInvalidoException(String mensaje) {
        super(mensaje);
    }
} 