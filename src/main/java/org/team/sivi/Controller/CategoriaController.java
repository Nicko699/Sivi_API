package org.team.sivi.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaEditarRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;
import org.team.sivi.Service.CategoriaService;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;

import java.util.Map;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<CategoriaResponseDto> crear(@Valid @RequestBody CategoriaCrearRequestDto dto) throws BadRequestException {
        return ResponseEntity.ok(categoriaService.crear(dto));
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<CategoriaResponseDto> editar(@PathVariable Long id, @Valid @RequestBody CategoriaEditarRequestDto dto) {
        return ResponseEntity.ok(categoriaService.editar(id, dto));  // ← Sin throws
    }


       //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) throws NotFoundException, BadRequestException {
        categoriaService.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Categoría eliminada correctamente"));
    }
}