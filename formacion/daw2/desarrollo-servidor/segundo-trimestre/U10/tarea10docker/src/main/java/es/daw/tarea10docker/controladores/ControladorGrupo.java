package es.daw.tarea10docker.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.daw.tarea10docker.modelos.Grupo;
import es.daw.tarea10docker.servicios.ServicioGrupo;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con Grupos.
 * Proporciona endpoints para realizar operaciones CRUD sobre la entidad Grupo.
 * 
 * La anotación @RestController indica que esta clase es un controlador REST
 * que maneja solicitudes HTTP y devuelve objetos que se serializan automáticamente a JSON.
 * 
 * La anotación @RequestMapping("/grupos") establece la ruta base para todos los endpoints de este controlador.
 * 
 * @author Agustín (Agu1406)
 * @version 1.0
 */
@RestController
@RequestMapping("/grupos")
public class ControladorGrupo {
    /*
     * obtener todos los Grupos | obtenerGrupos()
     * obtener un grupo dado su id | obtenerGrupo()
     * añadir un grupo sin alumnos | crearGrupoVacio()
     * añadir un grupo con alumnos | crearGrupo()
     * actualizar los datos del grupo idGrupo. manteniendo su lista de alumnos
     * anterior | actualizarGrupo
     * borrar el grupo idGrupo, sólo si no tiene alumnos | borrarGrupo()
     */

    /**
     * Servicio de grupos inyectado mediante inyección de dependencias.
     * La anotación @Autowired permite que Spring inyecte automáticamente una instancia
     * del servicio cuando se crea una instancia de este controlador.
     */
    @Autowired
    private ServicioGrupo servicioGrupo;

    // SECCIÓN DE "C" DE CRUD //

    /**
     * Función que permite crear un grupo vacio (sin alumnos), recibiendo
     * como argumento crudo (raw) en el cuerpo de la solciitud (body) el
     * objeto nuevo a agregar (en forma de JSON).
     * 
     * Este endpoint maneja solicitudes POST a /grupos/creargrupovacio.
     * 
     * @param nuevoGrupo objeto que se desea agregar en formato JSON.
     * @return el objeto creado indicando su ID en la misma dirección.
     */
    @PostMapping("/creargrupovacio")
    public ResponseEntity<?> crearGrupoVacio(@RequestBody Grupo nuevoGrupo) {
        // Llama al servicio para crear un grupo vacío
        Grupo grupoCreado = servicioGrupo.agregarGrupoVacio(nuevoGrupo);

        // Construye la URI de la ubicación del nuevo recurso
        // ServletUriComponentsBuilder.fromCurrentRequest() obtiene la URI de la solicitud actual
        // .path("/{id}") añade un segmento de ruta con un marcador de posición para el ID
        // .buildAndExpand(grupoCreado.getIdGrupo()) reemplaza el marcador con el ID real
        // .toUri() convierte el objeto UriComponents a URI
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(grupoCreado.getIdGrupo())
                .toUri();
        
        // Devuelve una respuesta con código 201 (Created), la ubicación del recurso y el recurso creado
        return ResponseEntity.created(location).body(grupoCreado);
    }

    // SECCIÓN DE "R" DE CRUD //

    /**
     * Función que permita listar todos los intituso que pertenezcan a "X"
     * instituto, es una solicitud del tipo GET que no tiene cuerpo (none)
     * y recibe desde la propia dirección (URL) en forma de String el dato
     * "ies" para filtrar todos los institutos existentes.
     * 
     * Este endpoint maneja solicitudes GET a /grupos/listarporinstitutos con un parámetro de consulta "ies".
     * 
     * @param ies String con el nombre del instituto a usar como filtro.
     * @return una lista de todos los objetos "Grupo" filtrados.
     */
    @GetMapping("/listarporinstitutos")
    public ResponseEntity<List<Grupo>> obtenerGruposPorIES(@RequestParam String ies) {
        // Obtiene los grupos filtrados por IES a través del servicio
        List<Grupo> gruposFiltrados = servicioGrupo.obtenerGrupoId(ies);

        // Si no hay grupos que coincidan con el filtro, devuelve una respuesta con código 400 (Bad Request)
        if (gruposFiltrados.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            // Si hay grupos, devuelve una respuesta con código 200 (OK) y la lista de grupos
            return ResponseEntity.ok(gruposFiltrados);
        }
    }

