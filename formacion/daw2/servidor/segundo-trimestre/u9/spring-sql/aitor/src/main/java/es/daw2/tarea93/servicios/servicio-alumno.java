package es.daw2.tarea93.servicios;

import es.daw2.tarea93.modelos.Alumno;
import es.daw2.tarea93.repositorio.RepositorioAlumno;
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