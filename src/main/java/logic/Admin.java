package logic;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @jakarta.persistence.Id
    private String id;
    private String correo;
    private String nombre;
    private String descripcion;
    private int telefono;
}
