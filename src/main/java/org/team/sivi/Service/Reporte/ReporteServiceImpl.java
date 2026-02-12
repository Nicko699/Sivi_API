package org.team.sivi.Service.Reporte;

import org.springframework.stereotype.Service;
import org.team.sivi.Dto.ReporteDto.TotalVentasDiaDto;
import org.team.sivi.Repository.VentaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final VentaRepository ventaRepository;

    public ReporteServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public TotalVentasDiaDto obtenerTotalVentasHoy() {
        return ventaRepository.obtenerResumenVentasHoy();
    }

    @Override
    public TotalVentasDiaDto obtenerVentasPorRango(LocalDate inicio, LocalDate fin) {
        // Convertimos LocalDate a LocalDateTime para la base de datos
        LocalDateTime inicioDT = inicio.atStartOfDay();
        LocalDateTime finDT = fin.atTime(23, 59, 59);
        return ventaRepository.sumarVentasEntreFechas(inicioDT, finDT);
    }
}
