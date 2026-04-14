package com.betacom.persistence.repository.commerce;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.commerce.Giornate;

@Repository
public interface IGiornateRepository extends JpaRepository<Giornate, Integer>{

    Optional<Giornate> findByData(LocalDate data);
}
