package com.betacom.persistence.entity.commerce;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="ordine")
public class Ordini {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column (nullable = false)
	private LocalDate dataOrdine;
	
	@Column (nullable = false)
	private String nome;
	
	@Column (nullable = false)
	private String cognome;
	
	@Column (nullable = false)
	private String indirizzo;
	
	@OneToMany
	@JoinColumn(name = "oggetti_ordine",referencedColumnName ="id")
	private List<OggettiOrdini> OggettoOrdine;
	
	
}