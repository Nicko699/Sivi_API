package org.team.sivi.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.ResetTokenCambiarPasswordRequestDto;
import org.team.sivi.Dto.UsuarioDto.ResetTokenRecuperarPasswordRequestDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Model.ResetToken;
import org.team.sivi.Model.Usuario;
import org.team.sivi.Repository.ResetTokenRepository;
import org.team.sivi.Repository.UsuarioRepository;


import java.time.Instant;
import java.util.Optional;

@Service
public class ResetTokenServiceImpl implements ResetTokenService {

    private final UsuarioRepository usuarioRepository;

    private final ResetTokenRepository resetTokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final CorreoService correoService;

    public ResetTokenServiceImpl(UsuarioRepository usuarioRepository, ResetTokenRepository resetTokenRepository, PasswordEncoder passwordEncoder, CorreoService correoService) {
        this.usuarioRepository = usuarioRepository;
        this.resetTokenRepository = resetTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.correoService = correoService;
    }

@Transactional
    @Override
    public void recuperarPassword(ResetTokenRecuperarPasswordRequestDto recuperarPasswordRequestDto) throws BadRequestException {

        Optional<Usuario>usuario=usuarioRepository.findUsuarioByCorreo(recuperarPasswordRequestDto.getCorreo());

        if (usuario.isPresent()){

     correoService.enviarCorreoRecuperacionAsync(usuario.get());

        }
        else {
            throw new BadRequestException("El correo electronico ingresado no se encuentra registrado en SIVI");
        }

    }


    @Transactional
    @Override
    public void cambiarPassword(ResetTokenCambiarPasswordRequestDto cambiarPasswordRequestDto) throws BadRequestException, NotFoundException {

  //En la entidad ResetTokenId es solo resetToken y en el Dto si es con Id
        //En la entidad resetToken encriptado es resetTokenHash y en el Dto es solo resetToken por qué no está hasheado
  Optional<ResetToken> resetToken=resetTokenRepository.findByResetToken(cambiarPasswordRequestDto.getResetTokenId());

  if (resetToken.isPresent()) {

      ResetToken resetTokenGet = resetToken.get();


      if (!resetTokenGet.isValido()) {

          throw new BadRequestException("El token ya se ha usado");

      }
      if (!resetTokenGet.getFechaExpiracion().isAfter(Instant.now())){

          throw new BadRequestException("Token caducado");

      }

      boolean coincide=passwordEncoder.matches(cambiarPasswordRequestDto.getResetToken(),resetTokenGet.getResetTokenHash());

      if (!coincide) {

          throw new BadRequestException("El token de restablecimiento no coincide o es inválido.");
      }

      Usuario usuario=resetTokenGet.getUsuario();

      String passwordHash=passwordEncoder.encode(cambiarPasswordRequestDto.getPassword());

      //Con transaccional cualquier cambio que se haga lo hace jpa automaticamente sin save
      usuario.setPassword(passwordHash);


      resetTokenGet.setValido(false);

  }
  else {

      throw new NotFoundException("El token no existe");
  }

    }
}

