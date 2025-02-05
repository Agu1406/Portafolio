package es.daw2.tarea92.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw2.tarea92.modelos.Alumno;
import es.daw2.tarea92.repositorio.RepositorioAlumno;

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