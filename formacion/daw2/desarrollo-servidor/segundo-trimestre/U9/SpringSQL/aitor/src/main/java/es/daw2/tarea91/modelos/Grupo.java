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
@Table (name = "GRUPOS")
public class Grupo {
    @Id @GeneratedValue
    private Long id;

    @Column (name = "IES", nullable = false, columnDefinition = "varchar(25)")
    private String ies;

    @Column (name = "CICLO", nullable = false, columnDefinition = "varchar(125)")
    private String ciclo;

    @Column (name = "CURSO", nullable = false, columnDefinition = "integer default 1")
    private String curso;
}
