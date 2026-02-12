package org.team.sivi.Controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.team.sivi.Dto.ReporteDto.TotalVentasDiaDto;
import org.team.sivi.Service.Reporte.ReporteService;

import java.time.LocalDate;

@RestController
@RequestMapping("/reportes")
@CrossOrigin(origins = "*") // conexion front y back
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    // Endpoint para el Widget del Inicio (Hoy)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ventas-hoy")
    public ResponseEntity<TotalVentasDiaDto> obtenerVentasHoy() {
        return ResponseEntity.ok(reporteService.obtenerTotalVentasHoy());
    }

    // Endpoint para el Reporte Detallado (Por rango de fechas)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ventas-rango")
    public ResponseEntity<TotalVentasDiaDto> obtenerVentasRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(reporteService.obtenerVentasPorRango(inicio, fin));
    }
}