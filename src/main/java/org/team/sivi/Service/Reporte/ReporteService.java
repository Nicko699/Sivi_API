package org.team.sivi.Service.Reporte;

import org.team.sivi.Dto.ReporteDto.TotalGananciasResponseDto;
import org.team.sivi.Dto.ReporteDto.TotalVentasResponseDto;

import java.time.LocalDate;

public interface ReporteService {

  public TotalVentasResponseDto obtenerTotalVentasHoy();

  public TotalVentasResponseDto obtenerVentasPorRango(LocalDate inicio, LocalDate fin);

  public TotalGananciasResponseDto obtenerTotalGananciasHoy();

  public TotalGananciasResponseDto obtenerGananciasPorRango(LocalDate inicio, LocalDate fin);
}
