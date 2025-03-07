package es.daw.servicios;

import java.util.List;

import es.daw.modelos.Grupo;

public interface IFServicioGrupo {

    public abstract Grupo agregarGrupoVacio(Grupo nuevoGrupo);

    public abstract Grupo agregarGrupoLleno(Grupo nuevoGrupo);

    public abstract List<Grupo> obtenerGrupos();

    public abstract List<Grupo> obtenerGrupoId(String ies);

    public abstract Grupo actualizarGrupo(Long idGrupo, Grupo nuevoGrupo);

    public abstract Grupo borrarGrupo(Long idGrupo);
}