package com.betacom.persistence.repository.commerce;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.persistence.entity.commerce.OggettiCarrelli;

public interface IOggettiCarrelliRepository extends JpaRepository<OggettiCarrelli, Integer>{
	Optional<OggettiCarrelli> findByCarrelloIdAndItemId(Integer carrelloId, Integer itemId);
}
