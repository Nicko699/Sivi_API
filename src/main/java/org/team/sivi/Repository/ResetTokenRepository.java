package org.team.sivi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Model.ResetToken;
import org.team.sivi.Model.Usuario;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken,Long> {

    Optional<ResetToken> findByResetToken(String resetToken);

    Optional<ResetToken> findByUsuario(Usuario usuario);


    //  Eliminar por el ID del propio ResetToken
    @Modifying
    @Query("DELETE FROM ResetToken r WHERE r.id = :id")
    void deleteResetTokenById(@Param("id") Long id);







}
