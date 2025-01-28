package es.daw2.tarea91.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Anotaciones de lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
// Anotaciones de jakarta persistente
@Entity
@Table (name = "ALUMNOS")
public class Alumno {
    @Id @GeneratedValue
    private Long id;
    
    @Column (name = "NOMNRE", nullable = false, columnDefinition = "varchar(25)")
    private String nombre;

    @Column (name = "APELLIDO", nullable = false, columnDefinition = "varchar(25)")
    private String apellido;

    @Column (name = "EMAIL", nullable = false, columnDefinition = "varchar(120)")
    private String email;
}
