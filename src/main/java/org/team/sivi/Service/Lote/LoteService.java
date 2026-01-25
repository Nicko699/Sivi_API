package org.team.sivi.Service.Lote;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.team.sivi.Dto.LoteDto.LoteCrearRequestDto;
import org.team.sivi.Dto.LoteDto.LoteCrearResponseDto;
import org.team.sivi.Dto.LoteDto.LoteEditarRequestDto;
import org.team.sivi.Dto.LoteDto.LoteListarResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;

public interface LoteService {

    public LoteCrearResponseDto crearLote(LoteCrearRequestDto loteCrearRequestDto) throws NotFoundException, BadRequestException;

    public Page<LoteListarResponseDto> filtrarLotes(String search, Boolean agotado, Pageable pageable) throws NotFoundException;

    public void editarLote(Long id, LoteEditarRequestDto loteEditarRequestDto)throws NotFoundException,BadRequestException;

    public void eliminarLote(Long id) throws NotFoundException,BadRequestException;
}
