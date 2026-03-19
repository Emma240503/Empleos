package presentation.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import logic.Service;


@org.springframework.stereotype.Controller("puesto")
public class PuestoController {
    @Autowired
    private Service service;

    @GetMapping("/presentation/puesto/show")
    public String show(Model model) {
        model.addAttribute("puesto", service.puestosAll());
        return "presentation/puesto/show";
    }
}
