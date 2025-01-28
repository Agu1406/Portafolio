package es.daw2.tarea91.modelos;

import lombok.Data;
import lombok.NonNull;

/**
 * Clase "Ciclo" que representa un ciclo formativo y
 * permite instanciar objetos de tipo "Ciclo" usando
 * el constructor de la clase, se usan anotaciones de
 * Lombok para generar automáticamente los métodos
 * "getter" y "setter" de los atributos de la clase.
 * y las anotacones @Data y @NonNull, la primera para
 * generar automáticamente los métodos "getter" y "setter"
 * y la segunda para indicar que los atributos no pueden
 * ser nulos.
 */
@Data
public class Ciclo {

    @NonNull
    private String nombre;

    @NonNull
    private String especialidad;

    int numCursos;

    public Ciclo(String nombre, String especialidad, int numCursos) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.numCursos = numCursos;
    }
}