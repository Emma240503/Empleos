package data;

import logic.OferenteHasCaracteristica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OferenteHasCaracteristicaRepository extends CrudRepository<OferenteHasCaracteristica, String> {
    Iterable<OferenteHasCaracteristica> findByOferente_Id(String oferenteId);
}