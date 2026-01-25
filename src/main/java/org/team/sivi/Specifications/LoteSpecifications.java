package org.team.sivi.Specifications;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.team.sivi.Model.Lote;

public class LoteSpecifications {

    // Buscar por código de lote
    public static Specification<Lote> likeCodigoLote(String search) {
        return (root, query, cb) -> {
            if (search == null || search.trim().isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("codigoLote")), "%" + search.toLowerCase() + "%");
        };
    }

    //  Buscar por código de barras del producto con Join
    public static Specification<Lote> likeCodigoBarrasProducto(String search) {
        return (root, query, cb) -> {
            if (search == null || search.trim().isEmpty()) {
                return cb.conjunction();
            }
            Join<Object, Object> productoJoin = root.join("producto", JoinType.INNER);
            return cb.like(cb.lower(productoJoin.get("codigoBarras")), "%" + search.toLowerCase() + "%");
        };
    }

    // Filtrar por estado agotado
    public static Specification<Lote> agotadoEqual(Boolean agotado) {
        return (root, query, cb) -> {
            if (agotado == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("agotado"), agotado);
        };
    }

    //  Excluir los lotes con soft delete
    public static Specification<Lote> noEliminados() {
        return (root, query, cb) -> cb.isFalse(root.get("softDelete"));
    }

    // Combinar búsqueda general por código de lote o producto
    public static Specification<Lote> buscarPorCodigo(String search) {
        return Specification.allOf(likeCodigoLote(search))
                .or(likeCodigoBarrasProducto(search));
    }
}
