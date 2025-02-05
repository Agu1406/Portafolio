package es.daw2.tarea93.servicios;

import es.daw2.tarea93.modelos.Grupo;
import es.daw2.tarea93.repositorio.RepositorioGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioGrupo implements IFServicioGrupo {

    @Autowired
    RepositorioGrupo repositorioGrupo;


    @Override
    public List<Grupo> listarGrupos() {
        return (List<Grupo>) repositorioGrupo.findAll();
    }

    @Override
    public List<Grupo> listarGruposPorIes(String ies) {
        // Creamos una lista donde guardaremos los grupos filtrados.
        List<Grupo> gruposFiltrados = new ArrayList<>();
        // Generamos una lista con todos los grupos existentes.
        List<Grupo> grupos = (List<Grupo>) repositorioGrupo.findAll();
        // Recorremos todos los grupos uno por uno.
        for (Grupo grupo : grupos) {
            // Si el IES (sin distinguir mayúsculas) coincide.
            if (grupo.getIes().equalsIgnoreCase(ies)) {
                // Guardamos en la lista de filtrados ese "X" grupo.
                gruposFiltrados.add(grupo);
            }
        }
        // Devuelve al final una lista de grupos filtrados.
        return gruposFiltrados;
    }

    @Override
    public Grupo agregarGrupo(Grupo nuevoGrupo) {
        return repositorioGrupo.save(nuevoGrupo);
    }

    @Override
    public Grupo actualizarGrupo(Long idGrupo, Grupo nuevoGrupo) {
        // Guardamos en una lista todos los grupos existentes.
        List<Grupo> grupos = (List<Grupo>) repositorioGrupo.findAll();
        // Recorremos uno por uno todos los grupos con foreach
        for (Grupo grupo : grupos) {
            
        }
        return nuevoGrupo;
    }
}