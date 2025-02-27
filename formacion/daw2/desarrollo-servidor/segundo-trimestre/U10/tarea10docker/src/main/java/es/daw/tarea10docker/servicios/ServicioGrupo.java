package es.daw.tarea10docker.servicios;

import es.daw.tarea10docker.modelos.Grupo;
import es.daw.tarea10docker.repositorio.RepositorioGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioGrupo implements IFServicioGrupo {

    @Autowired
    private RepositorioGrupo repositorioGrupo;

    @Override
    public Grupo agregarGrupoVacio(Grupo nuevoGrupo) {
        // Asegurarse que no hay alumnos asociados
        nuevoGrupo.setAlumnos(null);
        return repositorioGrupo.save(nuevoGrupo);
    }

    @Override
    public Grupo agregarGrupoLleno(Grupo nuevoGrupo) {
        throw new UnsupportedOperationException("Unimplemented method 'agregarGrupoLleno'");
    }

    @Override
    public List<Grupo> obtenerGrupos() {
        return (List<Grupo>) repositorioGrupo.findAll();
    }

    @Override
    public List<Grupo> obtenerGrupoId(String ies) {
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
    public Grupo actualizarGrupo(Long idGrupo, Grupo nuevoGrupo) {
        Grupo grupoExistente = obtenerGrupoPorId(idGrupo);
        grupoExistente.setIes(nuevoGrupo.getIes());
        grupoExistente.setCiclo(nuevoGrupo.getCiclo());
        grupoExistente.setCurso(nuevoGrupo.getCurso());
        return repositorioGrupo.save(grupoExistente);
    }

    @Override
    public Grupo borrarGrupo(Long idGrupo) {
        // Guar
        return null;
    }

    public Grupo obtenerGrupoPorId(Long id) {
        return repositorioGrupo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo no encontrado"));
    }

    public List<Grupo> findAll() {
        return (List<Grupo>) repositorioGrupo.findAll();
    }
}