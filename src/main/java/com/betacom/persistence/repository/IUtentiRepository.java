package com.betacom.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.items.Prodotti;

@Repository
public interface IUtentiRepository extends JpaRepository<Utenti, String> , JpaSpecificationExecutor<Utenti>{

    Optional<Utenti> findByUserName(String userName);

    Optional<Utenti> findByEmail(String email);
    
    

}