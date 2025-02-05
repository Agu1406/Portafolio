package es.daw2.tarea92.repositorio;

import org.springframework.data.repository.CrudRepository;
import es.daw2.tarea91.modelo.Grupo;

public class RepositorioGrupo extends CrudRepository<Grupo, Long> {
    List<Grupo> findAll();

    Grupo save(Grupo nuevoGrupo);

}
