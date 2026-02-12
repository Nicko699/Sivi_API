package org.team.sivi.Service.DetalleVenta;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.VentaDto.DetalleVentaListarResponseDto;
import org.team.sivi.Mapper.DetalleVentaMapper;
import org.team.sivi.Model.DetalleVenta;
import org.team.sivi.Repository.DetalleVentaRepository;

import java.util.Collection;
import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;
    private final DetalleVentaMapper detalleVentaMapper;

    public DetalleVentaServiceImpl(DetalleVentaRepository detalleVentaRepository, DetalleVentaMapper detalleVentaMapper) {
        this.detalleVentaRepository = detalleVentaRepository;
        this.detalleVentaMapper = detalleVentaMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DetalleVentaListarResponseDto> listarDetallesVenta(String codigoVenta) {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> listaRol=authentication.getAuthorities();

        boolean isAdmin=false;

        for (GrantedAuthority authority:listaRol){

            if (authority.getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {

                isAdmin = true;
                break;
            }
        }

        List<DetalleVenta> detalleVentas;

        if (isAdmin) {
            detalleVentas = detalleVentaRepository.findByVenta_CodigoVenta(codigoVenta);
        }
        else {

            String correo=authentication.getName();

            detalleVentas = detalleVentaRepository.findByVenta_CodigoVentaAndVenta_Usuario_Correo(codigoVenta   , correo);

        }

        return detalleVentas.stream().map(detalleVentaMapper::detalleVentaToDetalleVentaListarResponseDto).toList();

    }

}
