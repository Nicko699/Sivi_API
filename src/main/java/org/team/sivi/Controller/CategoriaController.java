package org.team.sivi.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Service.Categoria.CategoriaService;

import java.net.URI;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtenerCategoriasFiltradas")
    public ResponseEntity<Page<CategoriaListaResponseDto>> filtrarCategorias(@RequestParam(required = false) String nombre, @RequestParam(required = false) Boolean activo, Pageable pageable) throws NotFoundException {

        Page<CategoriaListaResponseDto> listaResponseDto=categoriaService.filtrarCategorias(nombre,activo,pageable);

        return ResponseEntity.ok(listaResponseDto);
    }

    // --- TAREA JULIANA: CREAR ---
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<CategoriaResponseDto> crear(@RequestBody CategoriaCrearRequestDto dto) throws BadRequestException {

        CategoriaResponseDto response = categoriaService.crearCategoria(dto);

        // Crear la URI del nuevo recurso
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        // Devuelve un estado 201 Created con la ubicación y el objeto creado
        return ResponseEntity.created(location).body(response);
    }

    // --- TAREA JULIANA: ELIMINAR ---
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) throws NotFoundException, BadRequestException {

        categoriaService.eliminarCategoria(id);

        // Devuelve un estado 204 No Content (Éxito pero sin cuerpo de respuesta)
        return ResponseEntity.noContent().build();
    }
}

