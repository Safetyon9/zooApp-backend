package com.betacom.persistence.entity.gestione.animali;

import com.betacom.persistence.entity.gestione.Animali;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pesci")
public class Pesci extends Animali {

    @Column(nullable = false)
    private String tipoAcqua;      // dolce / salata

    @Column(nullable = false)
    private Integer profonditaMax; // profondità massima in metri

    @Column(nullable = false)
    private Boolean squame;        // squame

    @Column(nullable = false)
    private String branchieTipo;   // descrizione tipo branchie

    @Column(nullable = false)
    private Boolean migrazione;    // migrano
}