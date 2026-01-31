package org.team.sivi.Controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaEditarRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Service.Categoria.CategoriaService;

import java.net.URI;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_VEND')")
    @GetMapping("/obtenerCategoriasFiltradas")
    public ResponseEntity<Page<CategoriaListaResponseDto>> filtrarCategorias(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Boolean activo,
            Pageable pageable) throws NotFoundException {
        return ResponseEntity.ok(categoriaService.filtrarCategorias(nombre, activo, pageable));
    }

    // --- TAREA JULIANA: CREAR ---
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<CategoriaResponseDto> crear(@Valid @RequestBody CategoriaCrearRequestDto dto) throws BadRequestException {
        CategoriaResponseDto response = categoriaService.crearCategoria(dto);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    // --- TAREA JEYMY: EDITAR (INTEGRADA) ---
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<CategoriaResponseDto> editar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaEditarRequestDto dto) throws NotFoundException, BadRequestException {
        return ResponseEntity.ok(categoriaService.editar(id, dto));
    }

    // --- TAREA JULIANA: ELIMINAR  ---
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) throws NotFoundException, BadRequestException {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}