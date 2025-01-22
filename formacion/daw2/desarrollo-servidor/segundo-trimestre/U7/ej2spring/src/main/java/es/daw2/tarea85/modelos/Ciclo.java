package es.daw2.tarea85.modelos;

import lombok.Data;
import lombok.NonNull;

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