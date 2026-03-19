package logic;

import jakarta.persistence.*;
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
@Table(name = "puesto")

public class Puesto {

    @jakarta.persistence.Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    private String descripcion;
    private double salario;
    private boolean activo = true;
    private ArrayList<Caracteristica> caracteristicasAll = new ArrayList<>();
}