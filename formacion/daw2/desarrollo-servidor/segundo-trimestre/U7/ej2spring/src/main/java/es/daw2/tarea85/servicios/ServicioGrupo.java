package es.daw2.tarea85.servicios;

import java.util.List;
import org.springframework.stereotype.Service;
import es.daw2.tarea85.modelos.Ciclo;

@Service
public class ServicioGrupo {

    public List<Ciclo> obtenerCiclosPorIes(String ies) {
        List<Ciclo> listaCiclosDelIes = null; // Por si se pide un ies que no existe

        if (ies.equalsIgnoreCase("Ventura")) {
            listaCiclosDelIes = List.of(
                    new Ciclo("DAW", "Informática", 2),
                    new Ciclo("ASIR", "Informática", 2),
                    new Ciclo("SMR", "Informática", 2));
        }
        if (ies.equalsIgnoreCase("Los Álamos")) {
            listaCiclosDelIes = List.of(
                    new Ciclo("DAW", "Informática", 2),
                    new Ciclo("DAM", "Informática", 2));
        }
        if (ies.equalsIgnoreCase("Zayas")) {
            listaCiclosDelIes = List.of(
                    new Ciclo("SMR", "Informática", 2));
        }
        return listaCiclosDelIes;
    }
}