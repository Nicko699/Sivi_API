package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.team.sivi.Dto.VentaDto.DetalleVentaListarResponseDto;
import org.team.sivi.Dto.VentaDto.VentaCrearResponseDto;
import org.team.sivi.Dto.VentaDto.VentaListarAdminResponseDto;
import org.team.sivi.Dto.VentaDto.VentaListarVendResponseDto;
import org.team.sivi.Model.Venta;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    VentaCrearResponseDto ventaToVentaCrearResponseDto(Venta venta);

    VentaListarAdminResponseDto ventaToVentaListarAdminResponseDto(Venta venta);

    VentaListarVendResponseDto ventaToVentaListarVendResponseDto(Venta venta);

}
