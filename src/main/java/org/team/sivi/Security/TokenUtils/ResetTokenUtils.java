package org.team.sivi.Security.TokenUtils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.team.sivi.Dto.ResetTokenResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Model.ResetToken;
import org.team.sivi.Model.Usuario;
import org.team.sivi.Repository.ResetTokenRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResetTokenUtils {

    private final ResetTokenRepository resetTokenRepository;

    private final PasswordEncoder passwordEncoder;

    public ResetTokenUtils(ResetTokenRepository resetTokenRepository, PasswordEncoder passwordEncoder) {
        this.resetTokenRepository = resetTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

  @Transactional
    public ResetTokenResponseDto crearResetToken(Usuario usuario) throws BadRequestException {

        Optional<ResetToken>resetToken=resetTokenRepository.findByUsuario(usuario);

        // Si existe un token anterior, eliminarlo
        if (resetToken.isPresent()) {

            ResetToken resetTokenGet=resetToken.get();


           resetTokenRepository.deleteResetTokenById(resetTokenGet.getId());
        }

        // Crear nuevo token
        ResetToken nuevoResetToken = new ResetToken();

        Instant fechaCreacion = Instant.now();
        Instant fechaExpiracion = fechaCreacion.plus(Duration.ofMinutes(10));

        String resetTokenId = UUID.randomUUID().toString();
        String resetTokenC = UUID.randomUUID().toString();
        String resetTokenHash = passwordEncoder.encode(resetTokenC);

        nuevoResetToken.setResetToken(resetTokenId);
        nuevoResetToken.setResetTokenHash(resetTokenHash);
        nuevoResetToken.setFechaCreacion(fechaCreacion);
        nuevoResetToken.setFechaExpiracion(fechaExpiracion);
        nuevoResetToken.setValido(true);
        nuevoResetToken.setUsuario(usuario);

        resetTokenRepository.save(nuevoResetToken);

        return new ResetTokenResponseDto(resetTokenId, resetTokenC);
    }
 }



