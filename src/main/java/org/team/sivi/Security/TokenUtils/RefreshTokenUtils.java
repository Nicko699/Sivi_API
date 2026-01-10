package org.team.sivi.Security.TokenUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.RefreshTokenDto.RefreshTokenResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Model.RefreshToken;
import org.team.sivi.Model.Usuario;
import org.team.sivi.Repository.RefreshTokenRepository;
import org.team.sivi.Repository.UsuarioRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenUtils {

    private final UsuarioRepository usuarioRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public RefreshTokenUtils(UsuarioRepository usuarioRepository, RefreshTokenRepository refreshTokenRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }
     //Metodo para crear un nuevo refreshToken
    @Transactional
    public RefreshTokenResponseDto crearRefreshToken(String correo) throws NotFoundException {
        //Encontramos al usuario en la bd por el correo
        Optional<Usuario>usuario=usuarioRepository.findUsuarioByCorreo(correo);
      //Comprobamos si existe en la bd
        if (usuario.isPresent()){
            Usuario usuarioGet=usuario.get();
            //Encontramos al refreshToken en la bd si hay alguno activo
            List<RefreshToken>encontrarRefreshToken=refreshTokenRepository.findAllByUsuarioAndActivo(usuarioGet,true);
           // comprobamos si el RefreshToken está en la bd
            List<RefreshToken>refreshTokensInactivo=new ArrayList<>();

            for (RefreshToken refreshToken:encontrarRefreshToken){
              refreshToken.setActivo(false);
              refreshTokensInactivo.add(refreshToken);
            }
            //Guardamos el refreshToken en la bd
            refreshTokenRepository.saveAll(refreshTokensInactivo);

           //Inicializamos el nuevo refreshToken
            RefreshToken nuevoRefreshToken=new RefreshToken();

            Instant fechaCreacion=Instant.now();
            Instant fechaExpiracion=fechaCreacion.plus(Duration.ofMinutes(30));
            //Creamos la cadena de caracteres para el refreshTokenId
            String refreshTokenId= UUID.randomUUID().toString();
            //Creamos la cadena de caracteres para el refreshTokenC
            String refreshTokenC=UUID.randomUUID().toString();
            //Encriptamos el refreshTokenC
            String refreshTokenHash=passwordEncoder.encode(refreshTokenC);

            //Creamos el nuevo refreshToken
            nuevoRefreshToken.setRefreshToken(refreshTokenId);
            nuevoRefreshToken.setRefreshTokenHash(refreshTokenHash);
            nuevoRefreshToken.setFechaCreacion(fechaCreacion);
            nuevoRefreshToken.setFechaExpiracion(fechaExpiracion);
            nuevoRefreshToken.setActivo(true);
            nuevoRefreshToken.setUsuario(usuarioGet);
           //Lo guardamos en la bd
            refreshTokenRepository.save(nuevoRefreshToken);

           //Retornamos el refreshToken id y RefreshTokenC sin encriptar al cliente, front, postman
            return new RefreshTokenResponseDto(refreshTokenId,refreshTokenC);
        }
        //Si el usuario no existe lanzamos una excepcion
        else {
            throw  new NotFoundException("El usuario no se encuentra en nuestro sistema");
        }


    }

    //Metodo para borrar los refreshTokens expirados que cumplan 30 dias
    @Scheduled(cron = "0 0 3 * * ?")
    public void limpiarTokensAntiguos() {
        Instant fechaLimite = Instant.now().minus(Duration.ofDays(30));
        refreshTokenRepository.deleteByFechaExpiracionBefore(fechaLimite);
    }


}
