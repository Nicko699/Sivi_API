package org.team.sivi.Service.Reporte;

import org.team.sivi.Dto.ReporteDto.TotalVentasResponseDto;

import java.time.LocalDate;

public interface ReporteService {

  public TotalVentasResponseDto obtenerTotalVentasHoy();

  public TotalVentasResponseDto obtenerVentasPorRango(LocalDate inicio, LocalDate fin);
}
