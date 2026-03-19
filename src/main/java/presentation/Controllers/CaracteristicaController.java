package presentation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import logic.Service;

@org.springframework.stereotype.Controller("carecteristica")
public class CaracteristicaController {
    @Autowired
    private Service service;

    @GetMapping("/presentation/carecteristica/show")
    public String show(Model model) {
        model.addAttribute("carecteristica", service.carecteristicasAll());
        return "presentation/carecteristica/show";
    }
}
