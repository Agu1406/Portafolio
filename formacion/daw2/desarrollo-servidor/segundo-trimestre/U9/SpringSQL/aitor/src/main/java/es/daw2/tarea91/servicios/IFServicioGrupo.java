package es.daw2.tarea91.servicios;

import java.util.List;

import es.daw2.tarea91.modelos.Grupo;

public interface IFServicioGrupo {
    public abstract List<Grupo> findAll();
    public abstract Grupo agregarGrupo (Grupo g);
}
