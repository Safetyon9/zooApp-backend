package com.betacom.persistence.entity.gestione.animali;

import com.betacom.persistence.entity.gestione.Animali;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tartarughe")
public class Tartarughe extends Animali{


	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
	private String tipo; //terra,acqua,mare
	
	@Column(nullable = false)
	private Boolean ovipara; //true fa le uova false no
	
	@Column(nullable = false)
	private Boolean letargo; //true e in letargo false sveglia
	
	
	
	
	
}
