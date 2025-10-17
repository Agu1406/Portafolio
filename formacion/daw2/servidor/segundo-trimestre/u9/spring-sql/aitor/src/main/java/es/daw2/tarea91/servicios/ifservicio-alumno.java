package es.daw2.tarea91.servicios;

import java.util.List;

import es.daw2.tarea91.modelos.Alumno;

public interface IFServicioAlumno {
    public abstract List<Alumno> listaAlumnos();
    public abstract Alumno agregarAlumno (Alumno nuevoAlumno);

}
