package com.betacom.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.Utenti;

@Repository
public interface IUtentiRepository extends JpaRepository<Utenti, Integer> {

    Optional<Utenti> findByUserNameIgnoreCase(String userName);

    Optional<Utenti> findByEmail(String email);

}