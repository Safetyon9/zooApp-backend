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
	
	@Column(nullable = false)
    private Double lunghezzaCriniera; // in centimetri

    @Column(nullable = false)
    private Integer numeroCuccioli;

    @Column(nullable = false)
    private Double velocitaMassima;	// in km/h
}
