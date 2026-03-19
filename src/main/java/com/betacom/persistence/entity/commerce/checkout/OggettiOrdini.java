package com.betacom.persistence.entity.commerce.checkout;

import java.math.BigDecimal;

import com.betacom.persistence.entity.commerce.Items;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	@Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoUnitario;
	
	@Column (nullable =false, precision = 10, scale = 2)
	private BigDecimal prezzoTotale;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acquistabile_id", referencedColumnName = "id")
    private Items item;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordine_id", referencedColumnName = "id")
    private Ordini ordine;
}
