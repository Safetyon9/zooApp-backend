package com.betacom.persistence.repository.commerce.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.betacom.persistence.entity.commerce.items.Biglietti;

public interface IBigliettiRepository extends JpaRepository<Biglietti, Integer>, JpaSpecificationExecutor<Biglietti>  {

}