package es.daw2.tarea93.servicios;

import es.daw2.tarea93.modelos.Alumno;

import java.util.List;

public interface IFServicioAlumno  {
    
    public abstract List<Alumno> findAll();

    public abstract Alumno agregarAlumno (Alumno nuevoAlumno);

}
