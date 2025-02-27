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

    /**
     * Función que permite crear un grupo vacio (sin alumnos), recibiendo
     * como argumento crudo (raw) en el cuerpo de la solciitud (body) el
     * objeto nuevo a agregar (en forma de JSON).
     * 
     * @param nuevoGrupo objeto que se desea agregar en formato JSON.
     * @return el objeto creado indicando su ID en la misma dirección.
     */
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

    /**
     * Función que permita listar todos los intituso que pertenezcan a "X"
     * instituto, es una solicitud del tipo GET que no tiene cuerpo (none)
     * y recibe desde la propia dirección (URL) en forma de String el dato
     * "ies" para filtrar todos los institutos existentes.
     * 
     * @param ies String con el nombre del instituto a usar como filtro.
     * @return una lista de todos los objetos "Grupo" filtrados.
     */
    @GetMapping("/listarporinstitutos")
    public ResponseEntity<List<Grupo>> obtenerGruposPorIES(@RequestParam String ies) {
        List<Grupo> gruposFiltrados = servicioGrupo.obtenerGrupoId(ies);

        if (gruposFiltrados.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(gruposFiltrados);
        }
    }

    /**
     * Función que permite listar todos los grupos existente en la base de datos
     * no recibe ningún tipo de argumentos y devuelve un objeto del tipo "List"
     * tipeado a la clase "Grupo" usando el RepositorioGrupo para obtener todos
     * los grupos existentes.
     * 
     * @return Objeto "List" de la clase "Grupo" con todos los grupos existentes.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Grupo>> obtenerGrupos() {
        List<Grupo> grupos = servicioGrupo.findAll();
        return ResponseEntity.ok(grupos);
    }

    /**
     * Función que permite listar un grupo usando su ID como argumento
     * para ser encontrando en la base de datos.
     * @param id que es la llave primaria para identificar "X" grupo.
     * @return el objeto grupo en formato JSON en el cuerpo de la solicitud.
     */
    @GetMapping("/listarporid/{id}")
    public ResponseEntity<Grupo> obtenerGrupoPorId(@PathVariable Long id) {
        Grupo grupo = servicioGrupo.obtenerGrupoPorId(id);
        return ResponseEntity.ok(grupo);
    }

    // SECCIÓN "U" DE CRUD (ACTUALIZAR) //

    /**
     * Función que permite actualizar un grupo por completo sobrescribiendo
     * el original con uno nuevo recibido en el cuerpo (body) de la solicitud
     * como un dato crudo (raw) en forma de JSON, se usa el "id" recibido
     * como argumento para identificar que grupo se debe actualizar.
     * @param id el ID del grupo original que queremos sobrescribir.
     * @param grupo el nuevo grupo que remplaza al anterior.
     * @return el grupo actualizado en el cuerpo.
     */
    @PutMapping("/actualizargrupo/{id}")
    public ResponseEntity<?> actualizarGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
        Grupo grupoActualizado = servicioGrupo.actualizarGrupo(id, grupo);
        if (!(grupoActualizado.getIdGrupo().equals(id))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "¡Error! El grupo no existe.");
        }
        return ResponseEntity.ok(grupoActualizado);
    }

    // SECCIÓN "D" DE CRUD (BORRAR)

    /**
     * Función que permite borrar un grupo de la base de datos utilizando
     * el id del instituo para localizarlo y borrarlo de la base de datos.
     * @param id identificador unico de cada grupo.
     * @return un Response Entity sin contenido.
     */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarGrupo(@PathVariable Long id) {
        servicioGrupo.borrarGrupo(id);
        return ResponseEntity.noContent().build();
    }
}