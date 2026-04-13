package com.betacom.persistence.repository.commerce;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.Eventi;

@Repository
public interface IEventiRepository extends JpaRepository<Eventi, Integer>, JpaSpecificationExecutor<Eventi>{

    List<Eventi> findByTipoEventoContainingIgnoreCase(String tipoEvento);

    List<Eventi> findByDataInizioBetween(LocalDate dataInizio, LocalDate dataFine);

    List<Eventi> findByTipoEventoContainingIgnoreCaseAndDataInizioBetween(
        String tipoEvento,
        LocalDate dataInizio,
        LocalDate dataFine
    );
}