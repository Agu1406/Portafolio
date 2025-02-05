package es.daw2.tarea92.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.daw2.tarea91.modelos.Alumno;

@Repository
public interface RepositorioAlumno extends CrudRepository<Alumno, Long> {

    List<Alumno> findAll();
    /*
     * Un repositorio provee las funciones basicas de un CRUD
     * CREATE: save(argumento)
     * READ: findAll(arugmento)
     * READ: findById(argumento)
     * UPDATE: no tiene.
     * DELETE: delete().
     * etc... hay otros.
     */

    Alumno save(Alumno nuevoAlumno);
    

}
