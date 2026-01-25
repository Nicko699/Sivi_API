package org.team.sivi.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.team.sivi.Dto.RefreshTokenDto.RefreshTokenRenovarAccesTokenResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Exception.UnauthorizedException;
import org.team.sivi.Service.RefreshToken.RefreshTokenService;

@RestController
@RequestMapping("/refreshToken")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    //Endpoint de renovarToken
    @PostMapping("/renovarToken")
    public ResponseEntity<RefreshTokenRenovarAccesTokenResponseDto> renovarToken(HttpServletResponse response, @CookieValue(value = "refreshTokenId", required = false) String refreshTokenId, @CookieValue(value = "refreshToken", required = false) String refreshToken) throws NotFoundException, UnauthorizedException {

        // Validación manual por si @CookieValue no funciona
        if (refreshTokenId == null || refreshToken == null) {
            return ResponseEntity.status(400).body(null);
        }

        RefreshTokenRenovarAccesTokenResponseDto renovarAccesTokenResponseDto =
                refreshTokenService.renovarToken(response, refreshTokenId, refreshToken);

        return ResponseEntity.ok().body(renovarAccesTokenResponseDto);
    }
}
