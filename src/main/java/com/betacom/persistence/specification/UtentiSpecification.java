package com.betacom.persistence.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.betacom.dto.inputs.UtentiReq;
import com.betacom.enums.Roles;
import com.betacom.persistence.entity.Utenti;

import jakarta.persistence.criteria.Predicate;

public class UtentiSpecification {

    public static Specification<Utenti> filterByParams(UtentiReq req) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));

            if (req.getUsername() != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("userName")),
                        "%" + req.getUsername().toLowerCase() + "%"));
            }

            if (req.getEmail() != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("email")),
                        "%" + req.getEmail().toLowerCase() + "%"));
            }

            if (req.getRole() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("role"),
                        Roles.valueOf(req.getRole().toUpperCase())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}