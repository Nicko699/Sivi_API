package org.team.sivi.Service.Reporte;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.ReporteDto.TotalGananciasResponseDto;
import org.team.sivi.Dto.ReporteDto.TotalVentasResponseDto;
import org.team.sivi.Repository.VentaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final VentaRepository ventaRepository;

    public ReporteServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public TotalVentasResponseDto obtenerTotalVentasHoy() {

        TotalVentasResponseDto ventasResponseDto=ventaRepository.obtenerResumenVentasHoy();


        if (ventasResponseDto==null){
            ventasResponseDto=new TotalVentasResponseDto(BigDecimal.ZERO,0L);
        }

        if (ventasResponseDto.getTotalVendido() == null) {
            ventasResponseDto.setTotalVendido(BigDecimal.ZERO);
        }
        if (ventasResponseDto.getCantidadVentas() == null) {
            ventasResponseDto.setCantidadVentas(0L);
        }

        return ventasResponseDto;
    }

    @Transactional(readOnly = true)
    @Override
    public TotalVentasResponseDto obtenerVentasPorRango(LocalDate inicio, LocalDate fin) {


        TotalVentasResponseDto ventasResponseDto=ventaRepository.sumarVentasEntreFechas(inicio.atStartOfDay(), fin.atTime(23,59,59));

        if (ventasResponseDto==null){

            ventasResponseDto=new TotalVentasResponseDto(BigDecimal.ZERO,0L);
        }

        if (ventasResponseDto.getTotalVendido() == null) {
            ventasResponseDto.setTotalVendido(BigDecimal.ZERO);
        }
        if (ventasResponseDto.getCantidadVentas() == null) {
            ventasResponseDto.setCantidadVentas(0L);
        }

        return ventasResponseDto;
    }

    @Transactional(readOnly = true)
    @Override
    public TotalGananciasResponseDto obtenerTotalGananciasHoy() {

        TotalGananciasResponseDto gananciasResponseDto = ventaRepository.obtenerResumenGananciasHoy();

        if (gananciasResponseDto == null) {

            gananciasResponseDto = new TotalGananciasResponseDto(BigDecimal.ZERO, 0L);

        }

        if (gananciasResponseDto.getTotalGanancia() == null) {
            gananciasResponseDto.setTotalGanancia(BigDecimal.ZERO);
        }

        if (gananciasResponseDto.getCantidadVentas() == null) {
            gananciasResponseDto.setCantidadVentas(0L);
        }

        return gananciasResponseDto;
    }

    @Transactional(readOnly = true)
    @Override
    public TotalGananciasResponseDto obtenerGananciasPorRango(LocalDate inicio, LocalDate fin) {

        TotalGananciasResponseDto gananciasResponseDto=ventaRepository.sumarGananciasEntreFechas(inicio.atStartOfDay(),fin.atTime(23,59,59));

        if (gananciasResponseDto == null) {

            gananciasResponseDto = new TotalGananciasResponseDto(BigDecimal.ZERO, 0L);

        }

        if (gananciasResponseDto.getTotalGanancia() == null) {
            gananciasResponseDto.setTotalGanancia(BigDecimal.ZERO);
        }

        if (gananciasResponseDto.getCantidadVentas() == null) {
            gananciasResponseDto.setCantidadVentas(0L);
        }

        return gananciasResponseDto;
    }
}
