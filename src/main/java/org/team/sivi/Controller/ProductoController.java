package org.team.sivi.Controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.team.sivi.Dto.LoteDto.LoteListarResponseDto;
import org.team.sivi.Dto.LoteDto.LoteProductoListarResponseDto;
import org.team.sivi.Dto.ProductoDto.*;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Service.Producto.ProductoService;
import java.net.URI;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearProducto")
    public ResponseEntity<ProductoCrearResponseDto> crearProducto(@Valid @RequestBody ProductoCrearRequestDto productoCrearRequestDto) throws NotFoundException, BadRequestException {

        ProductoCrearResponseDto productoCrear = productoService.crearProducto(productoCrearRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productoCrear.getId())
                .toUri();

        return ResponseEntity.created(location).body(productoCrear);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/filtrarProductos")
    public ResponseEntity<Page<ProductoListarAdminResponseDto>> filtrarProductosAdmin(@RequestParam(required = false) String search, @RequestParam(required = false) Boolean activo, @RequestParam(required = false) Long categoriaId, Pageable pageable) throws NotFoundException {

        Page<ProductoListarAdminResponseDto> filtrarProductosResponseDto = productoService.filtrarProductosAdmin(search, activo, categoriaId, pageable);

        return ResponseEntity.ok(filtrarProductosResponseDto);
    }

    @PreAuthorize("hasRole('VEND')")
    @GetMapping("/vend/filtrarProductos")
    public ResponseEntity<Page<ProductoListarVendResponseDto>> listarProductosAdmin(@RequestParam(required = false) String search, @RequestParam(required = false) Long categoriaId, Pageable pageable) throws NotFoundException {

        Page<ProductoListarVendResponseDto> filtrarProductosResponseDto = productoService.filtrarProductosVend(search, categoriaId, pageable);

        return ResponseEntity.ok(filtrarProductosResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editarProducto/{id}")
    public ResponseEntity<Void> editarProducto(@PathVariable Long id, @Valid @RequestBody ProductoEditarRequestDto productoEditarRequestDto) throws NotFoundException, BadRequestException {

        productoService.editarProducto(id, productoEditarRequestDto);

        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminarProducto/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) throws NotFoundException, BadRequestException {

        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VEND')")
    @GetMapping("/alertarProductosBajoStock")
    public ResponseEntity<Page<ProductoAlertarResponseDto>> alertarProductoBajoStock(@RequestParam(required = false) String search, Pageable pageable) throws NotFoundException {

        Page<ProductoAlertarResponseDto> productoAlertarResponseDto = productoService.alertarProductoBajoStock(search, pageable);

        return ResponseEntity.ok(productoAlertarResponseDto);

    }

    //Endpoint para filtrar los productos cuando se cree un nuevo lote
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/filtrarProductosLotes")
    public ResponseEntity<Page<LoteProductoListarResponseDto>> filtrarProductosLotes(@RequestParam(required = false) String search, Pageable pageable) {

        Page<LoteProductoListarResponseDto> loteProductoListarResponseDto = productoService.filtrarProductosLotes(search, pageable);

        return ResponseEntity.ok(loteProductoListarResponseDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','VEND')")
    @GetMapping("/filtrarProductosToVenta")
    public ResponseEntity<Page<ProductoVentaListarResponseDto>> filtrarProductosToVenta(@RequestParam(required = false)  String search, Pageable pageable) {

        Page<ProductoVentaListarResponseDto>productoVentaListarResponseDto=productoService.filtrarProductosToVenta(search,pageable);

        return ResponseEntity.ok(productoVentaListarResponseDto);
    }

}