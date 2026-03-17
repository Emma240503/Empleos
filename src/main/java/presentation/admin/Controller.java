package presentation.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import logic.Service;

@org.springframework.stereotype.Controller("admin")
public class Controller {
    @Autowired
    private Service service;

    @GetMapping("/presentation/admin/show")
    public String show(Model model) {
        model.addAttribute("admin", service.adminsAll());
        return "presentation/admin/show";
    }
}
