package com.betacom.persistence.entity.commerce;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrello_id", referencedColumnName = "id", nullable = false)
    private Carrelli carrello;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "items_id", referencedColumnName = "id", nullable = false)
    private Items item;
}
