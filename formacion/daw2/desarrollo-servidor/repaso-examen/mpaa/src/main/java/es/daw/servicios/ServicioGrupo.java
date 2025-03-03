package es.daw.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import es.daw.modelos.Grupo;
import es.daw.repositorios.RepositorioGrupo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioGrupo implements IFServicioGrupo {

    @Autowired
    private RepositorioGrupo repositorioGrupo;


    @Override
    public Grupo agregarGrupoVacio(Grupo nuevoGrupo) {

        nuevoGrupo.setAlumnos(null);

        return repositorioGrupo.save(nuevoGrupo);
    }

    @Override
    public Grupo agregarGrupoLleno(Grupo nuevoGrupo) {

        if (nuevoGrupo.getAlumnos() == null || nuevoGrupo.getAlumnos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El grupo debe tener alumnos asociados");
        }
        return repositorioGrupo.save(nuevoGrupo);
    }

    @Override
    public List<Grupo> obtenerGrupos() {
        return (List<Grupo>) repositorioGrupo.findAll();
    }

    @Override
    public List<Grupo> obtenerGrupoId(String ies) {
        List<Grupo> gruposFiltrados = new ArrayList<>();
        List<Grupo> grupos = (List<Grupo>) repositorioGrupo.findAll();
        for (Grupo grupo : grupos) {
            if (grupo.getIes().equalsIgnoreCase(ies)) {
                gruposFiltrados.add(grupo);
            }
        }
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
        Grupo grupo = obtenerGrupoPorId(idGrupo);
        if (grupo.getAlumnos() != null && !grupo.getAlumnos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede eliminar un grupo que tiene alumnos asociados");
        }
        repositorioGrupo.deleteById(idGrupo);
        return grupo;
    }

    public Grupo obtenerGrupoPorId(Long id) {
        return repositorioGrupo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo no encontrado"));
    }

    public List<Grupo> findAll() {
        return (List<Grupo>) repositorioGrupo.findAll();
    }
}