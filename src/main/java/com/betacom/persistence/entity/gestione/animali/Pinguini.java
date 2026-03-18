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
@Table(name = "pinguini")
public class Pinguini extends Animali {

    @Column(nullable = false)
    private String piume;              // descrizione piumaggio

    @Column(nullable = false)
    private Double velocitaNuoto;      // in km/h

    @Column(nullable = false)
    private Integer uovaMedia;         // numero medio di uova per covata

    @Column(nullable = false)
    private Boolean impermeabilita;    // true se piume molto impermeabili
}