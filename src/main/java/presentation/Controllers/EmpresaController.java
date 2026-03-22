package presentation.Controllers;

import jakarta.servlet.http.HttpSession;
import logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Controller("empresaController")
public class EmpresaController {

    @Autowired
    private Service service;

    // ── DASHBOARD ─────────────────────────────────────────
    @GetMapping("/empresa/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!"empresa".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        model.addAttribute("nombre", session.getAttribute("usuarioNombre"));
        return "empresa/dashboard";
    }

    // ── MIS PUESTOS ───────────────────────────────────────
    @GetMapping("/empresa/puestos")
    public String misPuestos(HttpSession session, Model model) {
        if (!"empresa".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        String empresaId = (String) session.getAttribute("usuarioId");
        model.addAttribute("puestos", service.puestosPorEmpresa(empresaId));
        return "empresa/mis-puestos";
    }

    // ── PUBLICAR PUESTO ───────────────────────────────────
    @GetMapping("/empresa/puestos/nuevo")
    public String nuevoPuestoForm(HttpSession session, Model model) {
        if (!"empresa".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        model.addAttribute("caracteristicas", service.carecteristicasAll());
        return "empresa/nuevo-puesto";
    }

    @PostMapping("/empresa/puestos/nuevo")
    public String nuevoPuestoSubmit(
            HttpSession session,
            @RequestParam String descripcion,
            @RequestParam String salario,
            @RequestParam String tipo,
            @RequestParam(required = false) List<String> caracteristicaIds,
            @RequestParam(required = false) List<Integer> niveles) {

        if (!"empresa".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        String empresaId = (String) session.getAttribute("usuarioId");
        service.crearPuesto(empresaId, descripcion, salario, tipo, caracteristicaIds, niveles);
        return "redirect:/empresa/puestos";
    }

    // ── DESACTIVAR PUESTO ─────────────────────────────────
    @PostMapping("/empresa/puestos/desactivar/{id}")
    public String desactivarPuesto(@PathVariable String id, HttpSession session) {
        if (!"empresa".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        service.desactivarPuesto(id);
        return "redirect:/empresa/puestos";
    }

    // ── BUSCAR CANDIDATOS ─────────────────────────────────
    @GetMapping("/empresa/candidatos/buscar")
    public String buscarCandidatos(@RequestParam String puestoId, HttpSession session, Model model) {
        if (!"empresa".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        model.addAttribute("puesto", service.findPuestoById(puestoId));
        model.addAttribute("candidatos", service.buscarCandidatos(puestoId));
        return "empresa/candidatos";
    }

    // ── VER DETALLE CANDIDATO ─────────────────────────────
    @GetMapping("/empresa/candidatos/{id}")
    public String verCandidato(@PathVariable String id, HttpSession session, Model model) {
        if (!"empresa".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        model.addAttribute("oferente", service.findOferenteById(id));
        model.addAttribute("habilidades", service.habilidadesDeOferente(id));
        return "empresa/detalle-candidato";
    }
}
