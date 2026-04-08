package com.betacom.persistence.repository.commerce.items;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.commerce.items.Prodotti;


@Repository
public interface IProdottiRepository extends JpaRepository<Prodotti, Integer>, JpaSpecificationExecutor<Prodotti>  {

    Optional<Prodotti> findBySku(Long sku);

}