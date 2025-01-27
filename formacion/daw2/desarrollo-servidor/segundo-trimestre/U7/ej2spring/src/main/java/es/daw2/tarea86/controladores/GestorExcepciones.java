package es.daw2.tarea86.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

/**
 * Clase "GestorExcepciones" que permite gestionar las excepciones
 * que se producen en la aplicación, se usan anotaciones de Spring
 * para indicar que es un controlador de excepciones.
 */
@ControllerAdvice
public class GestorExcepciones {
    /**
     * Método que permite gestionar las excepciones de tipo "Exception"
     * que se producen en la aplicación, se devuelve un mensaje de error
     * y un código de estado "500", el mensaje se obtiene del objeto
     * "exception" que se recibe como parámetro.
     * @param exception
     * @return
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException exception) {
        return ResponseEntity.badRequest().body(exception.getReason());
    }
}