package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaListaResponseDto categoriaToCategoriaListaResponseDto(Categoria categoria);

}
