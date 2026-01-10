package org.team.sivi.Service.Categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Exception.NotFoundException;

public interface CategoriaService {

    public Page<CategoriaListaResponseDto> filtrarCategorias(String nombre, Boolean activo,  Pageable pageable) throws NotFoundException;
}
