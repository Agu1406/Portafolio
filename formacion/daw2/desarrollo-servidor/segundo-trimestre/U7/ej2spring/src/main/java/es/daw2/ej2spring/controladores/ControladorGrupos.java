package es.daw2.ej2spring.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.daw2.ej2spring.modelos.Grupo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
     * Crea un nuevo grupo y lo agrega a la lista de grupos.
     * 
     * @param nuevoGrupo Objeto Grupo recibido desde la URL.
     * @return Lista actualizada de grupos.
     */
    @PostMapping("/grupos/crear")
    public ArrayList<Grupo> crearGrupoArrayList(@RequestBody Grupo nuevoGrupo) {
        // Ya recibido el "grupo" desde la URL, lo añadimos al ArrayList
        grupos.add(nuevoGrupo);
        // Devolvemos la lista de grupos actualizada.
        return grupos;
    }

    /**
     * Crea y lista los grupos de un instituto (IES) específico.
     * 
     * @param ies Nombre del instituto recibido desde la URL.
     * @return Lista de grupos pertenecientes al instituto.
     */
    @GetMapping("/grupos/crear/{ies}")
    public ArrayList<Grupo> gruposDeUnIes(@PathVariable String ies) {
        // Crea un nuevo ArrayList de grupos llamado "nuevoGrupo"
        ArrayList<Grupo> nuevogrupo = new ArrayList<Grupo>();
        for (Grupo grupo : grupos) {
            if (grupo.getIes().equalsIgnoreCase(ies)) {
                nuevogrupo.add(grupo);
            }
        }
        return nuevogrupo;
    }

    /**
     * Función "C" de CRUD para crear nuevos grupos usando ResponseEntity
     * la función crea un objeto de la clase URI que permite, una vez
     * creado un recurso, devolver la localización (URL) del mismo, así
     * devolviendo la respuesta HTTP también.
     *
     * @param nuevoGrupo recibido como JSON desde la consulta.
     * @return respueta HTTP con el grupo recien creado.
     */
    @PostMapping("grupos/crear");
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

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
                // Lo toma tood y lo convierte en URI.
                .toUri();

        // Devuelve en el cuerpo de la respuesta el nuevo grupo y la URL del recurso
        // creado.
        return ResponseEntity.created(location).body(nuevoGrupo);
    }

    // SECCIÓN DE "R" DE CRUD

    /**
     * Método READ (CRUD) que permita listar todos los grupos existentes
     * en la lista de grupos creados.
     *
     * @return la lista de grupos actualizada.
     */
    @GetMapping("/grupos/listar")
    public ArrayList<Grupo> listarGrupos() {
        // Devuelve la lista de grupos en su versión más reciente.
        return grupos;
    }

    /**
     * Función de tipo GET que recibe como argumento un instituto y
     * busca todos los cursos/ciclos de ese institutos y los guarda
     * en un array nuevo para poder listar solo los ciclos/cursos
     * de ese instituto.
     *
     * @param ies String con el nombre del instituto que se busca.
     * @return un RespondeEntity con el array de los ciclos/cursos
     */
    @GetMapping("/grupos/listarporinstitutos")
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
    @PutMapping("grupos/actualizargrupo/{nuevaposicion}")
    public ResponseEntity<?> actualizarGrupo(@PathVariable int nuevaposicion, @RequestBody Grupo grupo) {
        // Try-catch que intenta actualizar la posición de un grupo dentro del ArrayList
        // de grupos.
        try {
            // Si la nueva posición es mayor al tamaño del array o menor a "0" arroja una
            // excepción.
            if (nuevaposicion > grupos.size() || nuevaposicion < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "¡Error! La nueva posición no es posible.");
            }
            // Si no, intenta actualizar la posición de "X" grupo dentro del ArrayList de
            // grupos.
            grupos.set(nuevaposicion, grupo);
            // Devuelve una respuesta positiva (OK) si todo sale bien.
            return ResponseEntity.ok(grupo);

        } catch (IndexOutOfBoundsException exception) {
            // Si el ArrayList se sale de los limites permitido atrapa la excepción y la
            // envia con un mensaje.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "¡Error!");
        }
    }

    /**
     * Actualiza el instituto (IES) de un grupo existente.
     * 
     * @param ies Nuevo nombre del instituto recibido desde la URL.
     * @return Lista actualizada de grupos.
     */
    @PutMapping("/grupos/update/")
    public ArrayList<Grupo> actualizarGrupoArrayList(@PathVariable String ies) {
        return grupos;
    }

    // SECCIÓN "D" DEL CRUD (BORRAR)

    /**
     * Elimina un grupo con base en el instituto (IES) proporcionado.
     * 
     * @param ies Nombre del instituto recibido desde la URL.
     * @return Lista actualizada de grupos.
     */
    @DeleteMapping("/grupos/borrar/")
    public ArrayList<Grupo> borrarGrupo(@PathVariable String ies) {
        // Recorre con un foreach todos los grupos del Arraylist.
        for (Grupo grupo : grupos) {
            // Si consigue cualquier grupo con el mismo "ies" ejecuta el IF.
            if (grupo.getIes().equalsIgnoreCase(ies)) {
                // Remueve del ArrayList el grupo cuyo "ies" concuerde.
                grupos.remove(grupo);
            }
        }
        // Devuelve la lista actualizada de grupos.
        return grupos;
    }

}
