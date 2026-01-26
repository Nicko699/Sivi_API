package org.team.sivi.Controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.team.sivi.Dto.LoteDto.LoteCrearRequestDto;
import org.team.sivi.Dto.LoteDto.LoteCrearResponseDto;
import org.team.sivi.Dto.LoteDto.LoteEditarRequestDto;
import org.team.sivi.Dto.LoteDto.LoteListarResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Service.Lote.LoteService;

import java.net.URI;

@RestController
@RequestMapping("/lote")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearLote")
    public ResponseEntity<LoteCrearResponseDto> crearLote(@Valid @RequestBody LoteCrearRequestDto loteCrearRequestDto) throws NotFoundException, BadRequestException{

      LoteCrearResponseDto loteCrearResponseDto=loteService.crearLote(loteCrearRequestDto);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loteCrearResponseDto.getId())
                .toUri();

      return ResponseEntity.created(location).body(loteCrearResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/filtrarLotes")
    public ResponseEntity<Page<LoteListarResponseDto>> filtrarLotes(@RequestParam(required = false) String search,@RequestParam(required = false) Boolean agotado, Pageable pageable) throws NotFoundException{

        Page<LoteListarResponseDto>listaLotes=loteService.filtrarLotes(search,agotado,pageable);

        return ResponseEntity.ok(listaLotes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editarLote/{id}")
    public ResponseEntity<Void> editarLote(@PathVariable  Long id,@Valid @RequestBody LoteEditarRequestDto loteEditarRequestDto)throws NotFoundException,BadRequestException{

        loteService.editarLote(id,loteEditarRequestDto);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminarLote/{id}")
    public ResponseEntity<Void> eliminarLote(@PathVariable  Long id) throws NotFoundException,BadRequestException{

        loteService.eliminarLote(id);

        return ResponseEntity.noContent().build();

    }
}
