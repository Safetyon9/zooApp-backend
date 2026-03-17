package com.betacom.persistence.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name="area")
public class Aree {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@Column (nullable = false)
	private String tipoStruttura;
	

	@OneToOne(
			mappedBy = "area",
			cascade =  CascadeType.REMOVE
			)
	private Animali animale;
	
	@OneToMany(
			mappedBy = "area",
			fetch = FetchType.EAGER
			)
	private List<Dipendenti> dipendente;
	
	
	
}
