package presentation.Controllers;

import logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private Service service;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("puestos", service.getUltimosPuestosPublicos());
        return "index";
    }
}