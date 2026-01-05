package org.team.sivi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team.sivi.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
