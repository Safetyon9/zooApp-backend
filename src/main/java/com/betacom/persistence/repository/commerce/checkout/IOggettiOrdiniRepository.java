package com.betacom.persistence.repository.commerce.checkout;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.commerce.checkout.OggettiOrdini;

@Repository
public interface IOggettiOrdiniRepository extends JpaRepository<OggettiOrdini, Integer> {

    List<OggettiOrdini> findByOrdineId(Integer ordineId);

}