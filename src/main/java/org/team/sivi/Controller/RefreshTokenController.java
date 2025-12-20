package org.team.sivi.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.team.sivi.Dto.RefreshTokenRenovarAccesTokenResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Exception.UnauthorizedException;
import org.team.sivi.Service.RefreshTokenService;

@RestController
@RequestMapping("/refreshToken")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    //Endpoint de renovarToken
    @PostMapping("/renovarToken")
    public ResponseEntity<RefreshTokenRenovarAccesTokenResponseDto> renovarToken(
            HttpServletRequest request,
            HttpServletResponse response,
            @CookieValue(value = "refreshTokenId", required = false) String refreshTokenId,
            @CookieValue(value = "refreshToken", required = false) String refreshToken
    ) throws NotFoundException, UnauthorizedException {

        // 🐛 DEBUG: Ver qué cookies llegan
        Cookie[] cookies = request.getCookies();
        System.out.println("=== COOKIES RECIBIDAS EN /renovarToken ===");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("  ✅ " + cookie.getName() + " = " + cookie.getValue());
            }
        } else {
            System.out.println("  ❌ NO HAY COOKIES EN LA REQUEST");
        }
        System.out.println("==========================================");

        System.out.println("📋 @CookieValue refreshTokenId: " + refreshTokenId);
        System.out.println("📋 @CookieValue refreshToken: " + refreshToken);
        System.out.println("==========================================");

        // Validación manual por si @CookieValue no funciona
        if (refreshTokenId == null || refreshToken == null) {
            System.out.println("⚠️ Cookies faltantes, retornando error 400");
            return ResponseEntity.status(400).body(null);
        }

        RefreshTokenRenovarAccesTokenResponseDto renovarAccesTokenResponseDto =
                refreshTokenService.renovarToken(response, refreshTokenId, refreshToken);

        return ResponseEntity.ok().body(renovarAccesTokenResponseDto);
    }
}
