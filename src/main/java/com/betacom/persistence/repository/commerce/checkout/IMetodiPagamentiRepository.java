package com.betacom.persistence.repository.commerce.checkout;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.persistence.entity.commerce.checkout.MetodiPagamento;

public interface IMetodiPagamentiRepository extends JpaRepository<MetodiPagamento, Integer>{

}
