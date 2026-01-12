package org.team.sivi.Service;

import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaEditarRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;

public interface CategoriaService {
    CategoriaResponseDto crear(CategoriaCrearRequestDto dto);
    CategoriaResponseDto editar(Long id, CategoriaEditarRequestDto dto);  // ← Solo quité throws
    void eliminar(Long id);
}
