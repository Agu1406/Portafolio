package es.daw2.tarea92.modelos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// LOMBOK: Generar al compilar "Getters" y "Setters".
@Data
// LOMBOK: Generar dal compilar un constructor sin argumentos.
@NoArgsConstructor
// LOMBOK: Generar al compilar un constructor con todos los argumentos.
@AllArgsConstructor
// JPA: Especifica que la clasa es una entidad en una base de datos.
@Entity
// JPA: Especifica la tabla principal de la entidad.
@Table(name = "GRUPOS")
public class Grupo {
    // "Id" especifica la clave primeraria de la tabla.
    @Id
    // Permite que la clave se genera automaticamente de forma incrementativa.
    @GeneratedValue
    private Long idGrupo;

    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.
    @Column(name = "IES", nullable = false, columnDefinition = "varchar(25)")
    private String ies;
    
    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.
    @Column(name = "CICLO", nullable = false, columnDefinition = "varchar(125)")
    private String ciclo;
    
    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.
    @Column(name = "CURSO", nullable = false, columnDefinition = "integer default 1")
    private String curso;

    // Un grupo puede tener uno o varios alumnos (1:N)
    @OneToMany(mappedBy = "grupo")
    // Lista de alumnos pertenecientes a "X" grupo.
    private List<Alumno> alumnos;
}
