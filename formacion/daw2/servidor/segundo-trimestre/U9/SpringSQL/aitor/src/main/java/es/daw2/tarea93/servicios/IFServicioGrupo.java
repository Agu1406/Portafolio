package es.daw2.tarea93.servicios;

import es.daw2.tarea93.modelos.Grupo;

import java.util.List;

public interface IFServicioGrupo  {
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
