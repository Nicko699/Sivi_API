package org.team.sivi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.team.sivi.Model.Marca;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long>, JpaSpecificationExecutor<Marca> {


}
