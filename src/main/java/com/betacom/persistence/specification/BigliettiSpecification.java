package com.betacom.persistence.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

import com.betacom.persistence.entity.commerce.items.Biglietti;

public class BigliettiSpecification {
	public static Specification<Biglietti> filter(String nome, Integer tipoId, BigDecimal prezzoMin, BigDecimal prezzoMax) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();

            if (nome != null && !nome.isBlank()) {
                p = cb.and(p, cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            if (tipoId != null) {
                p = cb.and(p, cb.equal(root.get("tipo").get("id"), tipoId));
            }

            if (prezzoMin != null) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("prezzo"), prezzoMin));
            }

            if (prezzoMax != null) {
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("prezzo"), prezzoMax));
            }

            return p;
        };
    }
}
