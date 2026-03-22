package data;

import logic.PuestoHasCaracteristica;
import logic.PuestoHasCaracteristicaId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuestoHasCaracteristicaRepository extends CrudRepository<PuestoHasCaracteristica, PuestoHasCaracteristicaId> {
}