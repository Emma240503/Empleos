package presentation.Controllers;

import jakarta.servlet.http.HttpSession;
import logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller("oferenteController")
public class OferenteController {

    @Autowired
    private Service service;

    @GetMapping("/oferente/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!"oferente".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        model.addAttribute("nombre", session.getAttribute("usuarioNombre"));
        model.addAttribute("tieneCV", session.getAttribute("curriculum")!= null);
        return "oferente/dashboard";
    }

    @GetMapping("/oferente/habilidades")
    public String habilidades(HttpSession session, Model model) {
        if (!"oferente".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        String id = (String) session.getAttribute("usuarioId");
        model.addAttribute("habilidades", service.habilidadesDeOferente(id));
        model.addAttribute("caracteristicas", service.carecteristicasAll());
        model.addAttribute("nombre", session.getAttribute("usuarioNombre"));
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

    @PostMapping("/oferente/subir-cv")
    public String subirCV(
            HttpSession session,
            @RequestParam("archivo") MultipartFile archivo) {
        if (!"oferente".equals(session.getAttribute("usuarioRol"))) return "redirect:/login";
        // Validar que sea PDF
        String contentType = archivo.getContentType();
        if (contentType == null || !contentType.equals("application/pdf")) {
            return "redirect:/oferente/dashboard?error=Solo se permiten archivos PDF";
        }
        try {
            String oferenteId = (String) session.getAttribute("usuarioId");
            String fileName = UUID.randomUUID() + ".pdf";

            String projectRoot = System.getProperty("user.dir");
            Path uploadDir = Paths.get(projectRoot, "src", "main", "resources", "static", "pdf");
            Files.createDirectories(uploadDir);
            Path filePath = uploadDir.resolve(fileName);
            Files.write(filePath, archivo.getBytes());
            
            // Actualizar la ruta en la base de datos
            service.actualizarCurriculumOferente(oferenteId, "/pdf/" + fileName);
            return "redirect:/oferente/dashboard?success=CV subido correctamente";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/oferente/dashboard?error=Error al subir el archivo";
        }
    }
}
