package org.team.sivi.Service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.team.sivi.Dto.UsuarioDto.*;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;

public interface UsuarioService {

    public UsuarioCrearCuentaResponseDto crearCuenta(UsuarioCrearCuentaRequestDto crearCuentaRequestDto) throws BadRequestException, NotFoundException;

    public UsuarioIniciarSesionResponseDto iniciarSesion(HttpServletResponse response, UsuarioIniciarSesionRequestDto usuarioIniciarSesionRequestDto) throws NotFoundException;

    public Page<UsuarioListaResponseDto> obtenerListaUsuarios(Pageable pageable) throws NotFoundException;

    public Page<UsuarioListaResponseDto>filtrarUsuarios(String nombre,String rol,Boolean activo,Pageable pageable) throws NotFoundException;

    public void eliminarUsuario(Long id)throws NotFoundException, BadRequestException;
}


