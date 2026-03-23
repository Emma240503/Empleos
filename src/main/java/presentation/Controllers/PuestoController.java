package presentation.Controllers;

import logic.Puesto;
import logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller("puestoController")
public class PuestoController {

    @Autowired
    private Service service;

    @GetMapping("/puestos/buscar")
    public String buscar(@RequestParam(required = false) List<String> caracteristicaIds,
                         Model model) {
        List<Puesto> resultados = new ArrayList<>();
        if (caracteristicaIds != null && !caracteristicaIds.isEmpty()) {
            resultados = service.buscarPuestosPublicos(caracteristicaIds);
        }
        model.addAttribute("caracteristicas", service.carecteristicasAll());
        model.addAttribute("resultados", resultados);
        model.addAttribute("buscado", caracteristicaIds != null);
        return "puestos/buscar";
    }
}