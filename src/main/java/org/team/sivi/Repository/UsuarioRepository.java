package org.team.sivi.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.team.sivi.Model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>,JpaSpecificationExecutor<Usuario> {

  Optional<Usuario> findUsuarioByCorreoAndSoftDeleteFalse(String correo);

  boolean existsUsuarioByCorreoAndSoftDeleteFalse(String correo);

  Page<Usuario> findAllByOrderByIdAsc(Pageable pageable);

  long countByListaRol_NombreAndActivoAndSoftDeleteFalse(String listaRolNombre, Boolean activo);

  long countByListaRol_Nombre(String listaRolNombre);

  Optional<Usuario> findByIdAndSoftDeleteFalse(Long id);












}
