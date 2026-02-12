package org.team.sivi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.team.sivi.Dto.VentaDto.DetalleVentaListarResponseDto;
import org.team.sivi.Service.DetalleVenta.DetalleVentaService;

import java.util.List;
@RestController
@RequestMapping("/detalleVenta")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','VEND')")
   @GetMapping("/obtenerDetallesVenta/{codigoVenta}")
    public ResponseEntity<List<DetalleVentaListarResponseDto>> listarDetallesVenta(@PathVariable String codigoVenta){

        List<DetalleVentaListarResponseDto>detalleVentaListarResponseDto=detalleVentaService.listarDetallesVenta(codigoVenta);

        return ResponseEntity.ok(detalleVentaListarResponseDto);
    }
}
