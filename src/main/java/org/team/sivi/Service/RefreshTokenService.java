package org.team.sivi.Service;

import org.team.sivi.Dto.RefreshTokenRenovarAccesTokenRequestDto;
import org.team.sivi.Dto.RefreshTokenRenovarAccesTokenResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Exception.UnauthorizedException;

public interface RefreshTokenService {

    public RefreshTokenRenovarAccesTokenResponseDto renovarToken(RefreshTokenRenovarAccesTokenRequestDto renovarAccesTokenRequestDto) throws NotFoundException, UnauthorizedException;
}
