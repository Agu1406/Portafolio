package es.daw.tarea10docker.repositorio;

import es.daw.tarea10docker.modelos.Alumno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define el repositorio para la entidad Alumno.
 * Extiende CrudRepository para heredar operaciones CRUD básicas.
 * 
 * La anotación @Repository indica que esta interfaz es un componente de Spring
 * que maneja operaciones de acceso a datos y puede participar en la gestión de transacciones.
 * 
 * @author Agustín (Agu1406)
 * @version 1.0
 */
@Repository
public interface RepositorioAlumno extends CrudRepository<Alumno, Long> {
    /*
     * Un repositorio provee las funciones basicas de un CRUD
     * CREATE: save(argumento)
     * READ: findAll(arugmento)
     * READ: findById(argumento)
     * UPDATE: no tiene, se hace.
     * DELETE: delete(argumento).
     * etc... hay otros.
     */
    
    /**
     * Métodos heredados de CrudRepository:
     * 
     * - save(Alumno alumno): Guarda una entidad Alumno en la base de datos.
     *   Si el alumno ya existe (tiene un ID), lo actualiza; si no, lo crea.
     * 
     * - findAll(): Recupera todos los alumnos de la base de datos.
     *   Retorna un objeto Iterable<Alumno> con todos los alumnos.
     * 
     * - findById(Long id): Busca un alumno por su ID.
     *   Retorna un Optional<Alumno> que puede contener el alumno si existe.
     * 
     * - delete(Alumno alumno): Elimina un alumno de la base de datos.
     * 
     * - deleteById(Long id): Elimina un alumno por su ID.
     * 
     * - count(): Retorna el número total de alumnos en la base de datos.
     * 
     * - existsById(Long id): Verifica si existe un alumno con el ID especificado.
     */
}
