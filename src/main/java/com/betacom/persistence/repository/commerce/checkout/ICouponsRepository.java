package com.betacom.persistence.repository.commerce.checkout;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.commerce.checkout.Coupons;

@Repository
public interface ICouponsRepository extends JpaRepository<Coupons, Integer>{

	boolean existsByCodice(String codiceFinale);
	
	Optional<Coupons> findByCodice(String codice);

}
