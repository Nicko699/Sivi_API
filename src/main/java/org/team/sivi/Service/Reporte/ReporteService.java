package org.team.sivi.Service.Reporte;
import org.team.sivi.Dto.ReporteDto.TotalVentasDiaDto;

import java.time.LocalDate;

public interface ReporteService {
    TotalVentasDiaDto obtenerTotalVentasHoy();
    TotalVentasDiaDto obtenerVentasPorRango(LocalDate inicio, LocalDate fin);
}