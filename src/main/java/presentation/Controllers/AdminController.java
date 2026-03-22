package presentation.Controllers;

import jakarta.servlet.http.HttpSession;
import logic.Empresa;
import logic.Oferente;
import logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("adminController")
public class AdminController {

    @Autowired
    private Service service;

    // ── DASHBOARD ─────────────────────────────────────────
    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!"admin".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        model.addAttribute("nombre", session.getAttribute("usuarioNombre"));
        return "admin/dashboard";
    }

    // ── EMPRESAS PENDIENTES ───────────────────────────────
    @GetMapping("/admin/empresas/pendientes")
    public String empresasPendientes(HttpSession session, Model model) {
        if (!"admin".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        model.addAttribute("empresas", service.empresasPendientes());
        return "admin/empresas-pendientes";
    }

    @PostMapping("/admin/empresas/aprobar/{id}")
    public String aprobarEmpresa(@PathVariable String id, HttpSession session) {
        if (!"admin".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        service.aprobarEmpresa(id);
        return "redirect:/admin/empresas/pendientes";
    }

    // ── OFERENTES PENDIENTES ──────────────────────────────
    @GetMapping("/admin/oferentes/pendientes")
    public String oferentesPendientes(HttpSession session, Model model) {
        if (!"admin".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        model.addAttribute("oferentes", service.oferentesPendientes());
        return "admin/oferentes-pendientes";
    }

    @PostMapping("/admin/oferentes/aprobar/{id}")
    public String aprobarOferente(@PathVariable String id, HttpSession session) {
        if (!"admin".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        service.aprobarOferente(id);
        return "redirect:/admin/oferentes/pendientes";
    }
}