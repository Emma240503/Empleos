package logic;

import data.AdminRepository;
import data.CaracteristicaRepository;
import data.PuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import data.EmpresasRepository;

@org.springframework.stereotype.Service

public class Service {
    @Autowired
    private EmpresasRepository empresas;
    private AdminRepository admins;
    private PuestoRepository puestos;
    private CaracteristicaRepository carecteristica;

    public Iterable<Empresa> empresasAll() {
        return empresas.findAll();
    }
    public Iterable<Admin> adminsAll() { return admins.findAll();}
    public Iterable<Puesto> puestosAll() { return puestos.findAll();}
    public Iterable<Caracteristica> carecteristicasAll() { return carecteristica.findAll();}

    public void empresasAdd(Empresa empresa) {
        if(empresas.existsById(empresa.getId())) {
            throw new IllegalArgumentException("La empresa ya existe");
        }
        empresas.save(empresa);
    }
    public void adminsAdd(Admin admin) {
        if(admins.existsById(admin.getId())) {
            throw new IllegalArgumentException("El admin ya existe");
        }
        admins.save(admin);
    }
    public void puestosAdd(Puesto puesto) {
        if(puestos.existsById(puesto.getEmpresa_id())) {
            throw new IllegalArgumentException("El puesto ya existe");
        }
        puestos.save(puesto);
    }
    public void carecteristicasAdd(Caracteristica caracteristica) {
        if(carecteristica.existsById(caracteristica.getCaracteristica_id())) {
            throw new IllegalArgumentException("La caracteristica ya existe");
        }
        carecteristica.save(caracteristica);
    }
}