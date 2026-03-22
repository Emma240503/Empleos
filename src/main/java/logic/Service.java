package logic;

import data.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service

public class Service {
    @Autowired
    private EmpresasRepository empresas;
    @Autowired
    private AdminRepository admins;
    @Autowired
    private PuestoRepository puestos;
    @Autowired
    private CaracteristicaRepository carecteristica;
    @Autowired
    private OferenteRepository oferentes;
    public Iterable<Empresa> empresasAll() {
        return empresas.findAll();
    }
    public Iterable<Admin> adminsAll() { return admins.findAll();}
    public Iterable<Puesto> puestosAll() { return puestos.findAll();}
    public Iterable<Caracteristica> carecteristicasAll() { return carecteristica.findAll();}
    public Iterable<Oferente> oferentesAll() { return oferentes.findAll();}

    public void empresasAdd(Empresa empresa) {
        if(empresas.existsById(empresa.getId())) {
            throw new IllegalArgumentException("La empresa ya existe");
        }
        empresas.save(empresa);
    }
    public Empresa findEmpresaByCorreo(String correo) {
        return empresas.findByCorreo(correo);
    }
    public void adminsAdd(Admin admin) {
        if(admins.existsById(admin.getId())) {
            throw new IllegalArgumentException("El admin ya existe");
        }
        admins.save(admin);
    }
    public Admin findAdminById(String id) {
        return admins.findById(id).orElse(null);
    }
    public void puestosAdd(Puesto puesto) {
        if(puestos.existsById(puesto.getId())) {
            throw new IllegalArgumentException("El puesto ya existe");
        }
        puestos.save(puesto);
    }
    public void carecteristicasAdd(Caracteristica caracteristica) {
        if(carecteristica.existsById(caracteristica.getCaracteristicaId())) {
            throw new IllegalArgumentException("La caracteristica ya existe");
        }
        carecteristica.save(caracteristica);
    }
    public void oferentesAdd(Oferente oferente) {
        if(oferentes.existsById(oferente.getId())) {
            throw new IllegalArgumentException("El oferente ya existe");
        }
        oferentes.save(oferente);
    }
    public Oferente findOferenteByCorreo(String correo) {
        return oferentes.findByCorreo(correo);
    }

    public List<Puesto> getUltimosPuestosPublicos() {
        return puestos.findTop5ByTipoAndActivoOrderByFechaRegistroDesc("publico", (byte) 1);
    }
    //

    //

    public Iterable<Empresa> empresasPendientes() {
        return empresas.findByEstado("pendiente");
    }
    public void aprobarEmpresa(String id) {
        Empresa e = empresas.findById(id).orElseThrow();
        e.setEstado("aprobada");
        empresas.save(e);
    }
    public Iterable<Oferente> oferentesPendientes() {
        return oferentes.findByEstado("pendiente");
    }
    public void aprobarOferente(String id) {
        Oferente o = oferentes.findById(id).orElseThrow();
        o.setEstado("aprobado");
        oferentes.save(o);
    }
}