package org.team.sivi.Service.Categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaEditarRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;

public interface CategoriaService {

    public Page<CategoriaListaResponseDto> filtrarCategorias(String nombre, Boolean activo,  Pageable pageable) throws NotFoundException;

    CategoriaResponseDto crearCategoria(CategoriaCrearRequestDto dto) throws BadRequestException;

    CategoriaResponseDto editar(Long id, CategoriaEditarRequestDto dto) throws NotFoundException, BadRequestException;

    void eliminarCategoria(Long id) throws NotFoundException, BadRequestException;
}
