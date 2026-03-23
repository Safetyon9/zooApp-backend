package com.betacom.persistence.repository.commerce.checkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.commerce.checkout.Pagamenti;

@Repository
public interface IPagamentiRepository extends JpaRepository<Pagamenti, Integer>{

}
