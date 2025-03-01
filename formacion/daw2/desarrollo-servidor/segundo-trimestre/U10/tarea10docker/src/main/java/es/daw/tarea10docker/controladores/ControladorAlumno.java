package es.daw.tarea10docker.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.daw.tarea10docker.modelos.Alumno;
import es.daw.tarea10docker.servicios.ServicioAlumno;
import es.daw.tarea10docker.servicios.ServicioGrupo;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con Alumnos.
 * Proporciona endpoints para realizar operaciones CRUD sobre la entidad Alumno.
 * 
 * La anotación @RestController indica que esta clase es un controlador REST
 * que maneja solicitudes HTTP y devuelve objetos que se serializan automáticamente a JSON.
 * 
 * La anotación @RequestMapping("/alumnos") establece la ruta base para todos los endpoints de este controlador.
 * 
 * @author Agustín (Agu1406)
 * @version 1.0
 */
@RestController
@RequestMapping("/alumnos")
public class ControladorAlumno {
    /**
     * Servicio de alumnos inyectado mediante inyección de dependencias.
     * La anotación @Autowired permite que Spring inyecte automáticamente una instancia
     * del servicio cuando se crea una instancia de este controlador.
     */
    @Autowired
    private ServicioAlumno servicioAlumno;

    @Autowired
    private ServicioGrupo servicioGrupo;

    /*
     * Endpoints disponibles:
     * GET /alumnos/listar - obtener todos los Alumnos
     * GET /alumnos/{id} - obtener un alumno por su id
     * POST /alumnos/crear - añadir un alumno y asociarlo a un grupo
     * PUT /alumnos/actualizar/{id} - actualizar los datos del alumno
     * DELETE /alumnos/borrar/{id} - borrar el alumno
     */

    // SECCIÓN "C" DE CRUD (CREAR).

    /**
     * Crea un nuevo alumno en el sistema.
     * 
     * Este endpoint maneja solicitudes POST a /alumnos/crear.
     * Recibe un objeto Alumno en formato JSON en el cuerpo de la solicitud.
     * 
     * @param nuevoAlumno Objeto Alumno con los datos del nuevo alumno
     * @return ResponseEntity con el alumno creado y la URI de su ubicación
     */
    @PostMapping("/crear")
    public ResponseEntity<?> crearAlumno(@RequestBody Alumno nuevoAlumno) {
        // Llama al servicio para agregar el alumno
        Alumno alumnoCreado = servicioAlumno.agregarAlumno(nuevoAlumno);
        
        // Construye la URI de la ubicación del nuevo recurso
        // ServletUriComponentsBuilder.fromCurrentRequest() obtiene la URI de la solicitud actual
        // .path("/{id}") añade un segmento de ruta con un marcador de posición para el ID
        // .buildAndExpand(alumnoCreado.getIdAlumno()) reemplaza el marcador con el ID real
        // .toUri() convierte el objeto UriComponents a URI
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(alumnoCreado.getIdAlumno())
            .toUri();
        
        // Devuelve una respuesta con código 201 (Created), la ubicación del recurso y el recurso creado
        return ResponseEntity.created(location).body(alumnoCreado);
    }

    // SECCIÓN "R" DE CRUD (LEER).
    /**
     * Obtiene todos los alumnos del sistema.
     * 
     * Este endpoint maneja solicitudes GET a /alumnos/listar.
     * 
     * @return ResponseEntity con la lista de todos los alumnos
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Alumno>> obtenerAlumnos() {
        // Obtiene todos los alumnos a través del servicio
        List<Alumno> alumnos = servicioAlumno.findAll();
        // Devuelve una respuesta con código 200 (OK) y la lista de alumnos
        return ResponseEntity.ok(alumnos);
    }

    /**
     * Obtiene un alumno específico por su ID.
     * 
     * Este endpoint maneja solicitudes GET a /alumnos/{id}.
     * 
     * @param id Identificador único del alumno a obtener
     * @return ResponseEntity con el alumno encontrado
     */
    // Obtener alumno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerAlumno(@PathVariable Long id) {
        // Obtiene el alumno por su ID a través del servicio
        Alumno alumno = servicioAlumno.obtenerAlumnoPorId(id);
        // Devuelve una respuesta con código 200 (OK) y el alumno encontrado
        return ResponseEntity.ok(alumno);
    }

    // SECCIÓN "U" DE CRUD (UPDATE).
    /**
     * Actualiza los datos de un alumno existente.
     * 
     * Este endpoint maneja solicitudes PUT a /alumnos/actualizar/{id}.
     * Recibe un objeto Alumno en formato JSON en el cuerpo de la solicitud.
     * 
     * @param id Identificador único del alumno a actualizar
     * @param alumno Objeto Alumno con los nuevos datos
     * @return ResponseEntity con el alumno actualizado
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        // Llama al servicio para actualizar el alumno
        Alumno alumnoActualizado = servicioAlumno.actualizarAlumno(id, alumno);
        // Devuelve una respuesta con código 200 (OK) y el alumno actualizado
        return ResponseEntity.ok(alumnoActualizado);
    }

    // SECCIÓN "D" DE CRUD (DELETE).
    /**
     * Elimina un alumno del sistema.
     * 
     * Este endpoint maneja solicitudes DELETE a /alumnos/borrar/{id}.
     * 
     * @param id Identificador único del alumno a eliminar
     * @return ResponseEntity sin contenido y código 204 (No Content)
     */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarAlumno(@PathVariable Long id) {
        // Llama al servicio para borrar el alumno
        servicioAlumno.borrarAlumno(id);
        // Devuelve una respuesta con código 204 (No Content) y sin cuerpo
        return ResponseEntity.noContent().build();
    }
}
