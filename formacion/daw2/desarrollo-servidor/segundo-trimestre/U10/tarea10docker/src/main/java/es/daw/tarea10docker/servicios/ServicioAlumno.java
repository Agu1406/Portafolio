package es.daw.tarea10docker.servicios;

import es.daw.tarea10docker.modelos.Alumno;
import es.daw.tarea10docker.repositorio.RepositorioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Servicio que implementa la lógica de negocio para la entidad Alumno.
 * Implementa la interfaz IFServicioAlumno y proporciona la funcionalidad
 * para realizar operaciones CRUD y otras operaciones específicas sobre alumnos.
 * 
 * La anotación @Service indica que esta clase es un componente de servicio de Spring
 * que puede ser inyectado en otras clases.
 * 
 * @author Agustín (Agu1406)
 * @version 1.0
 */
@Service
public class ServicioAlumno implements IFServicioAlumno {

    /*
     * Repositorio que provee las operaciones básicas de persistencia
     */
    /**
     * Repositorio de alumnos inyectado mediante inyección de dependencias.
     * La anotación @Autowired permite que Spring inyecte automáticamente una instancia
     * del repositorio cuando se crea una instancia de esta clase de servicio.
     */
    @Autowired
    RepositorioAlumno repositorioAlumno;

    /*
     * CRUD (READ) Obtiene todos los alumnos existentes
     * @return Lista de todos los alumnos
     */
    /**
     * Obtiene todos los alumnos almacenados en la base de datos.
     * 
     * @return Lista de todos los alumnos existentes
     */
    @Override
    public List<Alumno> findAll() {
        // Convierte el Iterable<Alumno> que devuelve findAll() a List<Alumno>
        return (List<Alumno>) repositorioAlumno.findAll();
    }

    /**
     * Busca un alumno por su identificador único.
     * Si no encuentra el alumno, lanza una excepción ResponseStatusException con estado NOT_FOUND.
     * 
     * @param id Identificador único del alumno a buscar
     * @return El alumno encontrado
     * @throws ResponseStatusException si el alumno no existe
     */
    @Override
    public Alumno obtenerAlumnoPorId(Long id) {
        // Busca el alumno por su ID y lanza una excepción si no lo encuentra
        return repositorioAlumno.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
    }

    /**
     * Guarda un nuevo alumno en la base de datos.
     * 
     * @param nuevoAlumno El alumno a guardar
     * @return El alumno guardado con su ID asignado
     */
    @Override
    public Alumno agregarAlumno(Alumno nuevoAlumno) {
        // Utiliza el método save() del repositorio para guardar el alumno
        return repositorioAlumno.save(nuevoAlumno);
    }

    /**
     * Actualiza los datos de un alumno existente.
     * Primero verifica que el alumno exista, luego actualiza sus campos
     * y finalmente guarda los cambios en la base de datos.
     * 
     * @param id Identificador único del alumno a actualizar
     * @param alumno Objeto con los nuevos datos del alumno
     * @return El alumno actualizado
     * @throws ResponseStatusException si el alumno no existe
     */
    @Override
    public Alumno actualizarAlumno(Long id, Alumno alumno) {
        // Obtiene el alumno existente o lanza una excepción si no existe
        Alumno alumnoExistente = obtenerAlumnoPorId(id);
        // Actualiza los campos del alumno existente con los valores del alumno recibido
        alumnoExistente.setNombre(alumno.getNombre());
        alumnoExistente.setApellido(alumno.getApellido());
        alumnoExistente.setEmail(alumno.getEmail());
        alumnoExistente.setGrupo(alumno.getGrupo());
        // Guarda los cambios en la base de datos
        return repositorioAlumno.save(alumnoExistente);
    }

    /**
     * Elimina un alumno de la base de datos.
     * Primero verifica que el alumno exista, luego lo elimina.
     * 
     * @param id Identificador único del alumno a eliminar
     * @throws ResponseStatusException si el alumno no existe
     */
    @Override
    public void borrarAlumno(Long id) {
        // Obtiene el alumno existente o lanza una excepción si no existe
        Alumno alumno = obtenerAlumnoPorId(id);
        // Elimina el alumno de la base de datos
        repositorioAlumno.delete(alumno);
    }

}