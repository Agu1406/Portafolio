package es.daw.tarea10docker.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la entidad Alumno en la base de datos.
 * Esta clase define la estructura y relaciones de un alumno en el sistema.
 * 
 * Utiliza anotaciones de Lombok para generar código repetitivo:
 * - @Data: Genera getters, setters, equals, hashCode y toString
 * - @NoArgsConstructor: Genera un constructor sin argumentos
 * - @AllArgsConstructor: Genera un constructor con todos los argumentos
 * 
 * Y anotaciones JPA para la persistencia:
 * - @Entity: Marca la clase como una entidad JPA
 * - @Table: Define el nombre de la tabla en la base de datos
 * 
 * @author Agustín (Agu1406)
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
// Anotaciones de jakarta persistente
@Entity
@Table (name = "ALUMNOS")
public class Alumno {
    // "Id" especifica la clave primeraria de la tabla.
    /**
     * Identificador único del alumno.
     * Actúa como clave primaria en la tabla ALUMNOS.
     */
    @Id
    // Permite que la clave se genera automaticamente de forma incrementativa.
    /**
     * La estrategia de generación es AUTO por defecto,
     * lo que permite que el proveedor de persistencia elija la estrategia más adecuada.
     */
    @GeneratedValue
    private Long idAlumno;
    
    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.    
    /**
     * Nombre del alumno.
     * No puede ser nulo y se define como un varchar(25) en la base de datos.
     */
    @Column (name = "NOMBRE", nullable = false, columnDefinition = "varchar(25)")
    private String nombre;

    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.
    /**
     * Apellido del alumno.
     * No puede ser nulo y se define como un varchar(25) en la base de datos.
     */
    @Column (name = "APELLIDO", nullable = false, columnDefinition = "varchar(25)")
    private String apellido;

    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.
    /**
     * Email del alumno.
     * No puede ser nulo y se define como un varchar(120) en la base de datos.
     */
    @Column (name = "EMAIL", nullable = false, columnDefinition = "varchar(120)")
    private String email;

    

    // Indica que un alumno solo pertenece a un grupo.
    /**
     * Relación muchos a uno con la entidad Grupo.
     * Un alumno pertenece a un único grupo, pero un grupo puede tener muchos alumnos.
     * 
     * @ManyToOne: Define la relación muchos a uno
     * @JsonIgnore: Evita la serialización circular al convertir a JSON
     * @JoinColumn: Especifica la columna de clave foránea en la tabla ALUMNOS
     */
    @ManyToOne
    // Ignora (evita) llevar datos JSON a la base de datos.
    @JsonIgnore
    // Une la tabla con una FK a la tabla/entidad de grupos.
    @JoinColumn (name = "idGrupo")
    private Grupo grupo;
}
