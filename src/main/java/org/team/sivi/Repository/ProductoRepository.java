package org.team.sivi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team.sivi.Model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Validación de eliminar
    boolean existsByCategoriaId(Long id);
}