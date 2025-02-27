package es.daw.tarea10docker.servicios;

import es.daw.tarea10docker.modelos.Alumno;

import java.util.List;

public interface IFServicioAlumno {
    
    public abstract List<Alumno> findAll();

    public abstract Alumno obtenerAlumnoPorId(Long id);

    public abstract Alumno agregarAlumno(Alumno nuevoAlumno);

    public abstract Alumno actualizarAlumno(Long id, Alumno alumno);

    public abstract void borrarAlumno(Long id);

}
