package es.daw.modelos;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

// Anotación "Data": Genera automaticamente los Getters y Setters.
@Data
// Anotación "NoArgsConstructor": Genera automaticamente un constrcutor sin
// argumentos.
@NoArgsConstructor
// Anotación "AllArgsConstrcutor": Genera automaticamente un constrcutor con
// todos los argumentos.
@AllArgsConstructor

@Entity
@Table(name = "GRUPOS")
public class Grupo {
    // Identifica cual atributo es la clave primaria en la base de datos.
    @Id
    // Indica que el valor del ID se genera automaticamente.
    @GeneratedValue
    // Usamos una variable del tipo "Long" como ID de los grupos.
    private Long idGrupo;

    /*
     * Identifica el atributo como una columna en la base de datos, también
     * nos permite elegir si el valor puede o no ser nulo y nos permite
     * tipear el dato en variables SQL.
     */
    @Column(name = "IES", nullable = false, columnDefinition = "varchar(30)")
    private String ies;
    /*
     * Identifica el atributo como una columna en la base de datos, también
     * nos permite elegir si el valor puede o no ser nulo y nos permite
     * tipear el dato en variables SQL.
     */
    @Column(name = "CICLO", nullable = false, columnDefinition = "varchar(30)")
    private String ciclo;
    /*
     * Identifica el atributo como una columna en la base de datos, también
     * nos permite elegir si el valor puede o no ser nulo y nos permite
     * tipear el dato en variables SQL.
     */
    @Column(name = "CURSO", nullable = false, columnDefinition = "varchar(30)")
    private String curso;
    /*
     * Establece una relación 1:N entre "grupos" y "alumnos" queriendo decir
     * que un grupo (1) puede tener varios alumnos (N) pero no viceversa,
     * por eso el mappedBy es de grupo, porque grupo es el lider de la
     * relación.
     */
    @OneToMany(mappedBy = "grupo")
    private List<Alumno> alumnos;
}
