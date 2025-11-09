package org.team.sivi.Service;

import org.team.sivi.Dto.ResetTokenCambiarPasswordRequestDto;
import org.team.sivi.Dto.UsuarioDto.ResetTokenRecuperarPasswordRequestDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Model.Usuario;

public interface ResetTokenService {

    public void recuperarPassword(ResetTokenRecuperarPasswordRequestDto recuperarPasswordRequestDto) throws BadRequestException;


    public void cambiarPassword(ResetTokenCambiarPasswordRequestDto cambiarPasswordRequestDto) throws BadRequestException, NotFoundException;
}
