package com.betacom.persistence.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.betacom.dto.inputs.commerce.EventiReq;
import com.betacom.persistence.entity.commerce.Eventi;

import jakarta.persistence.criteria.Predicate;

public class EventiSpecification {

    public static Specification<Eventi> filterByParams(EventiReq req) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (req.getTipoEvento() != null && !req.getTipoEvento().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("tipoEvento")),
                        "%" + req.getTipoEvento().toLowerCase() + "%"));
            }

            if (req.getDescrizione() != null && !req.getDescrizione().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("descrizione")),
                        "%" + req.getDescrizione().toLowerCase() + "%"));
            }

            if (req.getDataInizio() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("dataInizio"),
                        req.getDataInizio()));
            }

            if (req.getDataFine() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("dataFine"),
                        req.getDataFine()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}