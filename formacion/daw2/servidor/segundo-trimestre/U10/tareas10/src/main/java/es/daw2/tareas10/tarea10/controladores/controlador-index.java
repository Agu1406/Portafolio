package es.daw2.tareas10.tarea10.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControladorIndex {

    @GetMapping
    public String abrir () {
        
        return "index";
    }
}
