package es.daw.tarea10docker.servicios;

import es.daw.tarea10docker.modelos.Alumno;

import java.util.List;

/**
 * Interfaz que define las operaciones disponibles para la gestión de Alumnos.
 * Define el contrato que debe implementar ServicioAlumno.
 * 
 * Esta interfaz proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre la entidad Alumno, así como otras operaciones específicas.
 * 
 * @author Agustín (Agu1406)
 * @version 1.0
 */
public interface IFServicioAlumno {
    
    /**
     * Obtiene todos los alumnos almacenados en la base de datos.
     * 
     * @return Lista de todos los alumnos existentes
     */
    public abstract List<Alumno> findAll();

    /**
     * Busca un alumno por su identificador único.
     * 
     * @param id Identificador único del alumno a buscar
     * @return El alumno encontrado. Si no existe, debería lanzar una excepción
     */
    public abstract Alumno obtenerAlumnoPorId(Long id);

    /**
     * Guarda un nuevo alumno en la base de datos.
     * 
     * @param nuevoAlumno El alumno a guardar
     * @return El alumno guardado con su ID asignado
     */
    public abstract Alumno agregarAlumno(Alumno nuevoAlumno);

    /**
     * Actualiza los datos de un alumno existente.
     * 
     * @param id Identificador único del alumno a actualizar
     * @param alumno Objeto con los nuevos datos del alumno
     * @return El alumno actualizado
     */
    public abstract Alumno actualizarAlumno(Long id, Alumno alumno);

    /**
     * Elimina un alumno de la base de datos.
     * 
     * @param id Identificador único del alumno a eliminar
     */
    public abstract void borrarAlumno(Long id);

}
