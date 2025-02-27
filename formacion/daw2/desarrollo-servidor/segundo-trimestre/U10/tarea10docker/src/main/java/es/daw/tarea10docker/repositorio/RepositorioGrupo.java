package es.daw.tarea10docker.repositorio;

import es.daw.tarea10docker.modelos.Grupo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que extiende CrudRepository para la entidad Grupo
 * Proporciona operaciones básicas de persistencia
 */
@Repository
public interface RepositorioGrupo extends CrudRepository<Grupo, Long> {
    /*
     * Un repositorio provee las funciones básicas de un CRUD:
     * CREATE: save(argumento)
     * READ: findAll(), findById(argumento)
     * UPDATE: save(argumento) con entidad existente
     * DELETE: delete(argumento)
     */
}
