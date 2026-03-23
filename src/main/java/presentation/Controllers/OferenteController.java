package presentation.Controllers;

import jakarta.servlet.http.HttpSession;
import logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller("oferenteController")
public class OferenteController {

    @Autowired
    private Service service;

    @GetMapping("/oferente/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!"oferente".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        model.addAttribute("nombre", session.getAttribute("usuarioNombre"));
        return "oferente/dashboard";
    }

    @GetMapping("/oferente/habilidades")
    public String habilidades(HttpSession session, Model model) {
        if (!"oferente".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        String id = (String) session.getAttribute("usuarioId");
        model.addAttribute("habilidades", service.habilidadesDeOferente(id));
        model.addAttribute("caracteristicas", service.carecteristicasAll());
        return "oferente/habilidades";
    }

    @PostMapping("/oferente/habilidades/agregar")
    public String agregarHabilidad(
            HttpSession session,
            @RequestParam String caracteristicaId,
            @RequestParam int nivel) {
        if (!"oferente".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        String oferenteId = (String) session.getAttribute("usuarioId");
        service.agregarHabilidad(oferenteId, caracteristicaId, nivel);
        return "redirect:/oferente/habilidades";
    }

    @PostMapping("/oferente/habilidades/eliminar/{id}")
    public String eliminarHabilidad(@PathVariable String id, HttpSession session) {
        if (!"oferente".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        service.eliminarHabilidad(id);
        return "redirect:/oferente/habilidades";
    }
}