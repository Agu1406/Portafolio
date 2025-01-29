package es.daw2.tarea91.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    // "Id" especifica la clave primeraria de la tabla.
    @Id
    // Permite que la clave se genera automaticamente de forma incrementativa.
    @GeneratedValue
    private Long idAlumno;
    
    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.    
    @Column (name = "NOMNRE", nullable = false, columnDefinition = "varchar(25)")
    private String nombre;

    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.
    @Column (name = "APELLIDO", nullable = false, columnDefinition = "varchar(25)")
    private String apellido;

    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.
    @Column (name = "EMAIL", nullable = false, columnDefinition = "varchar(120)")
    private String email;

    

    // Indica que un alumno solo pertenece a un grupo.
    @ManyToOne
    // Ignora (evita) llevar datos JSON a la base de datos.
    @JsonIgnore
    // Une la tabla con una FK a la tabla/entidad de grupos.
    @JoinColumn (name = "idGrupo")
    private Grupo grupo;
}
