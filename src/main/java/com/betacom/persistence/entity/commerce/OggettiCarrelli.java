package com.betacom.persistence.entity.commerce;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="oggetti_carrello")
public class OggettiCarrelli {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column (nullable =false)
	private Integer quantita;
	
	@Column (nullable =false)
	private Integer prezzoTotale;
	
	@ManyToOne
	@JoinColumn(name = "carrelli",referencedColumnName ="id")
	private Carrelli carrello;
}
