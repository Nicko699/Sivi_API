package org.team.sivi.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.team.sivi.Dto.RefreshTokenRenovarAccesTokenRequestDto;
import org.team.sivi.Dto.RefreshTokenRenovarAccesTokenResponseDto;
import org.team.sivi.Dto.RefreshTokenResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Exception.UnauthorizedException;
import org.team.sivi.Model.RefreshToken;
import org.team.sivi.Model.Usuario;
import org.team.sivi.Repository.RefreshTokenRepository;
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

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, PasswordEncoder passwordEncoder, AccesTokenUtils accesTokenUtils, RefreshTokenUtils refreshTokenUtils) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.accesTokenUtils = accesTokenUtils;
        this.refreshTokenUtils = refreshTokenUtils;
    }
   //Metodo para renovar el acces token y refreshToken
    @Override
    public RefreshTokenRenovarAccesTokenResponseDto renovarToken(RefreshTokenRenovarAccesTokenRequestDto renovarAccesTokenRequestDto) throws NotFoundException, UnauthorizedException {
       //Encontramos al refreshToken en la bd por su refreshTokenId
        Optional<RefreshToken>refreshToken=refreshTokenRepository.findByRefreshToken(renovarAccesTokenRequestDto.getRefreshTokenId());
    //Comprobamos si existe en la bd
        if (refreshToken.isPresent()){
            RefreshToken refreshTokenGet=refreshToken.get();
           //Comparamos el refreshToken sin encriptar del front con el refreshToken encriptado de la bd si coinciden
            boolean valido=passwordEncoder.matches(renovarAccesTokenRequestDto.getRefreshToken(),refreshTokenGet.getRefreshTokenHash());
         //Validamos si el refreshToken es válido, está activo y su fecha expiracion no ah caudado
            if (valido && refreshTokenGet.isActivo() && refreshTokenGet.getFechaExpiracion().isAfter(Instant.now())){

                //Obtenemos el usuario del refreshToken
                Usuario usuario=refreshTokenGet.getUsuario();

                //Creamos el nuevo access token y refreshToken
                String nuevoAccessToken=accesTokenUtils.token(usuario.getCorreo(),usuario.getListaRol());
                RefreshTokenResponseDto nuevoRefreshToken=refreshTokenUtils.crearRefreshToken(usuario.getCorreo());

                return new RefreshTokenRenovarAccesTokenResponseDto(nuevoAccessToken,"Bearer",nuevoRefreshToken);

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
