package com.betacom.persistence.repository.commerce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.commerce.Eventi;

@Repository
public interface IEventiRepository extends JpaRepository<Eventi, Integer>{

	
	
	
}
