package sistema_gestion_agricola.modelo;

import sistema_gestion_agricola.excepciones.HistorialVacioException;
import sistema_gestion_agricola.interfaces.ActividadAgricola;
import java.util.ArrayList;
import java.util.List;

public class RegistroActividades<T extends ActividadAgricola> {
    private List<T> actividades;

    public RegistroActividades() {
        this.actividades = new ArrayList<>();
    }

    public void agregarActividad(T actividad) {
        actividades.add(actividad);
    }

    public void mostrarHistorial() throws HistorialVacioException {
        if (actividades.isEmpty()) {
            throw new HistorialVacioException();
        }
        for (T actividad : actividades) {
            System.out.println(actividad);
        }
    }
} 