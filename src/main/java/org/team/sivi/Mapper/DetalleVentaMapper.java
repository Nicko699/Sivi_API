package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.team.sivi.Dto.VentaDto.DetalleVentaListarResponseDto;
import org.team.sivi.Model.DetalleVenta;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper {

    DetalleVentaListarResponseDto detalleVentaToDetalleVentaListarResponseDto(DetalleVenta detalleVenta);
}
