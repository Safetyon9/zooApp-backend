package com.betacom.persistence.repository.commerce.checkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.commerce.checkout.MetodiPagamento;

@Repository
public interface IMetodiPagamentiRepository extends JpaRepository<MetodiPagamento, Integer>{

}
