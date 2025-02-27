package es.daw.tarea10docker.servicios;

import es.daw.tarea10docker.modelos.Grupo;
import es.daw.tarea10docker.repositorio.RepositorioGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que implementa la lógica de negocio para la entidad Grupo.
 * Implementa la interfaz IFServicioGrupo y proporciona la funcionalidad
 * para realizar operaciones CRUD y otras operaciones específicas sobre grupos.
 * 
 * La anotación @Service indica que esta clase es un componente de servicio de Spring
 * que puede ser inyectado en otras clases.
 * 
 * @author Agustín (Agu1406)
 * @version 1.0
 */
@Service
public class ServicioGrupo implements IFServicioGrupo {

    /**
     * Repositorio de grupos inyectado mediante inyección de dependencias.
     * La anotación @Autowired permite que Spring inyecte automáticamente una instancia
     * del repositorio cuando se crea una instancia de esta clase de servicio.
     */
    @Autowired
    private RepositorioGrupo repositorioGrupo;

    /**
     * Crea un nuevo grupo sin alumnos asociados.
     * Establece explícitamente la lista de alumnos como null para asegurar
     * que no se asocien alumnos al crear el grupo.
     * 
     * @param nuevoGrupo Objeto Grupo con los datos del nuevo grupo
     * @return El grupo creado con su ID asignado
     */
    @Override
    public Grupo agregarGrupoVacio(Grupo nuevoGrupo) {
        // Asegurarse que no hay alumnos asociados
        nuevoGrupo.setAlumnos(null);
        // Guarda el nuevo grupo en la base de datos
        return repositorioGrupo.save(nuevoGrupo);
    }

    /**
     * Crea un nuevo grupo con alumnos asociados.
     * Este método no está implementado y lanza una excepción.
     * 
     * @param nuevoGrupo Objeto Grupo con los datos del nuevo grupo y su lista de alumnos
     * @return El grupo creado con su ID asignado
     * @throws UnsupportedOperationException siempre, ya que el método no está implementado
     */
    @Override
    public Grupo agregarGrupoLleno(Grupo nuevoGrupo) {
        // Verificar que el grupo tiene alumnos
        if (nuevoGrupo.getAlumnos() == null || nuevoGrupo.getAlumnos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "El grupo debe tener alumnos asociados");
        }
        return repositorioGrupo.save(nuevoGrupo);
    }

    /**
     * Obtiene todos los grupos almacenados en la base de datos.
     * 
     * @return Lista de todos los grupos existentes
     */
    @Override
    public List<Grupo> obtenerGrupos() {
        // Convierte el Iterable<Grupo> que devuelve findAll() a List<Grupo>
        return (List<Grupo>) repositorioGrupo.findAll();
    }

    /**
     * Filtra los grupos por el nombre del instituto (IES).
     * Recorre todos los grupos y selecciona aquellos cuyo IES coincide
     * con el parámetro proporcionado, sin distinguir entre mayúsculas y minúsculas.
     * 
     * @param ies Nombre del instituto a utilizar como filtro
     * @return Lista de grupos que pertenecen al instituto especificado
     */
    @Override
    public List<Grupo> obtenerGrupoId(String ies) {
        // Creamos una lista donde guardaremos los grupos filtrados.
        List<Grupo> gruposFiltrados = new ArrayList<>();
        // Generamos una lista con todos los grupos existentes.
        List<Grupo> grupos = (List<Grupo>) repositorioGrupo.findAll();
        // Recorremos todos los grupos uno por uno.
        for (Grupo grupo : grupos) {
            // Si el IES (sin distinguir mayúsculas) coincide.
            if (grupo.getIes().equalsIgnoreCase(ies)) {
                // Guardamos en la lista de filtrados ese "X" grupo.
                gruposFiltrados.add(grupo);
            }
        }
        // Devuelve al final una lista de grupos filtrados.
        return gruposFiltrados;
    }

    /**
     * Actualiza los datos de un grupo existente sin modificar sus alumnos asociados.
     * Primero verifica que el grupo exista, luego actualiza sus campos
     * y finalmente guarda los cambios en la base de datos.
     * 
     * @param idGrupo Identificador único del grupo a actualizar
     * @param nuevoGrupo Objeto con los nuevos datos del grupo
     * @return El grupo actualizado
     * @throws ResponseStatusException si el grupo no existe
     */
    @Override
    public Grupo actualizarGrupo(Long idGrupo, Grupo nuevoGrupo) {
        // Obtiene el grupo existente o lanza una excepción si no existe
        Grupo grupoExistente = obtenerGrupoPorId(idGrupo);
        // Actualiza los campos del grupo existente con los valores del grupo recibido
        grupoExistente.setIes(nuevoGrupo.getIes());
        grupoExistente.setCiclo(nuevoGrupo.getCiclo());
        grupoExistente.setCurso(nuevoGrupo.getCurso());
        // Guarda los cambios en la base de datos
        return repositorioGrupo.save(grupoExistente);
    }

    /**
     * Elimina un grupo de la base de datos.
     * Este método está incompleto y siempre devuelve null.
     * 
     * @param idGrupo Identificador único del grupo a eliminar
     * @return null (método incompleto)
     */
    @Override
    public Grupo borrarGrupo(Long idGrupo) {
        Grupo grupo = obtenerGrupoPorId(idGrupo);
        if (grupo.getAlumnos() != null && !grupo.getAlumnos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "No se puede eliminar un grupo que tiene alumnos asociados");
        }
        repositorioGrupo.deleteById(idGrupo);
        return grupo;
    }

    /**
     * Busca un grupo por su identificador único.
     * Si no encuentra el grupo, lanza una excepción ResponseStatusException con estado NOT_FOUND.
     * 
     * @param id Identificador único del grupo a buscar
     * @return El grupo encontrado
     * @throws ResponseStatusException si el grupo no existe
     */
    public Grupo obtenerGrupoPorId(Long id) {
        // Busca el grupo por su ID y lanza una excepción si no lo encuentra
        return repositorioGrupo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo no encontrado"));
    }

    /**
     * Obtiene todos los grupos almacenados en la base de datos.
     * Este método es una implementación adicional que no está definida en la interfaz.
     * 
     * @return Lista de todos los grupos existentes
     */
    public List<Grupo> findAll() {
        // Convierte el Iterable<Grupo> que devuelve findAll() a List<Grupo>
        return (List<Grupo>) repositorioGrupo.findAll();
    }
}