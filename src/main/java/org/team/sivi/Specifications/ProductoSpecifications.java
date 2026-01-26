package org.team.sivi.Specifications;
import org.springframework.data.jpa.domain.Specification;
import org.team.sivi.Model.Producto;

public class ProductoSpecifications {

    public static Specification<Producto> likeNombreOCodigo(String search) {
        return (root, query, cb) -> {
            if (search == null || search.trim().isEmpty()) {return null;
            }
            String pattern = "%" + search.toLowerCase() + "%";
            return cb.or(cb.like(cb.lower(root.get("nombre")), pattern),
                    cb.like(cb.lower(root.get("codigoBarras")), pattern)
            );
        };
    }

    public static Specification<Producto>activoEqual(Boolean activo){
        return (root, query, cb) ->{
            if (activo==null) return null;
            return cb.equal(root.get("activo"),activo);
        };
    }

    public static Specification<Producto> conCategoria(Long categoriaId) {
        return (root, query, cb) -> {
            if (categoriaId == null) {return null;}
            return cb.equal(root.get("categoria").get("id"), categoriaId
            );
        };
    }

    public static Specification<Producto> bajoStockTrue() {
        return (root, query, cb) -> cb.and(
                cb.isTrue(root.get("bajoStock")),
                cb.isTrue(root.get("activo")) // solo productos activos
        );
    }

    //  Trae productos no eliminados
    public static Specification<Producto> noEliminados() {
        return (root, query, cb) -> cb.equal(root.get("softDelete"), false);
    }

    public static Specification<Producto> soloActivos() {
        return (root, query, cb) -> cb.isTrue(root.get("activo"));
    }


}
