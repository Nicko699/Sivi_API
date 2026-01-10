package org.team.sivi.Controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.team.sivi.Dto.MarcaDto.MarcaCrearRequestDto;
import org.team.sivi.Dto.MarcaDto.MarcaCrearResponseDto;
import org.team.sivi.Dto.MarcaDto.MarcaEditarRequestDto;
import org.team.sivi.Dto.MarcaDto.MarcaListarResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Service.Marca.MarcaService;

import java.net.URI;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearMarca")
    public ResponseEntity< MarcaCrearResponseDto> crearMarca(@Valid  @RequestBody MarcaCrearRequestDto crearRequestDto){

        MarcaCrearResponseDto crearResponseDto=marcaService.crearMarca(crearRequestDto);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(crearResponseDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(crearResponseDto);
    }

   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping("/filtrarMarca")
    public ResponseEntity<Page<MarcaListarResponseDto>> filtrarMarca(@RequestParam(required = false)  String nombre, @RequestParam(required = false) Boolean activo, Pageable pageable) throws NotFoundException{

        Page<MarcaListarResponseDto>listaResponseDto=marcaService.filtrarMarca(nombre,activo,pageable);

        return ResponseEntity.ok(listaResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
   @PutMapping("/editarMarca/{id}")
    public ResponseEntity<Void> editarMarca(@PathVariable  Long id, @Valid @RequestBody MarcaEditarRequestDto editarRequestDto)throws  NotFoundException{

        marcaService.editarMarca(id,editarRequestDto);

        return ResponseEntity.noContent().build();

    }

   @DeleteMapping("/eliminarMarca/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id)throws NotFoundException{

        marcaService.eliminarMarca(id);

        return ResponseEntity.noContent().build();
    }

}
