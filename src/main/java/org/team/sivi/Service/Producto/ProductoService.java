package org.team.sivi.Service.Producto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.team.sivi.Dto.LoteDto.LoteProductoListarResponseDto;
import org.team.sivi.Dto.ProductoDto.*;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;


public interface ProductoService {

    public ProductoCrearResponseDto crearProducto(ProductoCrearRequestDto productoCrearRequestDto)throws NotFoundException,BadRequestException;

    public Page<ProductoListarAdminResponseDto> filtrarProductosAdmin(String search, Boolean activo, Long categoriaId, Pageable pageable) throws NotFoundException;

    public Page<ProductoListarVendResponseDto> filtrarProductosVend(String search, Long categoriaId, Pageable pageable)throws NotFoundException;

    public void editarProducto(Long id,  ProductoEditarRequestDto productoEditarRequestDto) throws NotFoundException,BadRequestException;

    public void eliminarProducto(Long id) throws NotFoundException,BadRequestException;

    public Page<ProductoAlertarResponseDto>alertarProductoBajoStock(String search, Pageable pageable) throws NotFoundException;

    public Page<LoteProductoListarResponseDto>filtrarProductosLotes(String search,Pageable pageable);
}
