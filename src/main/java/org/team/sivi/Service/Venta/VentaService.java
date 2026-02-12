package org.team.sivi.Service.Venta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.team.sivi.Dto.VentaDto.*;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;

import java.time.LocalDate;

public interface VentaService {

    public VentaCrearResponseDto crearVenta(VentaCrearRequestDto ventaCrearRequestDto) throws NotFoundException, BadRequestException;

    public Page<VentaListarAdminResponseDto> filtrarVentasAdmin(String codigoVenta, String estado, String nombreUsuario, LocalDate desde, LocalDate hasta, Pageable pageable);

    public Page<VentaListarVendResponseDto>filtrarVentasVend(String codigoVenta,String estado,LocalDate desde, LocalDate hasta,Pageable pageable);

  public void cambiarEstado(Long id, VentaCambiarEstadoRequestDto ventaCambiarEstadoRequestDto) throws NotFoundException,BadRequestException;

}
