package org.team.sivi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team.sivi.Model.Venta;
import org.team.sivi.Dto.ReporteDto.TotalVentasDiaDto;
import java.time.LocalDateTime;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Metodo para el Widget de "Hoy"
    @Query("SELECT new org.team.sivi.Dto.ReporteDto.TotalVentasDiaDto(SUM(v.montoTotal), COUNT(v)) " +
            "FROM Venta v WHERE DATE(v.fechaVenta) = CURRENT_DATE")
    TotalVentasDiaDto obtenerResumenVentasHoy();

    // NUEVO METODO PARA EL REPORTE DETALLADO (El que te da error en rojo)
    @Query("SELECT new org.team.sivi.Dto.ReporteDto.TotalVentasDiaDto(SUM(v.montoTotal), COUNT(v)) " +
            "FROM Venta v WHERE v.fechaVenta BETWEEN :inicio AND :fin")
    TotalVentasDiaDto sumarVentasEntreFechas(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}