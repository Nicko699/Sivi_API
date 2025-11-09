package org.team.sivi.Service;

import org.team.sivi.Dto.UsuarioDto.UsuarioCrearCuentaRequestDto;
import org.team.sivi.Dto.UsuarioDto.UsuarioCrearCuentaResponseDto;
import org.team.sivi.Dto.UsuarioDto.UsuarioIniciarSesionRequestDto;
import org.team.sivi.Dto.UsuarioDto.UsuarioIniciarSesionResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;

public interface UsuarioService {

    public UsuarioCrearCuentaResponseDto crearCuenta(UsuarioCrearCuentaRequestDto crearCuentaRequestDto) throws BadRequestException, NotFoundException;

    public UsuarioIniciarSesionResponseDto iniciarSesion(UsuarioIniciarSesionRequestDto usuarioIniciarSesionRequestDto) throws NotFoundException;

    public void eliminarUsuario(Long id)throws NotFoundException;
}


