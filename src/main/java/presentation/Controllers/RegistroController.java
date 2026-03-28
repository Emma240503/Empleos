package presentation.Controllers;

import logic.Empresa;
import logic.Oferente;
import logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    @Autowired
    private Service service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ── EMPRESA ──────────────────────────────────────────
    @GetMapping("/registro/empresa")
    public String registroEmpresaForm() {
        return "registro/empresa";
    }

    @PostMapping("/registro/empresa")
    public String registroEmpresaSubmit(
            @RequestParam String nombre,
            @RequestParam String ubicacion,
            @RequestParam String correo,
            @RequestParam String telefono,
            @RequestParam String descripcion,
            @RequestParam String contrasenna) {

        if (service.findEmpresaByCorreo(correo) != null) {
            return "redirect:/registro/empresa?duplicado";
        }

        Empresa e = new Empresa();
        e.setId(java.util.UUID.randomUUID().toString());
        e.setNombre(nombre);
        e.setUbicacion(ubicacion);
        e.setCorreo(correo);
        e.setTelefono(Integer.parseInt(telefono));
        e.setDescripcion(descripcion);
        e.setContrasenna(passwordEncoder.encode(contrasenna));
        e.setEstado("pendiente");
        service.empresasAdd(e);

        return "redirect:/registro/empresa?exito";
    }

    // ── OFERENTE ─────────────────────────────────────────
    @GetMapping("/registro/oferente")
    public String registroOferenteForm() {
        return "registro/oferente";
    }

    @PostMapping("/registro/oferente")
    public String registroOferenteSubmit(
            @RequestParam String id,
            @RequestParam String nombre,
            @RequestParam String primerApellido,
            @RequestParam String nacionalidad,
            @RequestParam String telefono,
            @RequestParam String correo,
            @RequestParam String ubicacion,
            @RequestParam String contrasenna) {

        if (service.findOferenteByCorreo(correo) != null) {
            return "redirect:/registro/oferente?duplicado";
        }

        Oferente o = new Oferente();
        o.setId(id);
        o.setNombre(nombre);
        o.setPrimerApellido(primerApellido);
        o.setNacionalidad(nacionalidad);
        o.setTelefono(Integer.parseInt(telefono));
        o.setCorreo(correo);
        o.setUbicacion(ubicacion);
        o.setContrasenna(passwordEncoder.encode(contrasenna));
        o.setEstado("pendiente");
        service.oferentesAdd(o);

        return "redirect:/registro/oferente?exito";
    }
}