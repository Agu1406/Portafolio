package es.daw2.tareas10.tarea10.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import es.daw2.tareas10.tarea10.modelos.Asignatura;

@Controller
public class ControladorAsignaturas {
    Asignatura asignatura = new Asignatura();

    @GetMapping("/abrir")
    public String abrirAsignatura(Model modelo) {
        modelo.addAttribute("asignatura", new Asignatura());
        return "formulario";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model modelo) {
        // Agregar atributos para mostrar en el informe
        modelo.addAttribute("atr_nombre", asignatura.getNombre());
        modelo.addAttribute("atr_ciclo", asignatura.getCiclo());
        modelo.addAttribute("atr_curso", asignatura.getCurso());
        modelo.addAttribute("atr_horas", asignatura.getHoras());
        modelo.addAttribute("atr_horasTrim", asignatura.getHorasTrimestre());

        return "formulario";
    }
}
