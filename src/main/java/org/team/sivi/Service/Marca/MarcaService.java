package org.team.sivi.Service.Marca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.team.sivi.Dto.MarcaDto.MarcaCrearRequestDto;
import org.team.sivi.Dto.MarcaDto.MarcaCrearResponseDto;
import org.team.sivi.Dto.MarcaDto.MarcaEditarRequestDto;
import org.team.sivi.Dto.MarcaDto.MarcaListarResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;

public interface MarcaService {

    public MarcaCrearResponseDto crearMarca(MarcaCrearRequestDto crearRequestDto);

    public Page<MarcaListarResponseDto>filtrarMarca(String nombre, Boolean activo, Pageable pageable) throws NotFoundException;

    public void editarMarca(Long id, MarcaEditarRequestDto editarRequestDto)throws  NotFoundException, BadRequestException;

    public void eliminarMarca(Long id)throws NotFoundException, BadRequestException;

}
