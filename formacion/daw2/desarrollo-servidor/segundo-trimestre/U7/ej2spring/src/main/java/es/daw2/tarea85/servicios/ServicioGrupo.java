package es.daw2.tarea85.servicios;

import java.util.List;
import org.springframework.stereotype.Service;
import es.daw2.tarea85.modelos.Ciclo;

/**
 * Clase "ServicioGrupo" que permite obtener los ciclos formativos
 * de un IES concreto, se usan anotaciones de Spring para indicar
 * que es un servicio y que debe ser gestionado por el contenedor
 * de Spring.
 */
@Service
public class ServicioGrupo implements InterfazServicioGrupo {
    /**
     * Método que permite obtener los ciclos formativos de un IES
     * concreto, se simula la obtención de los datos de una base
     * de datos.
     * 
     * @param ies IES del que se quieren obtener los ciclos formativos.
     * @return Lista de ciclos formativos del IES.
     */
    public List<Ciclo> obtenerCiclosPorIes(String ies) {
        // Inicialmente "null" por si no se encuentra el IES.
        List<Ciclo> listaCiclosDelIes = null;
        // Se comprueba el IES y se asigna la lista de ciclos correspondiente.
        if (ies.equalsIgnoreCase("Ventura")) {
            listaCiclosDelIes = List.of(
                    new Ciclo("DAW", "Informática", 2),
                    new Ciclo("ASIR", "Informática", 2),
                    new Ciclo("SMR", "Informática", 2));
        }
        // Se comprueba el IES y se asigna la lista de ciclos correspondiente.
        if (ies.equalsIgnoreCase("Los Álamos")) {
            listaCiclosDelIes = List.of(
                    new Ciclo("DAW", "Informática", 2),
                    new Ciclo("DAM", "Informática", 2));
        }
        // Se comprueba el IES y se asigna la lista de ciclos correspondiente.
        if (ies.equalsIgnoreCase("Zayas")) {
            listaCiclosDelIes = List.of(
                    new Ciclo("SMR", "Informática", 2));
        }
        // Se devuelve la lista de ciclos del IES.
        return listaCiclosDelIes;
    }
}