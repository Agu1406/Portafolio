package es.daw.tarea10docker.servicios;

import es.daw.tarea10docker.modelos.Grupo;

import java.util.List;

/**
 * Interfaz que define las operaciones disponibles para la gestión de Grupos.
 * Define el contrato que debe implementar ServicioGrupo.
 * 
 * Esta interfaz proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre la entidad Grupo, así como otras operaciones específicas.
 * 
 * @author Agustín (Agu1406)
 * @version 1.0
 */
public interface IFServicioGrupo  {
    /*
     * Métodos CRUD y operaciones específicas:
     * - agregarGrupoVacio: Crear grupo sin alumnos
     * - agregarGrupoLleno: Crear grupo con alumnos
     * - obtenerGrupos: Listar todos los grupos
     * - obtenerGrupoId: Filtrar grupos por IES
     * - actualizarGrupo: Modificar datos sin afectar alumnos
     * - borrarGrupo: Eliminar grupo si no tiene alumnos
     */

    /**
     * Crea un nuevo grupo sin alumnos asociados.
     * 
     * @param nuevoGrupo Objeto Grupo con los datos del nuevo grupo
     * @return El grupo creado con su ID asignado
     */
    // CRUD (CREATE) Permite agregar un nuevo grupo vacio (sin alumnos).
    public abstract Grupo agregarGrupoVacio (Grupo nuevoGrupo);

    /**
     * Crea un nuevo grupo con alumnos asociados.
     * 
     * @param nuevoGrupo Objeto Grupo con los datos del nuevo grupo y su lista de alumnos
     * @return El grupo creado con su ID asignado
     */
    // CRUD (CREATE) Permite agregar un nuevo grupo con alumnos.
    public abstract Grupo agregarGrupoLleno (Grupo nuevoGrupo);

    /**
     * Obtiene todos los grupos almacenados en la base de datos.
     * 
     * @return Lista de todos los grupos existentes
     */
    // CRUD (READ) Permite listar todos los grupos existentes.
    public abstract List<Grupo> obtenerGrupos();

    /**
     * Filtra los grupos por el nombre del instituto (IES).
     * 
     * @param ies Nombre del instituto a utilizar como filtro
     * @return Lista de grupos que pertenecen al instituto especificado
     */
    // CRUD (READ) Permite listar grupos filtrando por su "IES".
    public abstract List<Grupo> obtenerGrupoId(String ies);

    /**
     * Actualiza los datos de un grupo existente sin modificar sus alumnos asociados.
     * 
     * @param idGrupo Identificador único del grupo a actualizar
     * @param nuevoGrupo Objeto con los nuevos datos del grupo
     * @return El grupo actualizado
     */
    // CRUD (UPDATE) Permite actualizar el atributo de "X" grupo sin modificar sus alumnos.
    public abstract Grupo actualizarGrupo (Long idGrupo, Grupo nuevoGrupo);

    /**
     * Elimina un grupo de la base de datos.
     * Solo debe permitir eliminar grupos que no tengan alumnos asociados.
     * 
     * @param idGrupo Identificador único del grupo a eliminar
     * @return El grupo eliminado o null si no se pudo eliminar
     */
    // CRUD (DETELE) Permite borrar un grupo de la lista de grupos existente.
    public abstract Grupo borrarGrupo (Long idGrupo);
}
