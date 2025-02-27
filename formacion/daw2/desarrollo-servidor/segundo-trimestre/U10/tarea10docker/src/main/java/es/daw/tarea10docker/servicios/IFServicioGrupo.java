package es.daw.tarea10docker.servicios;

import es.daw.tarea10docker.modelos.Grupo;

import java.util.List;

/**
 * Interfaz que define las operaciones disponibles para la gestión de Grupos
 * Define el contrato que debe implementar ServicioGrupo
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

    // CRUD (CREATE) Permite agregar un nuevo grupo vacio (sin alumnos).
    public abstract Grupo agregarGrupoVacio (Grupo nuevoGrupo);

    // CRUD (CREATE) Permite agregar un nuevo grupo con alumnos.
    public abstract Grupo agregarGrupoLleno (Grupo nuevoGrupo);

    // CRUD (READ) Permite listar todos los grupos existentes.
    public abstract List<Grupo> obtenerGrupos();

    // CRUD (READ) Permite listar grupos filtrando por su "IES".
    public abstract List<Grupo> obtenerGrupoId(String ies);

    // CRUD (UPDATE) Permite actualizar el atributo de "X" grupo sin modificar sus alumnos.
    public abstract Grupo actualizarGrupo (Long idGrupo, Grupo nuevoGrupo);

    // CRUD (DETELE) Permite borrar un grupo de la lista de grupos existente.
    public abstract Grupo borrarGrupo (Long idGrupo);
}
