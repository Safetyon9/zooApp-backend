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
@Table(name = "leoni")
public class Leoni extends Animali{
	
	@Column(name = "lunghezza_criniera", nullable = false)
    private Double lunghezzaCriniera; // in centimetri

    @Column(name = "numero_cuccioli", nullable = false)
    private Integer numeroCuccioli;

    @Column(name = "velocità_massima", nullable = false)
    private Double velocitaMassima;	// in km/h
}
