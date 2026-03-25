package com.betacom.persistence.repository.commerce.items;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.persistence.entity.commerce.items.TipiBiglietti;

public interface ITipiBigliettiRepository extends JpaRepository<TipiBiglietti, Integer> {
}