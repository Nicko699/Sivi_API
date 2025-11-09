package org.team.sivi.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.sivi.Dto.RefreshTokenRenovarAccesTokenRequestDto;
import org.team.sivi.Dto.RefreshTokenRenovarAccesTokenResponseDto;
import org.team.sivi.Dto.ResetTokenCambiarPasswordRequestDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Exception.UnauthorizedException;
import org.team.sivi.Service.RefreshTokenService;

import java.util.Map;

@RestController
@RequestMapping("/refreshToken")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }
   //Endpoint de renovarToken
    @PostMapping("/renovarToken")
    public ResponseEntity<RefreshTokenRenovarAccesTokenResponseDto> renovarToken(@Valid @RequestBody RefreshTokenRenovarAccesTokenRequestDto renovarAccesTokenRequestDto) throws NotFoundException, UnauthorizedException{

        RefreshTokenRenovarAccesTokenResponseDto renovarAccesTokenResponseDto=refreshTokenService.renovarToken(renovarAccesTokenRequestDto);

        return ResponseEntity.ok().body(renovarAccesTokenResponseDto);
    }



}
