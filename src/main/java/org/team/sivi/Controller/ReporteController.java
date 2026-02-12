package org.team.sivi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.team.sivi.Dto.ReporteDto.TotalVentasResponseDto;
import org.team.sivi.Service.Reporte.ReporteService;

import java.time.LocalDate;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtenerTotalVentasDia")
    public ResponseEntity<TotalVentasResponseDto> obtenerTotalVentasHoy(){

        TotalVentasResponseDto totalVentasResponseDto=reporteService.obtenerTotalVentasHoy();

        return ResponseEntity.ok(totalVentasResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtenerTotalVentasFecha")
    public ResponseEntity<TotalVentasResponseDto> obtenerVentasPorRango(@RequestParam(required = false) LocalDate inicio,@RequestParam(required = false) LocalDate fin){

        if (inicio == null || fin == null) {
            return ResponseEntity.badRequest().build();
        }

        TotalVentasResponseDto ventasResponseDto=reporteService.obtenerVentasPorRango(inicio,fin);

        return ResponseEntity.ok(ventasResponseDto);

    }
}
