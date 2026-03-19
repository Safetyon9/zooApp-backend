package com.betacom.persistence.entity.commerce;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name="prodotti")

public class Prodotti {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
	private String nomeProdotto;
	
	@Column(nullable = false)
	private Integer prezzoProdotto;
	
	@OneToOne
	@JoinColumn(name = "biglietti",referencedColumnName ="id")
	private Biglietti biglietto;

	@OneToMany
	@JoinColumn(name ="oggetti_ordini" , referencedColumnName = "id")
	private List<OggettiOrdini> oggettoOrdine;
	
}
