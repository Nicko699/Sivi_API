package org.team.sivi.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.team.sivi.Model.Producto;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {
    // Validación de eliminar
    boolean existsByCategoriaId(Long id);


    Boolean existsByCodigoBarrasAndSoftDeleteFalse(String codigo);

   List<Producto> findAllByCodigoBarrasInAndSoftDeleteFalse( List<String> codigo);

}
