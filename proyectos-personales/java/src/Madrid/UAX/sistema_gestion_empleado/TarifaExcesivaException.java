package Madrid.UAX.sistema_gestion_empleado;

public class TarifaExcesivaException extends RuntimeException {
    public TarifaExcesivaException(String mensaje) {
        super(mensaje);
    }
} 