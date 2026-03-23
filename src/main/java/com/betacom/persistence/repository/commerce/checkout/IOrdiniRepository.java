package com.betacom.persistence.repository.commerce.checkout;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.commerce.checkout.Ordini;

@Repository
public interface IOrdiniRepository extends JpaRepository<Ordini, Integer> {

    List<Ordini> findByClienteId(Integer clienteId);

}