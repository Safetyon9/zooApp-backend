package com.betacom.persistence.entity.commerce;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table (name="oggetti_ordine")
public class OggettiOrdini {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column (nullable =false)
	private Integer quantita;
	
	@Column (nullable =false)
	private Integer totale;
	
	@ManyToOne
	@JoinColumn(name ="prodotti" , referencedColumnName = "id")
	private Prodotti prodotto;
	
	@ManyToOne
	@JoinColumn(name = "ordini",referencedColumnName ="id")
	private Ordini ordine;
}
