package es.daw2.tarea92.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.daw2.tarea92.modelos.Grupo;


@Repository
public interface RepositorioGrupo extends CrudRepository <Grupo, Long> {
    
    List<Grupo> findAll();

    Grupo agregarGrupo(Grupo nuevoGrupo);

}
