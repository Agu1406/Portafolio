package excepciones;

public class HistorialVacioException extends Exception {
    public HistorialVacioException() {
        super("El historial de actividades está vacío");
    }
} 