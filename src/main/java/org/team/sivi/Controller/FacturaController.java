package org.team.sivi.Controller;

import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.sivi.Dto.FacturaDto.FacturaCrearRequestDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Service.Factura.FacturaService;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','VEND')")
    @PostMapping("/crearFactura")
    public ResponseEntity<byte[]> generarFactura( @Valid @RequestBody FacturaCrearRequestDto facturaCrearRequestDto) throws JRException, NotFoundException {

        byte[]factura=facturaService.generarFactura(facturaCrearRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(factura);
    }
}
