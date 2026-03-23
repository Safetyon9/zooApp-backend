package com.betacom.persistence.repository.commerce.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.commerce.items.BigliettiGiornata;

@Repository
public interface IBigliettiGiornataRepository extends JpaRepository<BigliettiGiornata, Integer>{

}