    /**
     * Función que permite listar todos los grupos existente en la base de datos
     * no recibe ningún tipo de argumentos y devuelve un objeto del tipo "List"
     * tipeado a la clase "Grupo" usando el RepositorioGrupo para obtener todos
     * los grupos existentes.
     * 
     * Este endpoint maneja solicitudes GET a /grupos/listar.
     * 
     * @return Objeto "List" de la clase "Grupo" con todos los grupos existentes.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Grupo>> obtenerGrupos() {
        // Obtiene todos los grupos a través del servicio
        List<Grupo> grupos = servicioGrupo.findAll();
        // Devuelve una respuesta con código 200 (OK) y la lista de grupos
        return ResponseEntity.ok(grupos);
    }

    /**
     * Función que permite listar un grupo usando su ID como argumento
     * para ser encontrando en la base de datos.
     * 
     * Este endpoint maneja solicitudes GET a /grupos/listarporid/{id}.
     * 
     * @param id que es la llave primaria para identificar "X" grupo.
     * @return el objeto grupo en formato JSON en el cuerpo de la solicitud.
     */
    @GetMapping("/listarporid/{id}")
    public ResponseEntity<Grupo> obtenerGrupoPorId(@PathVariable Long id) {
        // Obtiene el grupo por su ID a través del servicio
        Grupo grupo = servicioGrupo.obtenerGrupoPorId(id);
        // Devuelve una respuesta con código 200 (OK) y el grupo encontrado
        return ResponseEntity.ok(grupo);
    }

    // SECCIÓN "U" DE CRUD (ACTUALIZAR) //

    /**
     * Función que permite actualizar un grupo por completo sobrescribiendo
     * el original con uno nuevo recibido en el cuerpo (body) de la solicitud
     * como un dato crudo (raw) en forma de JSON, se usa el "id" recibido
     * como argumento para identificar que grupo se debe actualizar.
     * 
     * Este endpoint maneja solicitudes PUT a /grupos/actualizargrupo/{id}.
     * 
     * @param id el ID del grupo original que queremos sobrescribir.
     * @param grupo el nuevo grupo que remplaza al anterior.
     * @return el grupo actualizado en el cuerpo.
     * @throws ResponseStatusException si el grupo no existe o el ID no coincide
     */
    @PutMapping("/actualizargrupo/{id}")
    public ResponseEntity<?> actualizarGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
        // Llama al servicio para actualizar el grupo
        Grupo grupoActualizado = servicioGrupo.actualizarGrupo(id, grupo);
        
        // Verifica que el ID del grupo actualizado coincida con el ID proporcionado
        if (!(grupoActualizado.getIdGrupo().equals(id))) {
            // Si no coincide, lanza una excepción con código 400 (Bad Request)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "¡Error! El grupo no existe.");
        }
        
        // Si todo está bien, devuelve una respuesta con código 200 (OK) y el grupo actualizado
        return ResponseEntity.ok(grupoActualizado);
    }

    // SECCIÓN "D" DE CRUD (BORRAR)

    /**
     * Función que permite borrar un grupo de la base de datos utilizando
     * el id del instituo para localizarlo y borrarlo de la base de datos.
     * 
     * Este endpoint maneja solicitudes DELETE a /grupos/borrar/{id}.
     * 
     * @param id identificador unico de cada grupo.
     * @return un Response Entity sin contenido.
     */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarGrupo(@PathVariable Long id) {
        // Llama al servicio para borrar el grupo
        servicioGrupo.borrarGrupo(id);
        // Devuelve una respuesta con código 204 (No Content) y sin cuerpo
        return ResponseEntity.noContent().build();
    }
}