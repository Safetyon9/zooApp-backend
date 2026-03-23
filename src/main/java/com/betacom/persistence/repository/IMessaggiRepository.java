package com.betacom.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.Messaggi;
import com.betacom.persistence.entity.MessaggiID;

@Repository
public interface IMessaggiRepository extends JpaRepository<Messaggi, MessaggiID>{

}
