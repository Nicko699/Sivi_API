package org.team.sivi.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.sivi.Dto.MensajeResponseDto;
import org.team.sivi.Dto.ResetTokenCambiarPasswordRequestDto;
import org.team.sivi.Dto.UsuarioDto.ResetTokenRecuperarPasswordRequestDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Service.ResetTokenService;


@RestController
@RequestMapping("/resetToken")
public class ResetTokenController {

    private final ResetTokenService resetTokenService;

    public ResetTokenController(ResetTokenService resetTokenService) {
        this.resetTokenService = resetTokenService;
    }


    @PostMapping("/recuperarContraseña")
    public ResponseEntity<MensajeResponseDto> recuperarPassword( @Valid @RequestBody ResetTokenRecuperarPasswordRequestDto recuperarPasswordRequestDto) throws BadRequestException {

        resetTokenService.recuperarPassword(recuperarPasswordRequestDto);

        return ResponseEntity.ok(new MensajeResponseDto("Te enviamos un enlace a tu correo electrónico para que puedas restablecer tu contraseña. Revisa tu bandeja de entrada o spam."));
    }

    @PostMapping("/cambiarContraseña")
    public ResponseEntity<MensajeResponseDto> cambiarPassword(@Valid @RequestBody  ResetTokenCambiarPasswordRequestDto cambiarPasswordRequestDto) throws BadRequestException, NotFoundException {

        resetTokenService.cambiarPassword(cambiarPasswordRequestDto);

        return ResponseEntity.ok(new MensajeResponseDto("¡Contraseña cambiada exitosamente!"));
    }
}
