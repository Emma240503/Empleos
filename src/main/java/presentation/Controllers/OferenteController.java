package presentation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import logic.Service;


@org.springframework.stereotype.Controller("oferente")
public class OferenteController {
    @Autowired
    private Service service;

    @GetMapping("/presentation/oferente/show")
    public String show(Model model) {
        model.addAttribute("empreoferentesa", service.empresasAll());
        return "presentation/oferente/show";
    }
}
