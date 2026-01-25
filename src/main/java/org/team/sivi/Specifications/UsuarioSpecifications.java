package org.team.sivi.Specifications;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.team.sivi.Model.Rol;
import org.team.sivi.Model.Usuario;

public class UsuarioSpecifications {

    public static Specification<Usuario> nombreLike(String nombre) {
        return (root, query, cb) -> {
            if (nombre == null || nombre.trim().isEmpty()) return null;
            return cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%");
        };
    }

    public static Specification<Usuario> activoEqual(Boolean activo) {
        return (root, query, cb) -> {
            if (activo == null) return null;
            return cb.equal(root.get("activo"), activo);
        };
    }

    public static Specification<Usuario> rolEqual(String rol) {
        return (root, query, cb) -> {
            if (rol == null) return null;
            Join<Usuario, Rol> rolesJoin = root.join("listaRol"); // roles = campo en Usuario
            return cb.equal(rolesJoin.get("nombre"), rol); // nombre = atributo en Rol
        };
    }

}


