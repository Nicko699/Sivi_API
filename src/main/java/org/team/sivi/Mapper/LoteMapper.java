package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.team.sivi.Dto.LoteDto.LoteCrearRequestDto;
import org.team.sivi.Dto.LoteDto.LoteCrearResponseDto;
import org.team.sivi.Dto.LoteDto.LoteEditarRequestDto;
import org.team.sivi.Dto.LoteDto.LoteListarResponseDto;
import org.team.sivi.Model.Lote;

@Mapper(componentModel = "spring")
public interface LoteMapper {

    Lote loteCrearRequestDtoToLote(LoteCrearRequestDto loteCrearRequestDto);

    LoteCrearResponseDto loteToLoteCrearResponseDto(Lote lote);

    LoteListarResponseDto loteToLoteListarResponseDto(Lote lote);

    void loteEditarRequestDtoToLote(LoteEditarRequestDto loteEditarResponseDto, @MappingTarget Lote lote);
}
