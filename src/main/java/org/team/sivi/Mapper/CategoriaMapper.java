package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;
import org.team.sivi.Model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    //Metodo para listar
       CategoriaListaResponseDto categoriaToCategoriaListaResponseDto(Categoria categoria);

    // Convertir DTO de creación a la Entidad
       Categoria categoriaCrearRequestDtoToCategoria(CategoriaCrearRequestDto dto);

    // Convertir Entidad a DTO de respuesta
       CategoriaResponseDto categoriaToCategoriaResponseDto(Categoria entidad);

}

