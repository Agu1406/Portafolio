package es.daw2.tarea92.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw2.tarea92.modelos.Grupo;
import es.daw2.tarea92.repositorio.RepositorioGrupo;

@Service
public class ServicioGrupo implements IFServicioGrupo {

    @Autowired
    RepositorioGrupo repositorioGrupo;

    @Override
    public List<Grupo> findAll() {
        return (List<Grupo>) repositorioGrupo.findAll();
    }

    @Override
    public Grupo agregarGrupo(Grupo g) {
        return repositorioGrupo.save(g);
    }
}