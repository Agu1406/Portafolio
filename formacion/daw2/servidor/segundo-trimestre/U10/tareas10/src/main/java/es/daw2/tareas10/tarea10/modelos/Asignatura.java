package es.daw2.tareas10.tarea10.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura {
    // Variables propias de la clase.
    private String nombre;
    private String ciclo;
    private int horas;
    private int curso;

    public int getHorasTrimestre () {
        
        // Returna "0" hasta que defina correctamente el método.
        return 0;
    }
}
