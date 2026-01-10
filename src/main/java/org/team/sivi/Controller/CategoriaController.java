package org.team.sivi.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Service.Categoria.CategoriaService;

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
}
