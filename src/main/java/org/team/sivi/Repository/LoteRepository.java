package org.team.sivi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.team.sivi.Model.Lote;
import org.team.sivi.Model.Producto;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<Lote,Long>, JpaSpecificationExecutor<Lote> {

    List<Lote> findByProductoAndAgotadoFalse(Producto producto);

    List<Lote>findByAgotadoFalseOrderByFechaCompraDesc();

}
