package es.daw.tarea10docker.repositorio;

import es.daw.tarea10docker.modelos.Alumno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
}
