package es.daw2.tareas10.tarea10.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import es.daw2.tareas10.tarea10.modelos.Asignatura;

@Controller
public class ControladorAsignaturas {
    Asignatura nuevAsignatura = new Asignatura();

    @GetMapping("abrir")
    public String abrirAsignatura(Model modelo) {

        // Returna null hasta que defina correctamente el método.
        return null;
    }

    @GetMapping("agregar")
    public String agregarAsignatura(Model modelo) {

        // Returna null hasta que defina correctamente el método.
        return null;
    }

}
