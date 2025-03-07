package es.daw.tarea10docker.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase que representa la entidad Grupo en la base de datos.
 * Esta clase define la estructura y relaciones de un grupo académico en el sistema.
 * 
 * @author Agustín (Agu1406)
 * @version 1.0
 */
// LOMBOK: Generar al compilar "Getters" y "Setters".
/**
 * @Data - Anotación de Lombok que genera automáticamente:
 * - Métodos getter y setter para todos los campos
 * - Métodos equals() y hashCode() basados en todos los campos
 * - Método toString() que incluye todos los campos
 */
@Data
// LOMBOK: Generar dal compilar un constructor sin argumentos.
/**
 * @NoArgsConstructor - Anotación de Lombok que genera un constructor sin argumentos,
 * requerido por JPA para la creación de instancias durante la carga desde la base de datos.
 */
@NoArgsConstructor
// LOMBOK: Generar al compilar un constructor con todos los argumentos.
/**
 * @AllArgsConstructor - Anotación de Lombok que genera un constructor
 * que incluye todos los campos de la clase como parámetros.
 */
@AllArgsConstructor
// JPA: Especifica que la clasa es una entidad en una base de datos.
/**
 * @Entity - Anotación JPA que marca esta clase como una entidad persistente,
 * lo que significa que será mapeada a una tabla en la base de datos.
 */
@Entity
// JPA: Especifica la tabla principal de la entidad.
/**
 * @Table - Anotación JPA que especifica el nombre de la tabla en la base de datos
 * a la que se mapeará esta entidad.
 */
@Table(name = "GRUPOS")
public class Grupo {
    // "Id" especifica la clave primeraria de la tabla.
    /**
     * Identificador único del grupo.
     * Actúa como clave primaria en la tabla GRUPOS.
     */
    @Id
    // Permite que la clave se genera automaticamente de forma incrementativa.
    /**
     * @GeneratedValue - Indica que el valor de este campo será generado automáticamente
     * por la base de datos. Por defecto usa la estrategia AUTO, que deja al proveedor
     * de persistencia elegir la estrategia más adecuada.
     */
    @GeneratedValue
    private Long idGrupo;

    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.
    /**
     * Nombre del instituto al que pertenece el grupo.
     * No puede ser nulo y se define como un varchar(25) en la base de datos.
     */
    @Column(name = "IES", nullable = false, columnDefinition = "varchar(25)")
    private String ies;
    
    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.
    /**
     * Nombre del ciclo formativo al que pertenece el grupo.
     * No puede ser nulo y se define como un varchar(125) en la base de datos.
     */
    @Column(name = "CICLO", nullable = false, columnDefinition = "varchar(125)")
    private String ciclo;
    
    // Define una columna de la tabla, si su valor puede ser null y el tipo de dato.
    /**
     * Curso académico del grupo (por ejemplo, "1" para primer curso).
     * No puede ser nulo y se define como un integer con valor por defecto 1.
     */
    @Column(name = "CURSO", nullable = false, columnDefinition = "integer default 1")
    private String curso;

    // Un grupo puede tener uno o varios alumnos (1:N)
    /**
     * Lista de alumnos que pertenecen a este grupo.
     * Define una relación uno a muchos con la entidad Alumno.
     * 
     * @OneToMany - Indica que un grupo puede tener muchos alumnos.
     * mappedBy = "grupo" - Indica que la relación está mapeada por el campo "grupo" en la entidad Alumno,
     * lo que significa que la tabla Alumno contiene la clave foránea.
     */
    @OneToMany(mappedBy = "grupo")
    // Lista de alumnos pertenecientes a "X" grupo.
    private List<Alumno> alumnos;
}
