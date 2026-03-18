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
@Table(name="serpenti")
public class Serpenti extends Animali{

	@Column(nullable = false)
	private Integer lunghezzaCm; 
	
	@Column(nullable = false)
	private Boolean velenoso; //true velenoso false no
	
	@Column(nullable = false)
	private Boolean zanne;  //true ce l ha false no
	
	@Column(nullable = false)
	private Integer tempoDigestioneGiorni;
	
	
}