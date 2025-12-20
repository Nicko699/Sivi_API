package org.team.sivi.Service;

import jakarta.servlet.http.HttpServletResponse;
import org.team.sivi.Dto.RefreshTokenRenovarAccesTokenResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Exception.UnauthorizedException;

public interface RefreshTokenService {

    public RefreshTokenRenovarAccesTokenResponseDto renovarToken(HttpServletResponse httpServletResponse, String refreshTokenId, String refreshToken) throws NotFoundException, UnauthorizedException;
}
