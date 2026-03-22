package data;

import logic.Puesto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PuestoRepository extends CrudRepository<Puesto, String> {
    List<Puesto> findTop5ByTipoAndActivoOrderByFechaRegistroDesc(String tipo, Byte activo);
}