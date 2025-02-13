package es.daw2.tarea85.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.daw2.tarea85.modelos.Ciclo;
import es.daw2.tarea85.modelos.Grupo;
import es.daw2.tarea85.servicios.ServicioGrupo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Controlador REST para gestionar los grupos de formación.
 * Este controlador permite realizar operaciones CRUD sobre
 * un conjunto de grupos almacenados en un ArrayList que
 * simula una "base de datos".
 */
@RestController
public class ControladorGrupos {
    /*
     * Como estámos trabajando sin una base de datos (por el momento)
     * para poder aprender, creamos un ArrayList de instancias de la
     * clase Grupo para poder cargar esos datos manualmente y
     * usarlos como "base de datos".
     */
    ArrayList<Grupo> grupos = new ArrayList<>(
            // El ArrayList es una "lista" de instancias de Grupo.
            List.of(
                    new Grupo("Ventura", "DAW", "1"),
                    new Grupo("Ventura", "DAW", "2"),
                    new Grupo("Ventura", "ASIR", "1"),
                    new Grupo("Ventura", "ASIR", "2"),
                    new Grupo("Ventura", "SMR", "1"),
                    new Grupo("Ventura", "SMR", "2"),
                    new Grupo("Los Álamos", "DAW", "1"),
                    new Grupo("Los Álamos", "DAW", "2"),
                    new Grupo("Zayas", "DAW", "1"),
                    new Grupo("Zayas", "DAW", "2")));

    // SECCIÓN DE "C" DE CRUD //

    /**
     * Función de tipo POST que recibe un JSON con los datos de un nuevo grupo
     * y lo añade al ArrayList de grupos existentes.
     * @param nuevoGrupo JSON con los datos del nuevo grupo.
     * @return un ResponseEntity con el nuevo grupo y la URL del recurso creado.
     */
    @PostMapping ("grupos/creargrupot85")
    public ResponseEntity<?> crearGrupo(@RequestBody Grupo nuevoGrupo) {
        // Agrega el nuevo grupo al ArrayList de grupos existentes.
        grupos.add(nuevoGrupo);
        // Crea un nuevo objeto de la clase URI
        URI location = ServletUriComponentsBuilder
                // Toma la URL actual como base, en mi caso es http://localhost:808/grupos/crear
                .fromCurrentRequest()
                // Agrega a la URL anterior un "/{ies}" siendo esto una variable remplazable.
                .path("/{ies}")
                // Toma el IES del nuevo grupo creado y remplaza "{ies}" con ese valor.
                .buildAndExpand(nuevoGrupo.getIes())
                // Lo toma todo y lo convierte en URI.
                .toUri();

        // Devuelve en el cuerpo de la respuesta el nuevo grupo y la URL del recurso
        // creado.
        return ResponseEntity.created(location).body(nuevoGrupo);
    }

    // SECCIÓN DE "R" DE CRUD 

    /**
     * Función de tipo GET que recibe como argumento un instituto y
     * busca todos los cursos/ciclos de ese institutos y los guarda
     * en un array nuevo para poder listar solo los ciclos/cursos
     * de ese instituto.
     *
     * @param ies String con el nombre del instituto que se busca.
     * @return un RespondeEntity con el array de los ciclos/cursos
     */
    @GetMapping("/grupos/listarporinstitutost85")
    public ResponseEntity<ArrayList<Grupo>> obtenerGruposPorIES(@RequestParam String ies) {
        ArrayList<Grupo> gruposFiltrados = new ArrayList<>();
        /*
         * Recorre uno por uno todos los grupos dentro de la "base de datos"
         * de institutos creada al inicio del código en forma de Arraylist.
         */
        for (Grupo grupo : grupos) {
            // Si el IES del grupo es el que buscamos, lo añadimos al array filtrado.
            if (grupo.getIes().equalsIgnoreCase(ies)) {
                gruposFiltrados.add(grupo);
            }
        }
        /*
         * Si no existen grupos que pertenecen a ese instituto el
         * Arraylist estara vacio, si eso sucede devolvera de
         * return un badrequest indicando que no está bien, en
         * cambio si tiene al menos un grupo devolvera un OK
         * que es código HTTP 200 con el Arraylist lleno de
         * los grupos de ese instituto.
         */
        if (gruposFiltrados.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(gruposFiltrados);
        }
    }

    // SECCIÓN "U" DE CRUD (ACTUALIZAR)

    /**
     * Función "U" de CRUD que permite actualizar la posición que ocupa un
     * grupo en el ArrayList que almacena todos los grupos existentes.
     * 
     * @param nuevaposicion recibida como número entero desde la URL.
     * @param grupo         recibido como JSON o código desde el BODY.
     * @return una respuesta OK si todo sale bien, si no, excepciones.
     */
    @PutMapping("grupos/actualizargrupot85/{nuevaposicion}")
    public ResponseEntity<?> actualizarGrupo(@PathVariable int nuevaposicion, @RequestBody Grupo grupo) {
            if (nuevaposicion > grupos.size() || nuevaposicion < 0) {
                // Si la nueva posición es mayor que el tamaño del ArrayList o menor que 0, lanza un error.
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "¡Error! El nuevo indice no es posible.");
            }
            // Si no, intenta actualizar la posición de "X" grupo dentro del ArrayList de grupos.
            grupos.set(nuevaposicion, grupo);
            // Devuelve una respuesta positiva (OK) si todo sale bien.
            return ResponseEntity.ok(grupo);
    }

    // SECCIÓN "D" DEL CRUD (BORRAR) - Por hacer.
    @PostMapping("grupos/borrargrupost85/{ies}")
    public ResponseEntity borrarGrupo (@RequestBody String instituto) {
        
        return null;
    }
    

    @Autowired
    private ServicioGrupo serviciogrupo;

    @GetMapping("/{ies}/ciclost85")
    public ResponseEntity<?> recuperarCiclosPorIes(@PathVariable String ies) {
        List<Ciclo> listaCiclosPorIes = serviciogrupo.obtenerCiclosPorIes(ies);
        return ResponseEntity.ok(listaCiclosPorIes);
    }
}