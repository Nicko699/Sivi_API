package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearResponseDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaEditarRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Model.Categoria;
import org.team.sivi.Model.Marca;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaListaResponseDto categoriaToCategoriaListaResponseDto(Categoria categoria);

    Categoria categoriaCrearRequestDtoToCategoria(CategoriaCrearRequestDto categoriaCrearRequestDto);

    CategoriaCrearResponseDto categoriaToCategoriaCrearResponseDto(Categoria categoria);

    void categoriaEditarRequestDtoToCategoria (CategoriaEditarRequestDto editarRequestDto, @MappingTarget Categoria categoria);

}
