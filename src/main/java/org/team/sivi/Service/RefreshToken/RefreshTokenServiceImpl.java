package org.team.sivi.Service.RefreshToken;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.RefreshTokenDto.RefreshTokenRenovarAccesTokenResponseDto;
import org.team.sivi.Dto.RefreshTokenDto.RefreshTokenResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Exception.UnauthorizedException;
import org.team.sivi.Model.RefreshToken;
import org.team.sivi.Model.Usuario;
import org.team.sivi.Repository.RefreshTokenRepository;
import org.team.sivi.Security.CookieUtils.CookieOnlyUtils;
import org.team.sivi.Security.TokenUtils.AccesTokenUtils;
import org.team.sivi.Security.TokenUtils.RefreshTokenUtils;

import java.time.Instant;
import java.util.Optional;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccesTokenUtils accesTokenUtils;
    private final RefreshTokenUtils refreshTokenUtils;
    private final CookieOnlyUtils cookieOnlyUtils;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, PasswordEncoder passwordEncoder, AccesTokenUtils accesTokenUtils, RefreshTokenUtils refreshTokenUtils, CookieOnlyUtils cookieOnlyUtils) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.accesTokenUtils = accesTokenUtils;
        this.refreshTokenUtils = refreshTokenUtils;
        this.cookieOnlyUtils = cookieOnlyUtils;
    }
   //Metodo para renovar el acces token y refreshToken
    @Transactional
    @Override
    public RefreshTokenRenovarAccesTokenResponseDto renovarToken(HttpServletResponse httpServletResponse, String refreshTokenId, String refreshToken) throws NotFoundException, UnauthorizedException {
       //Encontramos al refreshToken en la bd por su refreshTokenId
        Optional<RefreshToken>refreshTokenEncontrado=refreshTokenRepository.findByRefreshToken(refreshTokenId);
    //Comprobamos si existe en la bd
        if (refreshTokenEncontrado.isPresent()){
            RefreshToken refreshTokenGet=refreshTokenEncontrado.get();
           //Comparamos el refreshToken sin encriptar del front con el refreshToken encriptado de la bd si coinciden
            boolean valido=passwordEncoder.matches(refreshToken,refreshTokenGet.getRefreshTokenHash());
         //Validamos si el refreshToken es válido, está activo y su fecha expiracion no ah caudado
            if (valido && refreshTokenGet.isActivo() && refreshTokenGet.getFechaExpiracion().isAfter(Instant.now())){

                //Obtenemos el usuario del refreshToken
                Usuario usuario=refreshTokenGet.getUsuario();

                //Creamos el nuevo access token y refreshToken
                String nuevoAccessToken=accesTokenUtils.token(usuario.getCorreo(),usuario.getNombre(),usuario.getListaRol());
                RefreshTokenResponseDto nuevoRefreshToken=refreshTokenUtils.crearRefreshToken(usuario.getCorreo());

                //Creamos la cookieOnly para el refreshToken e impédir que me obtenga los tokens facilmente
                cookieOnlyUtils.crearCookieOnly(httpServletResponse,nuevoRefreshToken.getRefreshTokenId(),nuevoRefreshToken.getRefreshToken());

                return new RefreshTokenRenovarAccesTokenResponseDto(nuevoAccessToken,"Bearer");

            }
            else {
                throw new UnauthorizedException("RefreshToken invalido o no está activo o su fecha expiración ha caducado");
            }

        }
        else {
            throw new NotFoundException("El refreshToken no se encuentra en nuestro sistema");
        }

    }
}
