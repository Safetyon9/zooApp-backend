package com.betacom.persistence.entity.commerce.items;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="prodotti")
public class Prodotti extends Items{
	
	@Column(nullable = true)
    private String categoria;
	
	@Column(nullable = false)
    private Integer stock;
	
	@Column(nullable = false, unique = true)
    private Long sku;
	
	@Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal peso;
	
	@Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal dimensioni;
}
