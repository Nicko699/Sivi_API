package org.team.sivi.Service;

import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;

public interface CategoriaService {
    CategoriaResponseDto crear(CategoriaCrearRequestDto dto) throws BadRequestException;
    void eliminar(Long id) throws NotFoundException, BadRequestException;
}