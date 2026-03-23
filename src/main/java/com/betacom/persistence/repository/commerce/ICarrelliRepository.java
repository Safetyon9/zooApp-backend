package com.betacom.persistence.repository.commerce;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.persistence.entity.commerce.Carrelli;

public interface ICarrelliRepository extends JpaRepository<Carrelli, Integer>{
	Optional<Carrelli> findByClienteId(Integer clienteId);
}
