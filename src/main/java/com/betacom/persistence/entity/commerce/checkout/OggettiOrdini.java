package com.betacom.persistence.entity.commerce.checkout;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.betacom.persistence.entity.commerce.items.Items;

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


@Getter
@Setter
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

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "items_id", referencedColumnName = "id", nullable = false)
    private Items item;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordine_id", referencedColumnName = "id", nullable = false)
    private Ordini ordine;
}
