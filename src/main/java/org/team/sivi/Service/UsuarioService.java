package org.team.sivi.Service;

import org.team.sivi.Dto.UsuarioCrearCuentaRequestDto;
import org.team.sivi.Dto.UsuarioCrearCuentaResponseDto;
import org.team.sivi.Dto.UsuarioIniciarSesionRequestDto;
import org.team.sivi.Dto.UsuarioIniciarSesionResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;

public interface UsuarioService {

    public UsuarioCrearCuentaResponseDto crearCuenta(UsuarioCrearCuentaRequestDto crearCuentaRequestDto) throws BadRequestException, NotFoundException;

    public UsuarioIniciarSesionResponseDto iniciarSesion(UsuarioIniciarSesionRequestDto usuarioIniciarSesionRequestDto) throws NotFoundException;
}

