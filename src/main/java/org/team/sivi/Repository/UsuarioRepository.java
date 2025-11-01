package org.team.sivi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team.sivi.Model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

  Optional<Usuario> findUsuarioByCorreo(String correo);

  boolean existsUsuarioByCorreo(String correo);




}
