package sistema_gestion_agricola.excepciones;

public class CultivoSinAradoException extends Exception {
    public CultivoSinAradoException() {
        super("No se puede cultivar sin arar la parcela primero");
    }
} 