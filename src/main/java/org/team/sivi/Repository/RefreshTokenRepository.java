package org.team.sivi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team.sivi.Model.RefreshToken;
import org.team.sivi.Model.Usuario;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

  Optional<RefreshToken> findRefreshTokenByUsuarioAndActivo(Usuario usuario, boolean activo);

  void deleteRefreshTokenByFechaExpiracionBefore(Instant fechaExpiracionBefore);



}
