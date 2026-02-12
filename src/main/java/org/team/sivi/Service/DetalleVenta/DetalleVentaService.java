package org.team.sivi.Service.DetalleVenta;

import org.team.sivi.Dto.VentaDto.DetalleVentaListarResponseDto;

import java.util.List;

public interface DetalleVentaService {

    public List<DetalleVentaListarResponseDto> listarDetallesVenta(  String codigoVenta);
}
