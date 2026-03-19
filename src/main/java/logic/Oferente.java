package logic;

import logic.Caracteristica;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Oferente {
    @jakarta.persistence.Id
    private String id;
    private String correo;
    private String nombre;
    private String primerApellido;
    private String ubicacion;
    private String nacionalidad;
    private int telefono;
    private ArrayList<Caracteristica> caracteristicas;
}
