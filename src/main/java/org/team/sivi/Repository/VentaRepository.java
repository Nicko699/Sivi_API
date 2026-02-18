package org.team.sivi.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.team.sivi.Dto.ReporteDto.TotalGananciasResponseDto;
import org.team.sivi.Dto.ReporteDto.TotalVentasResponseDto;
import org.team.sivi.Model.Venta;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long>, JpaSpecificationExecutor<Venta> {

 Optional<Venta> findByCodigoVenta(String codigo);

 // Consulta a la bd para obtener ventas del dia
 @Query("SELECT new org.team.sivi.Dto.ReporteDto.TotalVentasResponseDto(SUM(v.montoTotal), COUNT(v)) " +
         "FROM Venta v WHERE DATE(v.fechaVenta) = CURRENT_DATE")
 TotalVentasResponseDto obtenerResumenVentasHoy();

 @Query("SELECT new org.team.sivi.Dto.ReporteDto.TotalVentasResponseDto(SUM(v.montoTotal), COUNT(v)) " +
         "FROM Venta v WHERE v.fechaVenta BETWEEN :inicio AND :fin")
 TotalVentasResponseDto sumarVentasEntreFechas(LocalDateTime inicio, LocalDateTime fin);

 // Consulta a la bd para obtener ganancias del dia
 @Query("SELECT new org.team.sivi.Dto.ReporteDto.TotalGananciasResponseDto( " +
         "COALESCE(SUM(v.gananciaVenta), 0), COUNT(v)) " +
         "FROM Venta v " +
         "WHERE DATE(v.fechaVenta) = CURRENT_DATE")
 TotalGananciasResponseDto obtenerResumenGananciasHoy();

 @Query("SELECT new org.team.sivi.Dto.ReporteDto.TotalGananciasResponseDto( " +
         "COALESCE(SUM(v.gananciaVenta), 0), COUNT(v)) " +
         "FROM Venta v " +
         "WHERE v.fechaVenta BETWEEN :inicio AND :fin")
 TotalGananciasResponseDto sumarGananciasEntreFechas(LocalDateTime inicio, LocalDateTime fin);



}
