package es.daw2.tarea93.controladores;

import es.daw2.tarea93.modelos.Grupo;
import es.daw2.tarea93.servicios.ServicioGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @Autowired
    private ServicioGrupo servicioGrupo;

    // SECCIÓN DE "C" DE CRUD //
    @PostMapping("/creargrupovacio")
    public ResponseEntity<?> crearGrupoVacio(@RequestBody Grupo nuevoGrupo) {
        Grupo grupoCreado = servicioGrupo.agregarGrupoVacio(nuevoGrupo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(grupoCreado.getIdGrupo())
                .toUri();
        return ResponseEntity.created(location).body(grupoCreado);
    }

    // SECCIÓN DE "R" DE CRUD //

    @GetMapping("/listarporinstitutos")
    public ResponseEntity<List<Grupo>> obtenerGruposPorIES(@RequestParam String ies) {
        List<Grupo> gruposFiltrados = servicioGrupo.obtenerGrupoId(ies);
        if (gruposFiltrados.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(gruposFiltrados);
        }
    }

    // SECCIÓN "U" DE CRUD (ACTUALIZAR) //

    @PutMapping("/actualizargrupo/{id}")
    public ResponseEntity<?> actualizarGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
        Grupo grupoActualizado = servicioGrupo.actualizarGrupo(id, grupo);
        if (!(grupoActualizado.equals(grupo))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "¡Error! El grupo no existe.");
        }
        return ResponseEntity.ok(grupoActualizado);
    }
}