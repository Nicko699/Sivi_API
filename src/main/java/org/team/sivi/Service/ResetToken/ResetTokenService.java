package org.team.sivi.Service.ResetToken;

import org.team.sivi.Dto.ResetTokenDto.ResetTokenCambiarPasswordRequestDto;
import org.team.sivi.Dto.ResetTokenDto.ResetTokenRecuperarPasswordRequestDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;

public interface ResetTokenService {

    public void recuperarPassword(ResetTokenRecuperarPasswordRequestDto recuperarPasswordRequestDto) throws BadRequestException;


    public void cambiarPassword(ResetTokenCambiarPasswordRequestDto cambiarPasswordRequestDto) throws BadRequestException, NotFoundException;
}
