package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.team.sivi.Dto.LoteDto.LoteProductoListarResponseDto;
import org.team.sivi.Dto.ProductoDto.*;
import org.team.sivi.Model.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    Producto productoCrearRequestDtoToProducto(ProductoCrearRequestDto productoCrearRequestDto);

    ProductoCrearResponseDto productoToProductoCrearResponseDto(Producto producto);

    ProductoListarAdminResponseDto productoToProductoListarAdminResponseDto(Producto producto);

    ProductoListarVendResponseDto productoToProductoListarVendResponseDto(Producto producto);

    Producto productoEditarRequestDtoToProducto(ProductoEditarRequestDto productoEditarRequestDto, @MappingTarget Producto producto);

    ProductoAlertarResponseDto productoToProductoAlertaResponseDto(Producto producto);

    LoteProductoListarResponseDto productoToLoteProductoListarResponseDto(Producto producto);

}
