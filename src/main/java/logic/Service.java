package logic;

import data.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import data.EmpresasRepository;

@org.springframework.stereotype.Service

public class Service {
    @Autowired
    private EmpresasRepository Empresas;
    private AdminRepository Admin;

    public Iterable<Empresa> empresasAll() {
        return Empresas.findAll();
    }
    public Iterable<Admin> adminsAll() { return Admin.findAll();}

    public void empresasAdd(Empresa empresa) {
        if(Empresas.existsById(empresa.getId())) {
            throw new IllegalArgumentException("La empresa ya existe");
        }
        Empresas.save(empresa);
    }
    public void adminsAdd(Admin admin) {
        if(Admin.existsById(admin.getId())) {
            throw new IllegalArgumentException("El admin ya existe");
        }
        Admin.save(admin);
    }
}