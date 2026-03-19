package logic;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Caracteristica {
    @jakarta.persistence.Id
    private String caracteristica_id;
    @ManyToOne
    @JoinColumn(name = "caracteristica_id")
    private Caracteristica idPadre;
    private String descripcion;
    private String nombre;
}
