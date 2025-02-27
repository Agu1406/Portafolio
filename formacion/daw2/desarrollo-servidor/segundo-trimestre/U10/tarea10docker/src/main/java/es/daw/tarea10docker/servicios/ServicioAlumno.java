package es.daw.tarea10docker.servicios;

import es.daw.tarea10docker.modelos.Alumno;
import es.daw.tarea10docker.repositorio.RepositorioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Servicio que implementa la lógica de negocio para la entidad Alumno
 * Proporciona métodos CRUD y otras operaciones específicas
 */
@Service
public class ServicioAlumno implements IFServicioAlumno {

    /*
     * Repositorio que provee las operaciones básicas de persistencia
     */
    @Autowired
    RepositorioAlumno repositorioAlumno;

    /*
     * CRUD (READ) Obtiene todos los alumnos existentes
     * @return Lista de todos los alumnos
     */
    @Override
    public List<Alumno> findAll() {
        return (List<Alumno>) repositorioAlumno.findAll();
    }

    @Override
    public Alumno obtenerAlumnoPorId(Long id) {
        return repositorioAlumno.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
    }

    @Override
    public Alumno agregarAlumno(Alumno nuevoAlumno) {
        return repositorioAlumno.save(nuevoAlumno);
    }

    @Override
    public Alumno actualizarAlumno(Long id, Alumno alumno) {
        Alumno alumnoExistente = obtenerAlumnoPorId(id);
        alumnoExistente.setNombre(alumno.getNombre());
        alumnoExistente.setApellido(alumno.getApellido());
        alumnoExistente.setEmail(alumno.getEmail());
        alumnoExistente.setGrupo(alumno.getGrupo());
        return repositorioAlumno.save(alumnoExistente);
    }

    @Override
    public void borrarAlumno(Long id) {
        Alumno alumno = obtenerAlumnoPorId(id);
        repositorioAlumno.delete(alumno);
    }

}