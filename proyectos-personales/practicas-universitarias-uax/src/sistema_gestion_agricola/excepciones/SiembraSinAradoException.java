package excepciones;

public class SiembraSinAradoException extends Exception {
    public SiembraSinAradoException() {
        super("No se puede sembrar sin arar la parcela primero");
    }
} 