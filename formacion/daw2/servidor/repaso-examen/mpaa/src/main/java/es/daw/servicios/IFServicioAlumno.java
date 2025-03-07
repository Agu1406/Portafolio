package es.daw.servicios;

import java.util.List;

import es.daw.modelos.Alumno;

public interface IFServicioAlumno {
    public abstract List<Alumno> findAll();

    public abstract Alumno obtenerAlumnoPorId(Long id);

    public abstract Alumno agregarAlumno(Alumno nuevoAlumno);

    public abstract Alumno actualizarAlumno(Long id, Alumno alumno);

    public abstract void borrarAlumno(Long id);
}