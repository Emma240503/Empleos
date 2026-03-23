package presentation.Controllers;

import jakarta.servlet.http.HttpSession;
import logic.Caracteristica;
import logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller("caracteristicaController")
public class CaracteristicaController {

    @Autowired
    private Service service;

    @GetMapping("/admin/caracteristicas")
    public String caracteristicas(@RequestParam(required = false) String actualId,
                                  HttpSession session, Model model) {
        if (!"admin".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        Caracteristica actual = null;
        Iterable<Caracteristica> subCategorias;
        List<Caracteristica> ruta = new ArrayList<>();
        if (actualId != null && !actualId.isEmpty()) {
            actual = service.findCaracteristicaById(actualId);
            subCategorias = service.getHijos(actualId);
            Caracteristica temp = actual;
            while (temp != null) { ruta.add(0, temp); temp = temp.getPadre(); }
        } else {
            subCategorias = service.getRaices();
        }
        model.addAttribute("actual", actual);
        model.addAttribute("subCategorias", subCategorias);
        model.addAttribute("ruta", ruta);
        model.addAttribute("todasCaracteristicas", service.carecteristicasAll());
        model.addAttribute("actualId", actualId != null ? actualId : "");
        return "admin/caracteristicas";
    }

    @PostMapping("/admin/caracteristicas/crear")
    public String crear(@RequestParam String nombre,
                        @RequestParam(required = false) String padreId,
                        @RequestParam(required = false) String actualId,
                        HttpSession session) {
        if (!"admin".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        service.crearCaracteristica(nombre, padreId);
        if (actualId != null && !actualId.isEmpty())
            return "redirect:/admin/caracteristicas?actualId=" + actualId;
        return "redirect:/admin/caracteristicas";
    }
}