package org.team.sivi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team.sivi.Model.DetalleVenta;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {

    List<DetalleVenta>findByVenta_CodigoVenta(String codigoVenta);

    List<DetalleVenta>findByVenta_CodigoVentaAndVenta_Usuario_Correo(String codigoVenta,String correo);

}
