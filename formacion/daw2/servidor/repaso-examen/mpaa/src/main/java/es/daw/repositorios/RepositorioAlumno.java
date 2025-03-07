package es.daw.repositorios;

import org.springframework.stereotype.Repository;
import es.daw.modelos.Alumno;
import org.springframework.data.repository.CrudRepository;

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
     * Si el alumno ya existe (tiene un ID), lo actualiza; si no, lo crea.
     * 
     * - findAll(): Recupera todos los alumnos de la base de datos.
     * Retorna un objeto Iterable<Alumno> con todos los alumnos.
     * 
     * - findById(Long id): Busca un alumno por su ID.
     * Retorna un Optional<Alumno> que puede contener el alumno si existe.
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
