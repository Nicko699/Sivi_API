package org.team.sivi.Controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearResponseDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaEditarRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
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

    @PreAuthorize("hasAnyRole('ADMIN','VEND')")
    @GetMapping("/obtenerCategoriasFiltradas")
    public ResponseEntity<Page<CategoriaListaResponseDto>> filtrarCategorias(@RequestParam(required = false) String nombre, @RequestParam(required = false) Boolean activo, Pageable pageable) throws NotFoundException {

        Page<CategoriaListaResponseDto> listaResponseDto=categoriaService.filtrarCategorias(nombre,activo,pageable);

        return ResponseEntity.ok(listaResponseDto);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearCategoria")
    public ResponseEntity<CategoriaCrearResponseDto> crearCategoria(@Valid @RequestBody CategoriaCrearRequestDto dto) throws BadRequestException {

        CategoriaCrearResponseDto response = categoriaService.crearCategoria(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editarCategoria/{id}")
    public ResponseEntity<Void> editarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaEditarRequestDto dto) throws NotFoundException, BadRequestException {

        categoriaService.editarCategoria(id,dto);

        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) throws NotFoundException, BadRequestException {

        categoriaService.eliminarCategoria(id);

        return ResponseEntity.noContent().build();
    }
}
