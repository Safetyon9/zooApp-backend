package com.betacom.persistence.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.betacom.dto.inputs.commerce.items.ProdottiReq;
import com.betacom.persistence.entity.commerce.items.Prodotti;

import jakarta.persistence.criteria.Predicate;

public class ProdottiSpecification {

    public static Specification<Prodotti> filterByParams(ProdottiReq req) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            
            predicates.add(criteriaBuilder.equal(root.get("deleted"), false));

            if (req.getCategoriaId() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("categoria").get("id"), req.getCategoriaId()));
            }

            if (req.getNome() != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nome")),
                        "%" + req.getNome().toLowerCase() + "%"));
            }

            if (req.getPrezzo() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("prezzo"), req.getPrezzo()));
            }

            if (req.getPeso() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("peso"), req.getPeso()));
            }

            if (req.getDimensioni() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("dimensioni"), req.getDimensioni()));
            }

            if (req.getSku() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("sku"), req.getSku()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}