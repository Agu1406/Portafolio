package es.daw2.tarea92.servicios;

import java.util.List;

import es.daw2.tarea92.modelos.Alumno;

public interface IFServicioAlumno  {
    
    public abstract List<Alumno> findAll();

    public abstract Alumno agregarAlumno (Alumno nuevoAlumno);

}
