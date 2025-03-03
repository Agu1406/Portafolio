package es.daw.repositorios;

import org.springframework.data.repository.CrudRepository;

import es.daw.modelos.Grupo;

public interface RepositorioGrupo extends CrudRepository<Grupo, Long> {
    /*
     * Un repositorio provee las funciones básicas de un CRUD:
     * CREATE: save(argumento)
     * READ: findAll(), findById(argumento)
     * UPDATE: save(argumento) con entidad existente
     * DELETE: delete(argumento)
     */

    /**
     * Métodos heredados de CrudRepository:
     * 
     * - save(Grupo grupo): Guarda una entidad Grupo en la base de datos.
     * Si el grupo ya existe (tiene un ID), lo actualiza; si no, lo crea.
     * 
     * @param grupo El grupo a guardar o actualizar
     * @return El grupo guardado con su ID asignado (en caso de ser nuevo)
     * 
     *         - findAll(): Recupera todos los grupos de la base de datos.
     * @return Un objeto Iterable<Grupo> con todos los grupos
     * 
     *         - findById(Long id): Busca un grupo por su ID.
     * @param id El ID del grupo a buscar
     * @return Un objeto que contiene el grupo si existe
     * 
     *         - delete(Grupo grupo): Elimina un grupo de la base de datos.
     * @param grupo El grupo a eliminar
     * 
     *              - deleteById(Long id): Elimina un grupo por su ID.
     * @param id    El ID del grupo a eliminar
     * 
     *              - count(): Retorna el número total de grupos en la base de
     *              datos.
     * @return El número total de grupos
     * 
     *         - existsById(Long id): Verifica si existe un grupo con el ID
     *         especificado.
     * @param id El ID del grupo a verificar
     * @return true si existe, false si no
     */
}