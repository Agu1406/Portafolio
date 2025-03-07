package es.daw.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALUMNOS")
public class Alumno {
    @Id
    @GeneratedValue
    private Long idAlumno;

    @Column(name = "NOMBRE", nullable = false, columnDefinition = "varchar(25)")
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, columnDefinition = "varchar(25)")
    private String apellido;

    @Column(name = "EMAIL", nullable = false, columnDefinition = "varchar(120)")
    private String email;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idGrupo")
    private Grupo grupo;
}
