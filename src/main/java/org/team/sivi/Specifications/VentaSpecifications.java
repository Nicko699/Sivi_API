package org.team.sivi.Specifications;

import org.springframework.data.jpa.domain.Specification;
import org.team.sivi.Model.Usuario;
import org.team.sivi.Model.Venta;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VentaSpecifications {

    // Filtrar por código de venta
    public static Specification<Venta> codigoVentaLike(String codigoVenta) {
        return (root, query, cb) -> {
            if (codigoVenta == null || codigoVenta.isBlank()) {
                return cb.conjunction(); // sin filtro
            }
            return cb.like(cb.lower(root.get("codigoVenta")), "%" + codigoVenta.toLowerCase() + "%");
        };
    }

    // Filtrar por estado (VENDIDO, FIADO, CANCELADO)
    public static Specification<Venta> estadoEquals(String estado) {
        return (root, query, cb) -> {
            if (estado == null || estado.isBlank()) {
                return cb.conjunction();
            }
            return cb.equal(cb.lower(root.get("estado")), estado.toLowerCase());
        };
    }

    public static Specification<Venta> usuarioEquals(String nombreUsuario) {
        return (root, query, cb) -> {
            if (nombreUsuario == null || nombreUsuario.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(
                    cb.lower(root.get("nombreUsuario")),
                    "%" + nombreUsuario.toLowerCase().trim() + "%"
            );
        };
    }



    public static Specification<Venta> fechaEntre(LocalDate desde, LocalDate hasta) {
        return (root, query, cb) -> {
            if (desde == null && hasta == null) {
                return cb.conjunction(); // no aplica filtro
            } else if (desde != null && hasta == null) {
                return cb.greaterThanOrEqualTo(root.get("fechaVenta"), desde.atStartOfDay());
            } else if (desde == null) {
                return cb.lessThanOrEqualTo(root.get("fechaVenta"), hasta.atTime(23, 59, 59));
            } else {
                return cb.between(root.get("fechaVenta"), desde.atStartOfDay(), hasta.atTime(23, 59, 59));
            }
        };
    }


    // Filtrar por correo del usuario (si Venta tiene el campo "correoUsuario" o similar)
    public static Specification<Venta> correoUsuarioEquals(String correo) {
        return (root, query, cb) -> {
            if (correo == null || correo.trim().isEmpty()) {
                return null;
            }
            // Ajusta correoUsuario al nombre real del campo en tu entidad Venta
            return cb.equal(cb.lower(root.get("email")), correo.toLowerCase());
        };
    }
    }

