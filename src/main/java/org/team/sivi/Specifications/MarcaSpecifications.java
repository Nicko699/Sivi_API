package org.team.sivi.Specifications;

import org.springframework.data.jpa.domain.Specification;
import org.team.sivi.Model.Categoria;
import org.team.sivi.Model.Marca;

public class MarcaSpecifications {

    public static Specification<Marca>likeNombre(String nombre){
        return (root, query, cb) -> {
            if (nombre==null || nombre.trim().isEmpty()) return null;
            return cb.like(cb.lower(root.get("nombre")), "%" +nombre.toLowerCase()+"%");
        };
    }

    public static Specification<Marca>activoEqual(Boolean activo){
        return (root, query, cb) -> {
            if (activo==null)return null;
            return cb.equal(root.get("activo"),activo);
        };
    }

}
