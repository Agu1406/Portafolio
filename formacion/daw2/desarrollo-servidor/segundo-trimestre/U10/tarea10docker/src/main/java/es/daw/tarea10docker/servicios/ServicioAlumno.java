package es.daw.tarea10docker.servicios;

import es.daw.tarea10docker.modelos.Alumno;
import es.daw.tarea10docker.repositorio.RepositorioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioAlumno implements IFServicioAlumno {

    @Autowired
    RepositorioAlumno repositorioAlumno;

    @Override
    public List<Alumno> findAll() {
        return (List<Alumno>) repositorioAlumno.findAll();
    }

    @Override
    public Alumno agregarAlumno(Alumno nuevoAlumno) {
        return repositorioAlumno.save(nuevoAlumno);
    }

}