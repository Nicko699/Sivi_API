package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    // De DTO a Entidad (Para crear)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", constant = "true") // Por defecto activo
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Categoria requestDtoToCategoria(CategoriaCrearRequestDto dto);

    // De Entidad a DTO (Para responder al cliente)
    CategoriaResponseDto categoriaToResponseDto(Categoria categoria);
}