package es.daw.tarea10docker.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.daw.tarea10docker.modelos.Alumno;
import es.daw.tarea10docker.servicios.ServicioAlumno;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con Alumnos
 * Proporciona endpoints para realizar operaciones CRUD
 */
@RestController
@RequestMapping("/alumnos")
public class ControladorAlumno {
    @Autowired
    private ServicioAlumno servicioAlumno;

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
     * 
     * @param nuevoAlumno
     * @return
     */
    @PostMapping("/crear")
    public ResponseEntity<?> crearAlumno(@RequestBody Alumno nuevoAlumno) {
        Alumno alumnoCreado = servicioAlumno.agregarAlumno(nuevoAlumno);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(alumnoCreado.getIdAlumno())
            .toUri();
        return ResponseEntity.created(location).body(alumnoCreado);
    }

    // SECCIÓN "R" DE CRUD (LEER).
    @GetMapping("/listar")
    public ResponseEntity<List<Alumno>> obtenerAlumnos() {
        List<Alumno> alumnos = servicioAlumno.findAll();
        return ResponseEntity.ok(alumnos);
    }

    // Obtener alumno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerAlumno(@PathVariable Long id) {
        Alumno alumno = servicioAlumno.obtenerAlumnoPorId(id);
        return ResponseEntity.ok(alumno);
    }

    // SECCIÓN "U" DE CRUD (UPDATE).
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        Alumno alumnoActualizado = servicioAlumno.actualizarAlumno(id, alumno);
        return ResponseEntity.ok(alumnoActualizado);
    }

    // SECCIÓN "D" DE CRUD (DELETE).
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarAlumno(@PathVariable Long id) {
        servicioAlumno.borrarAlumno(id);
        return ResponseEntity.noContent().build();
    }
}
