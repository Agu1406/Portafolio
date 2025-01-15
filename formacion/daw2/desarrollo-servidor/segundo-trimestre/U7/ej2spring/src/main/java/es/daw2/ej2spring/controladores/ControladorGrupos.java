package es.daw2.ej2spring.controladores;

import org.springframework.web.bind.annotation.*;
import es.daw2.ej2spring.modelos.Grupo;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador REST para gestionar los grupos de formación.
 * Este controlador permite realizar operaciones CRUD sobre
 * un conjunto de grupos almacenados en un ArrayList que
 * simula una "base de datos".
 *
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

    /**
     * Crea un nuevo grupo y lo agrega a la lista de grupos.
     * @param nuevoGrupo Objeto Grupo recibido desde la URL.
     * @return Lista actualizada de grupos.
     */
    @PostMapping("/grupos/crear")
    public ArrayList<Grupo> crearGrupo(@RequestBody Grupo nuevoGrupo) {
        // Ya recibido el "grupo" desde la URL, lo añadimos al ArrayList
        grupos.add(nuevoGrupo);
        // Devolvemos la lista de grupos actualizada.
        return grupos;
    }

    /**
     * Crea y lista los grupos de un instituto (IES) específico.
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
     * Actualiza el instituto (IES) de un grupo existente.
     * @param ies Nuevo nombre del instituto recibido desde la URL.
     * @return Lista actualizada de grupos.
     */
    @PutMapping("/grupos/update/")
    public ArrayList<Grupo> actualizarGrupo(@PathVariable String ies) {
        return grupos;
    }

    /**
     * Elimina un grupo con base en el instituto (IES) proporcionado.
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
