package org.team.sivi.Controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.team.sivi.Dto.VentaDto.*;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Model.Usuario;
import org.team.sivi.Service.Venta.VentaService;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/venta")
public class VentaController {
    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','VEND')")
    @PostMapping("/crearVenta")
    public ResponseEntity<VentaCrearResponseDto> crearVenta(@Valid @RequestBody VentaCrearRequestDto ventaCrearRequestDto) throws NotFoundException, BadRequestException{

        VentaCrearResponseDto ventaCrearResponseDto=ventaService.crearVenta(ventaCrearRequestDto);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(ventaCrearResponseDto.getId())
                .toUri();

         return ResponseEntity.created(location).body(ventaCrearResponseDto);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/filtrarVentas")
    public ResponseEntity<Page<VentaListarAdminResponseDto>> filtrarVentasAdmin(@RequestParam(required = false) String codigoVenta,@RequestParam(required = false) String estado,@RequestParam(required = false) String nombreUsuario,@RequestParam(required = false) LocalDate desde,@RequestParam(required = false) LocalDate hasta, Pageable pageable){

       Page<VentaListarAdminResponseDto>ventaListarAdminResponseDto=ventaService.filtrarVentasAdmin(codigoVenta,estado,nombreUsuario,desde,hasta,pageable);

        return ResponseEntity.ok(ventaListarAdminResponseDto);
    }

    @PreAuthorize("hasRole('VEND')")
   @GetMapping("/vend/filtrarVentas")
    public ResponseEntity<Page<VentaListarVendResponseDto>>filtrarVentasVend( @AuthenticationPrincipal  Usuario usuario,@RequestParam(required = false) String codigoVenta,@RequestParam(required = false) String estado,@RequestParam(required = false) LocalDate desde,@RequestParam(required = false) LocalDate hasta, Pageable pageable){

        Page<VentaListarVendResponseDto>ventaListarVendResponseDto=ventaService.filtrarVentasVend(codigoVenta,estado,desde,hasta,pageable);

        return ResponseEntity.ok(ventaListarVendResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/cambiarEstado/{id}")
    public ResponseEntity<Void> cambiarEstado(@PathVariable  Long id, @Valid @RequestBody VentaCambiarEstadoRequestDto ventaCambiarEstadoRequestDto) throws NotFoundException,BadRequestException{

        ventaService.cambiarEstado(id,ventaCambiarEstadoRequestDto);

        return ResponseEntity.noContent().build();

    }
}
