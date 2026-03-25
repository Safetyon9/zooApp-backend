package com.betacom.persistence.entity.commerce.items;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="prodotti")
public class Prodotti extends Items{
	
	@Column(nullable = false)
    private Integer stock;
	
	@Column(nullable = false)
    private boolean deleted = false;
	
	@Column(nullable = false, unique = true)
    private Long sku;
	
	@Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal peso;	//kg
	
	@Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal dimensioni;	//volume cm^3
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categorie categoria;
}
