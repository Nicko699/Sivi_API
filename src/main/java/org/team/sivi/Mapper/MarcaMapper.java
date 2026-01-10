package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.team.sivi.Dto.MarcaDto.MarcaCrearRequestDto;
import org.team.sivi.Dto.MarcaDto.MarcaCrearResponseDto;
import org.team.sivi.Dto.MarcaDto.MarcaEditarRequestDto;
import org.team.sivi.Dto.MarcaDto.MarcaListarResponseDto;
import org.team.sivi.Model.Marca;


@Mapper(componentModel = "spring")
public interface MarcaMapper {

  Marca marcaCrearRequestDtoToMarca(MarcaCrearRequestDto marcaCrearRequestDto);

  MarcaCrearResponseDto marcaToMarcaCrearResponseDto(Marca marca);
 @Mapping(source = "fechaActualizacion", target = "fechaActualizacion")
  MarcaListarResponseDto marcaToMarcaListaResponseDto(Marca marca);

  void marcaEditarRequestDtoToMarca(MarcaEditarRequestDto marcaEditarRequestDto, @MappingTarget Marca marca);

}
