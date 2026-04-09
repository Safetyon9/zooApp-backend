package com.betacom.persistence.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.betacom.persistence.entity.commerce.items.Biglietti;

import jakarta.persistence.criteria.Predicate;

public class BigliettiSpecification {

    public static Specification<Biglietti> filter(String nome, Integer tipoId, BigDecimal prezzoMin, BigDecimal prezzoMax) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nome != null && !nome.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            if (tipoId != null) {
                predicates.add(cb.equal(root.get("tipo").get("id"), tipoId));
            }

            if (prezzoMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("prezzo"), prezzoMin));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}